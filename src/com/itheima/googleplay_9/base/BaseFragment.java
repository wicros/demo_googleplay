package com.itheima.googleplay_9.base;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.utils.UIUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午4:25:36
 * @描述	     TODO
 *
 * @版本       $Rev: 27 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 15:41:42 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public abstract class BaseFragment extends Fragment {

	private LoadingPager	mLoadingPager;

	public LoadingPager getLoadingPager() {
		return mLoadingPager;
	}

	@Override
	public boolean getUserVisibleHint() {// 类似我们fragment里面的onresume
		/*	 
			mLoadingPager.triggerLoadData();
			*/
		return super.getUserVisibleHint();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mLoadingPager == null) {// 第一次
			mLoadingPager = new LoadingPager(UIUtils.getContext()) {
				@Override
				protected LoadedResult initData() {
					return BaseFragment.this.initData();
				}

				@Override
				protected View initSuccessView() {
					return BaseFragment.this.initSuccessView();
				}
			};
		} else {// 第2次
			ViewParent parent = mLoadingPager.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(mLoadingPager);
			}
		}

		// 盒子里面的一个BaseFragment取出来-->取出里面的mLoadingPager-->返回给viewPager-->adapter-->Fragment
		return mLoadingPager;// -->view-->ViewGroup
	}

	// 页面显示分析
	// Fragment/Activity共性-->页面共性-->视图的展示
	/**
	 任何应用其实就只有4种页面类型-->常规的页面
	 ① 加载页面
	 ② 错误页面
	 ③ 空页面 		
	 		
	 ④ 成功页面 	
		①②③三种页面一个应用基本是固定的
		每一个fragment/activity对应的页面④就不一样
		进入应用的时候显示①,②③④需要加载数据之后才知道显示哪个		
	*/

	// 数据加载的流程
	/**
	① 触发加载  	进入页面开始加载/点击某一个按钮的时候加载
	② 异步加载数据  -->显示加载视图
	③ 处理加载结果
		① 成功-->显示成功视图
		② 失败
			① 数据为空-->显示空视图
			② 数据加载失败-->显示加载失败的视图
	*/
	/**
	 * @des 真正子子线程中加载数据,必须实现,但是不知道具体实现,交给子类去实现
	 * @des 它是和LoadingPager中的initData同名方法
	 * @call triggerLoadData方法被调用的时候调用
	 */
	protected abstract LoadedResult initData();

	/**
	 * @des 数据加载成功的时候返回具体的成功视图,必须实现,但是不知道具体实现,交给子类实现
	 * @des 它是和LoadingPager中的initSuccessView同名方法
	 * @call 数据加载成功的时候
	 */
	protected abstract View initSuccessView();

	/**
	 * 检测加载网络之后返回数据对应的状态
	 * @param obj
	 * @return
	 */
	public LoadedResult checkState(Object obj) {
		if (obj == null) {
			return LoadedResult.EMPTY;
		}
		// list
		if (obj instanceof List) {
			if (((List) obj).size() == 0) {
				return LoadedResult.EMPTY;
			}
		}
		// map
		if (obj instanceof Map) {
			if (((Map) obj).size() == 0) {
				return LoadedResult.EMPTY;
			}
		}

		return LoadedResult.SUCCESS;
	}
}
