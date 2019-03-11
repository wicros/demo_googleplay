package com.itheima.googleplay_9.hodler;

import java.util.List;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.conf.Constants.URLS;
import com.itheima.googleplay_9.utils.BitmapHelper;
import com.itheima.googleplay_9.utils.UIUtils;
import com.itheima.googleplay_9.views.RatioLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 下午2:46:20
 * @描述	     TODO
 *
 * @版本       $Rev: 37 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 17:20:53 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class AppDetailPicHolder extends BaseHolder<AppInfoBean> {
	@ViewInject(R.id.app_detail_pic_iv_container)
	LinearLayout	mContainerPic;

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_pic, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	protected void rerefreshHolderView(AppInfoBean data) {
		List<String> screen = data.screen;

		for (int i = 0; i < screen.size(); i++) {
			String picUrl = screen.get(i);
			ImageView iv = new ImageView(UIUtils.getContext());

			iv.setImageResource(R.drawable.ic_default);
			BitmapHelper.display(iv, URLS.IMAGEBASEURL + picUrl);

			RatioLayout ratioLayout = new RatioLayout(UIUtils.getContext());
			ratioLayout.setPicRatio((float) 150 / 250);
			ratioLayout.setRelative(RatioLayout.RELATIVE_WITH);
			// 把imageView添加到RatioLayout
			ratioLayout.addView(iv);

			int widthPixels = UIUtils.getResources().getDisplayMetrics().widthPixels;
			widthPixels = widthPixels - UIUtils.dip2Px(5) - UIUtils.dip2Px(5);
			int width = widthPixels / 3;// 屏幕的1/3
			int height = LayoutParams.WRAP_CONTENT;
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
			if (i != 0) {
				layoutParams.leftMargin = UIUtils.dip2Px(5);
			}
			// 把RatioLayout添加到mContainerPic
			mContainerPic.addView(ratioLayout, layoutParams);
		}
	}
}
