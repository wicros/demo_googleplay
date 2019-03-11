package com.itheima.googleplay_9.factory;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

import com.itheima.googleplay_9.utils.UIUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 上午9:15:47
 * @描述	     TODO
 *
 * @版本       $Rev: 33 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 11:29:06 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class ListViewFactory {
	public static ListView createListView() {
		ListView listView = new ListView(UIUtils.getContext());
		// 简单设置
//		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setFastScrollEnabled(true);
		// item点击的时候没有具体的颜色变化
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		return listView;
	}
}
