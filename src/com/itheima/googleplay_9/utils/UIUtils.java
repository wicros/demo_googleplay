package com.itheima.googleplay_9.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.itheima.googleplay_9.base.BaseApplication;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午2:27:07
 * @描述	     和ui相关的一些静态工具方法
 *
 * @版本       $Rev: 36 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 15:32:51 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class UIUtils {
	/**得到上下文*/
	public static Context getContext() {
		return BaseApplication.getContext();
	}

	/**得到resouce对象*/
	public static Resources getResources() {
		return getContext().getResources();
	}

	// string.xml-->string-->arr
	/**得到string.xml中的一个字符串*/
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/**得到string.xml中的一个字符串,带占位符情况*/
	public static String getString(int resId, Object... formatArgs) {
		return getResources().getString(resId, formatArgs);
	}

	/**得到string.xml中的一个字符串数组*/
	public static String[] getStringArr(int resId) {
		return getResources().getStringArray(resId);
	}

	/**得到color.xml中的颜色值*/
	public static int getColor(int colorId) {
		return getResources().getColor(colorId);
	}

	/**得到应用程序的包名*/
	public static String getPackageName() {
		return getContext().getPackageName();
	}

	/**得到主线程id*/
	public static long getMainThreadId() {
		return BaseApplication.getMainThreadId();
	}

	/**得到一个主线程的handler*/
	public static Handler getMainThreadHandler() {
		return BaseApplication.getHandler();
	}

	/**安全的执行一个task*/
	public static void postTaskSafely(Runnable task) {
		long curThreadId = android.os.Process.myTid();
		long mainThreadId = getMainThreadId();
		// 如果当前线程是主线程
		if (curThreadId == mainThreadId) {
			task.run();
		} else {// 如果当前线程不是主线程
			getMainThreadHandler().post(task);
		}
	}

	/**在主线程执行一个延时的task*/
	public static void postTaskDelay(Runnable task, long delayMillis) {
		getMainThreadHandler().postDelayed(task, delayMillis);
	}

	/**移除主线程里面的一个task*/
	public static void removeTask(Runnable task) {
		getMainThreadHandler().removeCallbacks(task);
	}

	/**
	 * dp-->px
	 * @param dp
	 * @return
	 */
	public static int dip2Px(int dp) {
		// px/dp = density;
		float density = getResources().getDisplayMetrics().density;
		System.out.println("density:" + density);
		int px = (int) (dp * density + .5f);
		return px;
	}

	/**
	 * px-->dp
	 * @param dp
	 * @return
	 */
	public static int px2Dp(int px) {
		// px/dp = density;
		float density = getResources().getDisplayMetrics().density;
		System.out.println("density:" + density);
		int dp = (int) (px / density + .5f);
		return px;
	}

}
