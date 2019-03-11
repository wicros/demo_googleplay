package com.itheima.googleplay_9.hodler;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.bean.CategoryBean;
import com.itheima.googleplay_9.utils.UIUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午9:04:55
 * @描述	     TODO
 *
 * @版本       $Rev: 31 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 09:52:29 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class CategoryTitleHolder extends BaseHolder<CategoryBean> {

	private TextView	mTvTitle;

	@Override
	protected View initHolderView() {
		mTvTitle = new TextView(UIUtils.getContext());
		mTvTitle.setBackgroundColor(Color.WHITE);
		int padding = UIUtils.dip2Px(3);
		mTvTitle.setPadding(padding, padding, padding, padding);
		return mTvTitle;
	}

	@Override
	protected void rerefreshHolderView(CategoryBean data) {
		mTvTitle.setText(data.title);
	}

}
