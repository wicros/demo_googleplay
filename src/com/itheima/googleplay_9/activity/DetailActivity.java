package com.itheima.googleplay_9.activity;

import android.app.ActionBar;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseActivity;
import com.itheima.googleplay_9.base.LoadingPager;
import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.hodler.AppDetailBottomHolder;
import com.itheima.googleplay_9.hodler.AppDetailDesHolder;
import com.itheima.googleplay_9.hodler.AppDetailInfoHolder;
import com.itheima.googleplay_9.hodler.AppDetailPicHolder;
import com.itheima.googleplay_9.hodler.AppDetailSafeHolder;
import com.itheima.googleplay_9.manager.DownLoadManager;
import com.itheima.googleplay_9.protocol.AppDetailProtocol;
import com.itheima.googleplay_9.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午11:21:17
 * @描述	     TODO
 *
 * @版本       $Rev: 42 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 15:25:49 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class DetailActivity extends BaseActivity {
	private String					mPackageName;
	private LoadingPager			mLoadingPager;
	private AppInfoBean				mAppInfoBean;

	@ViewInject(R.id.app_detail_bottom)
	FrameLayout						mContainerBottom;

	@ViewInject(R.id.app_detail_des)
	FrameLayout						mContainerDes;

	@ViewInject(R.id.app_detail_info)
	FrameLayout						mContainerInfo;

	@ViewInject(R.id.app_detail_pic)
	FrameLayout						mContainerPic;

	@ViewInject(R.id.app_detail_safe)
	FrameLayout						mContainerSafe;
	private AppDetailBottomHolder	mAppDetailBottomHolder;

	@Override
	protected void init() {
		mPackageName = getIntent().getStringExtra("packageName");
	}

	@Override
	protected void initView() {// 告诉DetailActivity视图显示
		mLoadingPager = new LoadingPager(UIUtils.getContext()) {
			@Override
			protected LoadedResult initData() {
				return DetailActivity.this.loadData();
			}

			@Override
			protected View initSuccessView() {
				return DetailActivity.this.initSuccessView();
			}
		};
		setContentView(mLoadingPager);
	}

	/**
	 * activtiy具体子线程中应该加载什么数据
	 * @return
	 */
	protected LoadedResult loadData() {
		// protocol-->封装本地缓存
		AppDetailProtocol protocol = new AppDetailProtocol(mPackageName);
		try {
			mAppInfoBean = protocol.loadData(0);
			if (mAppInfoBean == null) {
				return LoadedResult.EMPTY;
			}
			return LoadedResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return LoadedResult.ERROR;
		}
	}

	@Override
	protected void initActionBar() {
		ActionBar supportActionBar = getSupportActionBar();
		supportActionBar.setTitle("GooglePlay");
		supportActionBar.setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 返回Activity对应的成功视图
	 * @return
	 */
	private View initSuccessView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_detail, null);
		// 找出几个占位的FrameLayout
		ViewUtils.inject(this, view);

		// app 信息部分
		AppDetailInfoHolder appDetailInfoHolder = new AppDetailInfoHolder();
		mContainerInfo.addView(appDetailInfoHolder.mHolderView);
		appDetailInfoHolder.setDataAndRefreshHolderView(mAppInfoBean);

		// app 安全部分
		AppDetailSafeHolder appDetailSafeHolder = new AppDetailSafeHolder();
		mContainerSafe.addView(appDetailSafeHolder.mHolderView);
		appDetailSafeHolder.setDataAndRefreshHolderView(mAppInfoBean);

		// app 截图部分
		AppDetailPicHolder appDetailPicHolder = new AppDetailPicHolder();
		mContainerPic.addView(appDetailPicHolder.mHolderView);
		appDetailPicHolder.setDataAndRefreshHolderView(mAppInfoBean);

		// app 描述部分
		AppDetailDesHolder appDetailDesHolder = new AppDetailDesHolder();
		mContainerDes.addView(appDetailDesHolder.mHolderView);
		appDetailDesHolder.setDataAndRefreshHolderView(mAppInfoBean);

		mAppDetailBottomHolder = new AppDetailBottomHolder();
		mContainerBottom.addView(mAppDetailBottomHolder.mHolderView);
		mAppDetailBottomHolder.setDataAndRefreshHolderView(mAppInfoBean);

		// 添加appDetailBottomHolder到观察者集合中
		DownLoadManager.getInstance().addObserver(mAppDetailBottomHolder);

		return view;
	}

	@Override
	protected void onResume() {
		// 添加监听
		if (mAppDetailBottomHolder != null) {
			mAppDetailBottomHolder.addObserverAndRefresh();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		// 移除监听
		if (mAppDetailBottomHolder != null) {
			DownLoadManager.getInstance().deleteObserver(mAppDetailBottomHolder);
		}
		super.onPause();
	}

	@Override
	protected void initData() {
		mLoadingPager.triggerLoadData();
	}

	@Override
	protected void initListener() {
		// TODO

	}
}
