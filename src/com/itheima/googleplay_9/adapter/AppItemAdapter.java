package com.itheima.googleplay_9.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.itheima.googleplay_9.activity.DetailActivity;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.base.SuperBaseAdapter;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.hodler.AppItemHolder;
import com.itheima.googleplay_9.manager.DownLoadManager;
import com.itheima.googleplay_9.utils.UIUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午11:14:33
 * @描述	     TODO
 *
 * @版本       $Rev: 45 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 17:20:02 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class AppItemAdapter extends SuperBaseAdapter<AppInfoBean> {
	/**定义集合存储所有的AppItemHolder*/
	public List<AppItemHolder>	mAppItemHolders	= new ArrayList<AppItemHolder>();

	public List<AppItemHolder> getAppItemHolders() {
		return mAppItemHolders;
	}

	public AppItemAdapter(AbsListView absListView, List<AppInfoBean> datas) {
		super(absListView, datas);
	}

	@Override
	protected BaseHolder<AppInfoBean> getSpecialHolder(int position) {
		AppItemHolder appItemHolder = new AppItemHolder();
		mAppItemHolders.add(appItemHolder);
		// 添加到观察者集合中
		DownLoadManager.getInstance().addObserver(appItemHolder);

		return appItemHolder;
	}

	@Override
	public void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {
		gotoDetailActivity(mDataSource.get(position).packageName);
		super.onNormalItemClick(parent, view, position, id);
	}

	private void gotoDetailActivity(String packageName) {
		Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
		intent.putExtra("packageName", packageName);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		UIUtils.getContext().startActivity(intent);// getapplicationcontext
	}

}
