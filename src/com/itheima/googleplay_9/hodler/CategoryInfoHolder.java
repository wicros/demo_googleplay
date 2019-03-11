package com.itheima.googleplay_9.hodler;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.bean.CategoryBean;
import com.itheima.googleplay_9.conf.Constants.URLS;
import com.itheima.googleplay_9.utils.BitmapHelper;
import com.itheima.googleplay_9.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午9:05:20
 * @描述	     TODO
 *
 * @版本       $Rev: 31 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 09:52:29 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class CategoryInfoHolder extends BaseHolder<CategoryBean> {
	@ViewInject(R.id.item_category_icon_1)
	ImageView		mIvIcon1;

	@ViewInject(R.id.item_category_icon_2)
	ImageView		mIvIcon2;

	@ViewInject(R.id.item_category_icon_3)
	ImageView		mIvIcon3;

	@ViewInject(R.id.item_category_item_1)
	LinearLayout	mContainerItem1;

	@ViewInject(R.id.item_category_item_2)
	LinearLayout	mContainerItem2;

	@ViewInject(R.id.item_category_item_3)
	LinearLayout	mContainerItem3;

	@ViewInject(R.id.item_category_name_1)
	TextView		mTvName1;

	@ViewInject(R.id.item_category_name_2)
	TextView		mTvName2;

	@ViewInject(R.id.item_category_name_3)
	TextView		mTvName3;

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_category_info, null);
		// 注入
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	protected void rerefreshHolderView(CategoryBean data) {//

		setData(data.name1, data.url1, mTvName1, mIvIcon1);
		setData(data.name2, data.url2, mTvName2, mIvIcon2);
		setData(data.name3, data.url3, mTvName3, mIvIcon3);

		/*mTvName2.setText(data.name2);
		mTvName3.setText(data.name3);

		mIvIcon2.setImageResource(R.drawable.ic_default);
		BitmapHelper.display(mIvIcon2, URLS.IMAGEBASEURL + data.url2);

		mIvIcon3.setImageResource(R.drawable.ic_default);
		BitmapHelper.display(mIvIcon3, URLS.IMAGEBASEURL + data.url3);
		*/
	}

	private void setData(final String name, String url, TextView tvName, ImageView ivIcon) {
		if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(url)) {
			tvName.setText(name);
			ivIcon.setImageResource(R.drawable.ic_default);
			BitmapHelper.display(ivIcon, URLS.IMAGEBASEURL + url);
			((ViewGroup) tvName.getParent()).setVisibility(View.VISIBLE);

			// 点击事件
			((ViewGroup) tvName.getParent()).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO
					Toast.makeText(UIUtils.getContext(), name, 0).show();
				}
			});
		} else {
			((ViewGroup) tvName.getParent()).setVisibility(View.INVISIBLE);
		}
	}

}
