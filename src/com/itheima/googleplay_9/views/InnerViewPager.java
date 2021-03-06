package com.itheima.googleplay_9.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 上午11:17:25
 * @描述	     TODO
 *
 * @版本       $Rev: 26 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 11:40:01 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class InnerViewPager extends ViewPager {

	private float	mDownX;
	private float	mDownY;
	private float	mMoveX;
	private float	mMoveY;

	public InnerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InnerViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 左右滑动-->自己处理事件-->请求父亲不拦截
		// 上下滑动-->父亲处理事件-->请求父亲拦截
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = ev.getRawX();
			mDownY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			mMoveX = ev.getRawX();
			mMoveY = ev.getRawY();
			int diffX = (int) (mMoveX - mDownX);
			int diffY = (int) (mMoveY - mDownY);
			// getParent(父亲)().request(请求)Disallow(不)Intercept(拦截)TouchEvent(touch事件)(true(同意));
			if (Math.abs(diffX) > Math.abs(diffY)) {// 左右滑动-->自己处理事件-->请求父亲不拦截
				getParent().requestDisallowInterceptTouchEvent(true);
			} else {// 上下滑动-->父亲处理事件-->请求父亲拦截
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

}
