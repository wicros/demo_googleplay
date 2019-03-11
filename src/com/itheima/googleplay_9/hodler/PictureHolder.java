package com.itheima.googleplay_9.hodler;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.conf.Constants.URLS;
import com.itheima.googleplay_9.utils.BitmapHelper;
import com.itheima.googleplay_9.utils.UIUtils;
import com.itheima.googleplay_9.views.InnerViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 上午10:14:35
 * @描述	     TODO
 *
 * @版本       $Rev: 46 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 17:20:45 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class PictureHolder extends BaseHolder<List<String>> {
	@ViewInject(R.id.item_home_picture_pager)
	InnerViewPager			mViewPager;

	@ViewInject(R.id.item_home_picture_container_indicator)
	LinearLayout			mContainerIndicator;

	private List<String>	mPicturesUrl;

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_home_picture, null);
		// 初始化布局里面对象
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	protected void rerefreshHolderView(List<String> picturesUrl) {
		// 保存数据
		mPicturesUrl = picturesUrl;

		// 设置adapter
		mViewPager.setAdapter(new PictureAdapter());

		// 设置效果
//		mViewPager.setTransitionEffect(TransitionEffect.CubeIn);

		for (int i = 0; i < picturesUrl.size(); i++) {
			View indicatorView = new View(UIUtils.getContext());

			indicatorView.setBackgroundResource(R.drawable.indicator_normal);
			LinearLayout.LayoutParams pramas = new LinearLayout.LayoutParams(UIUtils.dip2Px(6), UIUtils.dip2Px(6));
			pramas.leftMargin = UIUtils.dip2Px(5);
			pramas.bottomMargin = UIUtils.dip2Px(5);
			// 添加小点
			mContainerIndicator.addView(indicatorView, pramas);

			// 设置默认选中效果
			if (i == 0) {
				indicatorView.setBackgroundResource(R.drawable.indicator_selected);
			}
		}

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				position = position % mPicturesUrl.size();
				for (int i = 0; i < mPicturesUrl.size(); i++) {
					// 取出一个indicatorView
					View indicatorView = mContainerIndicator.getChildAt(i);
					// 还原背景
					indicatorView.setBackgroundResource(R.drawable.indicator_normal);
				}
				// 设置当前
				mContainerIndicator.getChildAt(position).setBackgroundResource(R.drawable.indicator_selected);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO

			}
		});
		// 让它可以第一次的时候往左边滑动
		int item = Integer.MAX_VALUE / 2;
		int diff = item % mPicturesUrl.size();
		mViewPager.setCurrentItem(item - diff);

		// 自动轮播
		final AutoScrollTask autoScrollTask = new AutoScrollTask();
		autoScrollTask.start();

		// 按住的时候停止自动滚动
		mViewPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					autoScrollTask.stop();
					break;
				case MotionEvent.ACTION_MOVE:

					break;
				case MotionEvent.ACTION_UP:
					autoScrollTask.start();
					break;

				default:
					break;
				}
				return false;
			}
		});
	}

	class AutoScrollTask implements Runnable {
		/**开始轮播*/
		public void start() {
			UIUtils.postTaskDelay(this, 2000);
		}

		/**停止轮播*/
		public void stop() {
			UIUtils.removeTask(this);
		}

		@Override
		public void run() {
			int preItem = mViewPager.getCurrentItem();
			preItem++;
			mViewPager.setCurrentItem(preItem);
			// 执行完成之后再次启动
			start();
		}
	}

	class PictureAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if (mPicturesUrl != null) {
				// return mPicturesUrl.size();
				return Integer.MAX_VALUE;
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position = position % mPicturesUrl.size();
			ImageView iv = new ImageView(UIUtils.getContext());
			iv.setImageResource(R.drawable.ic_default);
			// 得到数据
			String url = mPicturesUrl.get(position);
			// 加载图片
			String picUrl = URLS.IMAGEBASEURL + url;
			BitmapHelper.display(iv, picUrl);
			// 加到viewpager中
			// 设置scaleType
			iv.setScaleType(ScaleType.FIT_XY);
			container.addView(iv);
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
