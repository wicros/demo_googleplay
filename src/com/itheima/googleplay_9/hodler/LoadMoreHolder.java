package com.itheima.googleplay_9.hodler;

import android.view.View;
import android.widget.LinearLayout;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 下午3:24:39
 * @描述	     TODO
 *
 * @版本       $Rev: 16 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 15:36:10 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public class LoadMoreHolder extends BaseHolder<Integer> {
	@ViewInject(R.id.item_loadmore_container_loading)
	LinearLayout			mContainerLoading;

	@ViewInject(R.id.item_loadmore_container_retry)
	LinearLayout			mContainerRetry;

	public static final int	STATE_LOADING	= 1;	// 显示加载效果
	public static final int	STATE_RETRY		= 2;	// 显示重试效果
	public static final int	STATE_EMPTY		= 3;	// 没有加载更多,两个都不显示

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_loadmore, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	protected void rerefreshHolderView(Integer state) {
		// 根据传过来的状态去区分显示

		// 首先隐藏2个布局
		mContainerLoading.setVisibility(8);
		mContainerRetry.setVisibility(8);
		switch (state) {
		case STATE_LOADING:
			mContainerLoading.setVisibility(0);

			break;
		case STATE_RETRY:
			mContainerRetry.setVisibility(0);
			break;
		case STATE_EMPTY:
			
			break;

		default:
			break;
		}
	}

}
