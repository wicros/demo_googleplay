package com.itheima.googleplay_9.base;

import java.util.LinkedList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.itheima.googleplay_9.MainActivity;
import com.itheima.googleplay_9.utils.SPUtil;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-19 上午8:45:57
 * @描述	     TODO
 *
 * @版本       $Rev: 38 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 09:07:19 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public abstract class BaseActivity extends ActionBarActivity {

	// 放置共有的方法
	// 1.activity完全退出
	// 2.退出提示
	// 3.返回当前的activity
	public static LinkedList<BaseActivity>	mAllActivitys	= new LinkedList<BaseActivity>();
	private long							mPreClickTime;
	public BaseActivity						mCurActivity;
	// 放置共有的属性
	//操作sp的一个工具类
	public SPUtil spUtil;
	
	
	public BaseActivity getCurActivity() {
		return mCurActivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		spUtil = new SPUtil(this);
		init();
		initView();
		initActionBar();
		initData();
		initListener();
		mAllActivitys.add(this);
	}

	@Override
	protected void onResume() {
		mCurActivity = this;
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mAllActivitys.remove(this);
		super.onDestroy();
	}

	protected void init() {

	}

	protected abstract void initView();

	protected void initActionBar() {
		// TODO

	}

	protected void initData() {
		// TODO

	}

	protected void initListener() {
		// TODO

	}

	/**
	 * 完成退出
	 */
	public void exit() {
		for (BaseActivity activity : mAllActivitys) {
			activity.finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.out.println(" 按了返回按钮");
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		if (this instanceof MainActivity) {
			// 拿着当前的时间戳--记录的时间搓 如何间隔2s以上,我就弹出toast提示
			if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次点击的时间间隔>2s
				Toast.makeText(getApplicationContext(), "再按一次,退出谷歌市场", 0).show();
				mPreClickTime = System.currentTimeMillis();
				return;
			} else {
				// 两次点击的时间间隔<2s
				// 完成退出
				exit();
			}
		}
		// 如果不是主页,点击就是finish效果
		super.onBackPressed();
	}
}
