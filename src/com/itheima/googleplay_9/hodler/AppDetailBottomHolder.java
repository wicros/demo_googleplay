package com.itheima.googleplay_9.hodler;

import java.io.File;

import android.view.View;
import android.view.View.OnClickListener;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.manager.DownLoadInfo;
import com.itheima.googleplay_9.manager.DownLoadManager;
import com.itheima.googleplay_9.manager.DownLoadManager.DownLoadObserver;
import com.itheima.googleplay_9.utils.CommonUtils;
import com.itheima.googleplay_9.utils.PrintDownLoadInfo;
import com.itheima.googleplay_9.utils.UIUtils;
import com.itheima.googleplay_9.views.ProgressButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 下午2:46:20
 * @描述	     TODO
 *
 * @版本       $Rev: 44 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 16:45:06 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class AppDetailBottomHolder extends BaseHolder<AppInfoBean> implements OnClickListener, DownLoadObserver {
	@ViewInject(R.id.app_detail_download_btn_download)
	ProgressButton		mProgressBtn;
	private AppInfoBean	mData;

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_bottom, null);
		ViewUtils.inject(this, view);
		mProgressBtn.setOnClickListener(this);
		return view;
	}

	@Override
	protected void rerefreshHolderView(AppInfoBean data) {
		mData = data;
		DownLoadInfo info = DownLoadManager.getInstance().getDownLoadInfo(mData);
		/*=============== 根据不同的状态给用户提示 ===============*/
		refreshProgressBtnUI(info);
	}

	public void refreshProgressBtnUI(DownLoadInfo info) {

		/**
		状态(编程记录)  	|  给用户的提示(ui展现) 
		未下载			|下载				    	
		下载中			|显示进度条		   			
		暂停下载			|继续下载				
		等待下载			|等待中...				
		下载失败 			|重试						
		下载完成 			|安装						
		已安装 			|打开						
		 */
//		
		mProgressBtn.setBackgroundResource(R.drawable.selector_app_detail_bottom_normal);
		switch (info.state) {
		
		case DownLoadManager.STATE_UNDOWNLOAD:// 未下载
			mProgressBtn.setText("下载");
			break;
		case DownLoadManager.STATE_DOWNLOADING:// 下载中
			mProgressBtn.setBackgroundResource(R.drawable.selector_app_detail_bottom_downloading);
			mProgressBtn.setProgressEnable(true);
			mProgressBtn.setMax(info.max);
			mProgressBtn.setProgress(info.progress);
			int progress = (int) (info.progress * 1.0f / info.max * 100 + .5f);
			mProgressBtn.setText(progress + "%");

			break;
		case DownLoadManager.STATE_PAUSEDOWNLOAD:// 暂停下载
			mProgressBtn.setText("继续下载");
			break;
		case DownLoadManager.STATE_WAITINGDOWNLOAD:// 等待下载
			mProgressBtn.setText("等待中...");
			break;
		case DownLoadManager.STATE_DOWNLOADFAILED:// 下载失败
			mProgressBtn.setText("重试");
			break;
		case DownLoadManager.STATE_DOWNLOADED:// 下载完成
			mProgressBtn.setText("安装");
			break;
		case DownLoadManager.STATE_INSTALLED:// 已安装
			mProgressBtn.setText("打开");
			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		/**
		 状态(编程记录)    		| 用户行为(触发操作) 
		未下载				| 去下载
		下载中				| 暂停下载
		暂停下载				| 断点继续下载
		等待下载				| 取消下载
		下载失败 				| 重试下载
		下载完成 				| 安装应用
		已安装 				| 打开应用              
		 */
		switch (v.getId()) {
		case R.id.app_detail_download_btn_download:
			/*=============== 根据不同的状态触发不同的操作 ===============*/
			// 得到当前的状态-->其实就是希望downLoadManager能够返回当前的downLoadInfo(state)
			DownLoadInfo info = DownLoadManager.getInstance().getDownLoadInfo(mData);
			switch (info.state) {
			case DownLoadManager.STATE_UNDOWNLOAD:// 未下载
				doDownLoad(info);
				break;
			case DownLoadManager.STATE_DOWNLOADING:// 下载中
				pauseDownLoad(info);
				break;
			case DownLoadManager.STATE_PAUSEDOWNLOAD:// 暂停下载
				doDownLoad(info);
				break;
			case DownLoadManager.STATE_WAITINGDOWNLOAD:// 等待下载
				cancelDownLoad(info);
				break;
			case DownLoadManager.STATE_DOWNLOADFAILED:// 下载失败
				doDownLoad(info);
				break;
			case DownLoadManager.STATE_DOWNLOADED:// 下载完成
				installApk(info);
				break;
			case DownLoadManager.STATE_INSTALLED:// 已安装
				openApk(info);
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 安装apk
	 * @param info
	 */
	private void installApk(DownLoadInfo info) {
		File apkFile = new File(info.savePath);
		CommonUtils.installApp(UIUtils.getContext(), apkFile);
	}

	/**
	 * 打开apk
	 * @param info
	 */
	private void openApk(DownLoadInfo info) {
		CommonUtils.openApp(UIUtils.getContext(), info.packageName);
	}

	/**
	 * 开始下载
	 * @param info
	 */
	public void doDownLoad(DownLoadInfo info) {
		// 开始下载
		// 开线程池-->请求网络-->保存文件
		// ThreadFactory.getDownLoadPool().execute(new DownLoadTask());
		// 下载地址
		// 下载文件保存到哪里
		/*DownLoadInfo info = new DownLoadInfo();
		info.downLoadUrl = mData.downloadUrl;

		// 保存的文件夹
		String dir = FileUtils.getDir("apk");// sdcard/android/data/包名/apk
		String saveName = mData.packageName + ".apk";
		File saveFile = new File(dir, saveName);// com.itheima.www.apk

		info.savePath = saveFile.getAbsolutePath();// sdcard/android/data/包名/apk/com.itheima.www.apk

		info.packageName = mData.packageName;*/
		DownLoadManager.getInstance().downLoad(info);
	}

	/**
	 * 暂停下载
	 * @param info
	 */
	private void pauseDownLoad(DownLoadInfo info) {
		DownLoadManager.getInstance().pauseDownLoad(info);
	}

	/**
	 * 取消下载
	 * @param info
	 */
	private void cancelDownLoad(DownLoadInfo info) {
		DownLoadManager.getInstance().cancelDownLoad(info);
	}

	/*=============== 时刻监听DownLoadManger发布的最新的downLoadInfo ===============*/
	@Override
	public void onDownloadInfoChange(final DownLoadInfo downLoadInfo) {
		// 过滤
		if (!mData.packageName.equals(downLoadInfo.packageName)) {
			return;
		}

		PrintDownLoadInfo.printDownLoadInfo(downLoadInfo);
		// 刷新ui
		UIUtils.postTaskSafely(new Runnable() {

			@Override
			public void run() {
				// TODO
				refreshProgressBtnUI(downLoadInfo);
			}
		});
	}

	/**
	 * 添加观察者到观察者集合
	 * 手动更新到最新的状态
	 */
	public void addObserverAndRefresh() {
		// 添加观察者到观察者集合
		DownLoadManager.getInstance().addObserver(this);
		// 手动更新到最新的状态
		DownLoadInfo downLoadInfo = DownLoadManager.getInstance().getDownLoadInfo(mData);
		DownLoadManager.getInstance().notifyObservers(downLoadInfo);
	}
}
