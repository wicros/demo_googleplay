package com.itheima.googleplay_9.factory;

import android.support.v4.util.SparseArrayCompat;

import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.fragment.AppFragment;
import com.itheima.googleplay_9.fragment.CategoryFragment;
import com.itheima.googleplay_9.fragment.GameFragment;
import com.itheima.googleplay_9.fragment.HomeFragment;
import com.itheima.googleplay_9.fragment.HotFragment;
import com.itheima.googleplay_9.fragment.RecommendFragment;
import com.itheima.googleplay_9.fragment.SubjectFragment;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午3:57:35
 * @描述	     fragment的工厂类
 *
 * @版本       $Rev: 14 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 11:28:31 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public class FragmentFactory {
	/**
	        <item>首页</item>
	    <item>应用</item>
	    <item>游戏</item>
	    <item>专题</item>
	    <item>推荐</item>
	    <item>分类</item>
	    <item>排行</item>
	 */
	public static final int							FRAGMENT_HOME		= 0;
	public static final int							FRAGMENT_APP		= 1;
	public static final int							FRAGMENT_GAME		= 2;
	public static final int							FRAGMENT_SUBJECT	= 3;
	public static final int							FRAGMENT_RECOMMEND	= 4;
	public static final int							FRAGMENT_CATEGORY	= 5;
	public static final int							FRAGMENT_HOT		= 6;

	// private static Map<Integer, BaseFragment> cacheFragmentMap = new HashMap<Integer, BaseFragment>();
	private static SparseArrayCompat<BaseFragment>	cacheFragments		= new SparseArrayCompat<BaseFragment>();

	public static BaseFragment getFragment(int position) {
		BaseFragment fragment = null;
		/*	if (cacheFragmentMap.containsKey(position)) {
				fragment = cacheFragmentMap.get(position);
				return fragment;
			}*/
		if (cacheFragments.get(position) != null) {
			fragment = cacheFragments.get(position);
			return fragment;
		}
		switch (position) {
		case FRAGMENT_HOME:
			fragment = new HomeFragment();
			break;
		case FRAGMENT_APP:
			fragment = new AppFragment();
			break;
		case FRAGMENT_GAME:
			fragment = new GameFragment();
			break;
		case FRAGMENT_SUBJECT:
			fragment = new SubjectFragment();
			break;
		case FRAGMENT_RECOMMEND:
			fragment = new RecommendFragment();
			break;
		case FRAGMENT_CATEGORY:
			fragment = new CategoryFragment();
			break;
		case FRAGMENT_HOT:
			fragment = new HotFragment();
			break;

		default:
			break;
		}
		// 进行保存
		cacheFragments.put(position, fragment);
		return fragment;
	}

}
