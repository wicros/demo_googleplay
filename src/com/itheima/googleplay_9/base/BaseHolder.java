package com.itheima.googleplay_9.base;

import android.view.View;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 上午10:58:11
 * @描述	     TODO
 *
 * @版本       $Rev: 15 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 14:45:59 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public abstract class BaseHolder<HOLDERBEANTYPE> {

	public View				mHolderView;	// 根布局
	private HOLDERBEANTYPE	mData;

	public BaseHolder() {
		// 2.决定根布局
		mHolderView = initHolderView();
		// 4.绑定tag
		mHolderView.setTag(this);
	}

	/**
	 * @des 保存数据,刷新itemView
	 */
	public void setDataAndRefreshHolderView(HOLDERBEANTYPE data) {
		mData = data;
		rerefreshHolderView(data);
	}

	/**
	 * @return 
	 * @des 初始化根布局,必须实现,但是不知道具体实现,定义成抽象方法,交给子类具体实现
	 * @call BaesHolder具体子类被初始化的时候被调用
	 */
	protected abstract View initHolderView();

	/**
	 * des 刷新itemView,必须实现,但是不知道具体实现,定义成抽象方法,交给子类具体实现
	 */
	protected abstract void rerefreshHolderView(HOLDERBEANTYPE data);

}
