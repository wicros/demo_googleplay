package com.itheima.googleplay_9;

import android.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.itheima.googleplay_9.base.BaseActivity;
import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.base.LoadingPager;
import com.itheima.googleplay_9.factory.FragmentFactory;
import com.itheima.googleplay_9.hodler.MenuHolder;
import com.itheima.googleplay_9.utils.LogUtils;
import com.itheima.googleplay_9.utils.UIUtils;

public class MainActivity extends BaseActivity {

	private DrawerLayout				mDrawerLayout;
	private ActionBarDrawerToggle		mToggle;
	private PagerSlidingTabStripExtends	mTabs;
	private ViewPager					mViewPager;
	private String[]					mMainTitles;
	private FrameLayout					mMain_leftmenu;

	/**初始化视图*/
	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
		mTabs = (PagerSlidingTabStripExtends) findViewById(R.id.main_tabs);
		mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
		mMain_leftmenu = (FrameLayout) findViewById(R.id.main_leftmenu);
	}

	private FrameLayout findViewById(int mainLeftmenu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initActionBar() {
		ActionBar actionBar = getSupportActionBar();

		actionBar.setTitle("GooglePlay");
		actionBar.setLogo(R.drawable.ic_launcher);

		// 显示访问按钮
		actionBar.setDisplayHomeAsUpEnabled(true);

		initActionBarDrawerToggle();

	}

	/**初始化数据*/
	@Override
	protected void initData() {
		mMainTitles = UIUtils.getStringArr(R.array.main_titles);

		// 设置viewpager的adapter
		// HomeAdapter adapter = new HomeAdapter();
		// HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
		HomeFragmentStatePagerAdapter adapter = new HomeFragmentStatePagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(adapter);

		// Bind the tabs to the ViewPager
		// viewpager和tabs需要绑定
		mTabs.setViewPager(mViewPager);

		MenuHolder menuHolder = new MenuHolder();
		mMain_leftmenu.addView(menuHolder.mHolderView);
		menuHolder.setDataAndRefreshHolderView(null);
	}

	@Override
	protected void initListener() {
		final OnPageChangeListener listener = new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 触发加载数据
				// loadingpager-->BaseFragment
				BaseFragment fragment = FragmentFactory.getFragment(position);
				LoadingPager loadingPager = fragment.getLoadingPager();
				if (loadingPager != null) {
					loadingPager.triggerLoadData();
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO

			}
		};
		mTabs.setOnPageChangeListener(listener);
		/*
		 也可以在PagerSlidingTabStripExtends里面的addOnGlobalLayoutListener这个里面去修改
		  mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// TODO
				listener.onPageSelected(0);
				mViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});*/
	}

	private void initActionBarDrawerToggle() {
		mToggle = new ActionBarDrawerToggle(this,//
				mDrawerLayout,//
				R.drawable.ic_drawer_am,//
				R.string.open,//
				R.string.close//
				);

		// 同步状态
		mToggle.syncState();

		// 设置drawerlayout的监听
		mDrawerLayout.setDrawerListener(mToggle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// 点击返回按钮的时候可以展开/收起对应的drawerlayout
			mToggle.onOptionsItemSelected(item);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class HomeAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if (mMainTitles != null) {
				return mMainTitles.length;
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TextView tv = new TextView(UIUtils.getContext());
			tv.setText(mMainTitles[position]);
			container.addView(tv);
			return tv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO
			container.removeView((View) object);
		}

		/**需要去覆写getPageTitle方法*/
		@Override
		public CharSequence getPageTitle(int position) {

			return mMainTitles[position];
		}
	}

	class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

		public HomeFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			LogUtils.sf("初始化 " + mMainTitles[position]);
			return FragmentFactory.getFragment(position);
		}

		@Override
		public int getCount() {
			if (mMainTitles != null) {
				return mMainTitles.length;
			}
			return 0;
		}

		/**需要去覆写getPageTitle方法*/
		@Override
		public CharSequence getPageTitle(int position) {

			return mMainTitles[position];
		}

	}

	class HomeFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

		public HomeFragmentStatePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			LogUtils.sf("初始化 " + mMainTitles[position]);
			return FragmentFactory.getFragment(position);
		}

		@Override
		public int getCount() {
			// TODO
			if (mMainTitles != null) {
				return mMainTitles.length;
			}
			return 0;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO
			return mMainTitles[position];
		}

	}
}
