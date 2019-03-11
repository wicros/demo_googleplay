package com.itheima.googleplay_9.hodler;

import android.view.View;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.utils.UIUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-19 下午5:06:22
 * @描述	     TODO
 *
 * @版本       $Rev: 45 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 17:20:02 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class MenuHolder extends BaseHolder<Object> {

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.menu_view, null);
		return view;
	}

	@Override
	protected void rerefreshHolderView(Object data) {
		// TODO
		
	}

}
