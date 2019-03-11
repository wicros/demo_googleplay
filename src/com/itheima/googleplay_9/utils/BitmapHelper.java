package com.itheima.googleplay_9.utils;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 下午3:00:57
 * @描述	     TODO
 *
 * @版本       $Rev: 16 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 15:36:10 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public class BitmapHelper {
	static BitmapUtils	bitmapUtils;
	// static BitmapUtils bitmapUtils = new BitmapUtils(UIUtils.getContext());
	static {
		bitmapUtils = new BitmapUtils(UIUtils.getContext());
	}

	public static <T extends View> void display(T container, String uri) {
		bitmapUtils.display(container, uri);
	}
}
