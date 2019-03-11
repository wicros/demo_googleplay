package com.itheima.googleplay_9.hodler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.bean.SubjectInfoBean;
import com.itheima.googleplay_9.conf.Constants.URLS;
import com.itheima.googleplay_9.utils.BitmapHelper;
import com.itheima.googleplay_9.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 下午2:39:53
 * @描述	     TODO
 *
 * @版本       $Rev: 27 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 15:41:42 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class SubjectHolder extends BaseHolder<SubjectInfoBean> {
	@ViewInject(R.id.item_subject_iv_icon)
	ImageView	mIvIcon;

	@ViewInject(R.id.item_subject_tv_title)
	TextView	mTvTitle;

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);
		// 注入
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	protected void rerefreshHolderView(SubjectInfoBean data) {
		mTvTitle.setText(data.des);

		BitmapHelper.display(mIvIcon, URLS.IMAGEBASEURL + data.url);
	}

}
