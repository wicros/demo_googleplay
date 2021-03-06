package com.itheima.googleplay_9.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午4:19:59
 * @描述	     TODO
 *
 * @版本       $Rev: 8 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-14 17:44:25 +0800 (星期五, 14 八月 2015) $
 * @更新描述    TODO
 */
public abstract class BaseFragmentCommon extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		init();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return initView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initData();
		initListener();
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * @des 选择性实现,如果需要实现,对应覆写此方法就可以了
	 */
	public void init() {

	}

	/**
	 * @des 选择性实现,如果需要实现,对应覆写此方法就可以了
	 */
	public void initListener() {

	}

	/**
	 * @des 选择性实现,如果需要实现,对应覆写此方法就可以了
	 */
	public void initData() {

	}

	/**
	 * @des 必须实现,但是不知道具体实现,交给子类实现
	 * 
	 */
	protected abstract View initView();

}
