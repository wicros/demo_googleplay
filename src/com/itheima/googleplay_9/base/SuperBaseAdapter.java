package com.itheima.googleplay_9.base;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.itheima.googleplay_9.conf.Constants;
import com.itheima.googleplay_9.factory.ThreadFactory;
import com.itheima.googleplay_9.hodler.LoadMoreHolder;
import com.itheima.googleplay_9.utils.LogUtils;
import com.itheima.googleplay_9.utils.UIUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 上午10:15:16
 * @描述	     TODO
 *
 * @版本       $Rev: 33 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 11:29:06 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public abstract class SuperBaseAdapter<ITEMBEANTYPE> extends BaseAdapter implements OnItemClickListener {
	private static final int	VIEWTYPE_LOADMORE	= 0;								//
	private static final int	VIEWTYPE_NORMAL		= 1;								//
	public List<ITEMBEANTYPE>	mDataSource			= new ArrayList<ITEMBEANTYPE>();
	private LoadMoreHolder		mLoadMoreHolder;
	private LoadMoreTask		mLoadMoreTask;
	private AbsListView			mAbsListView;

	public SuperBaseAdapter(AbsListView absListView, List<ITEMBEANTYPE> datas) {
		super();
		absListView.setOnItemClickListener(this);
		mAbsListView = absListView;
		mDataSource = datas;
	}

	@Override
	public int getCount() {
		if (mDataSource != null) {
			return mDataSource.size() + 1;// 加上了加载更多
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mDataSource != null) {
			return mDataSource.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO
		return position;
	}

	/*--------------- listview中显示几种布局 begin ---------------*/
	// listview中viewType的总数-->一共有多少种类型
	@Override
	public int getViewTypeCount() {
		// TODO
		return super.getViewTypeCount() + 1;// 默认是1,2(加上了加载更多的类型)
	}

	// 得到每一个item对应的viewType
	// Integers must be in the range 0 to getViewTypeCount - 1
	@Override
	public int getItemViewType(int position) {

		if (position == getCount() - 1) {// 如果滑到底的时候-->加载更多
			return VIEWTYPE_LOADMORE;// 0
		}
		return getNormalItemType(position);
	}

	/**
	 * @des 定义成一个方法.我们子类就可以覆写此方法,修改返回值
	 * @return
	 */
	protected int getNormalItemType(int position) {
		return VIEWTYPE_NORMAL;// 1
	}

	/*--------------- listview中显示几种布局 end ---------------*/

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*--------------- 视图部分,决定根视图 ---------------*/
		BaseHolder<ITEMBEANTYPE> baseHolder = null;
		if (convertView == null) {
			// System.out.println("-----------getView-----------");

			if (getItemViewType(position) == VIEWTYPE_LOADMORE) {// 显示加载更多的holder
				baseHolder = (BaseHolder<ITEMBEANTYPE>) getLoadMoreHolder();// 在成员变量里面肯定有一个loadMore
			} else {// 其他holder
				baseHolder = getSpecialHolder(position);
			}

		} else {
			baseHolder = (BaseHolder) convertView.getTag();
		}
		/*--------------- 数据展示部分 ---------------*/
		if (getItemViewType(position) == VIEWTYPE_LOADMORE) {// 显示加载更多的holder
			// 希望去加载更多的数据-->触发加载数据
			// 如果有加载更多
			if (hasLoadMore()) {// 有加载更多
				performLoadMore();
			} else {// 没有加载更多
				mLoadMoreHolder.setDataAndRefreshHolderView(LoadMoreHolder.STATE_EMPTY);
			}
		} else {// 其他holder
			ITEMBEANTYPE data = mDataSource.get(position);
			baseHolder.setDataAndRefreshHolderView(data);
		}
		return baseHolder.mHolderView;
	}

	/*--------------- 加载更多的逻辑 begin ---------------*/
	/**
	 * @des 判断是否有加载更多,默认有加载更多
	 * @call 如果子类没有加载更多,可以通过覆写该方法,修改行为
	 */
	protected boolean hasLoadMore() {
		return true;
	}

	/**
	 * 触发加载更多的数据
	 */
	private void performLoadMore() {
		if (mLoadMoreTask == null) {
			LogUtils.sf("###开始加载更多");
			// 加载之前这个状态修改为加载中
			int state = LoadMoreHolder.STATE_LOADING;
			mLoadMoreHolder.setDataAndRefreshHolderView(state);
			mLoadMoreTask = new LoadMoreTask();
			ThreadFactory.getNormalPool().execute(mLoadMoreTask);
		}
	}

	class LoadMoreTask implements Runnable {
		@Override
		public void run() {
			// 正在的去加载数据-->
			/*--------------- 更加加载更多的数据,处理LoadMoreHolder的一个状态  begin---------------*/
			int state = LoadMoreHolder.STATE_LOADING;
			List<ITEMBEANTYPE> loadMoreData = null;
			try {
				loadMoreData = onLoadMore();
				if (loadMoreData == null) {// 没有加载更多
					state = LoadMoreHolder.STATE_EMPTY;
				} else {
					if (loadMoreData.size() < Constants.PAGERSIZE) {// 10<20
						state = LoadMoreHolder.STATE_EMPTY;// 没有加载更多
					} else {// 还有可能有数据
						state = LoadMoreHolder.STATE_LOADING;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				state = LoadMoreHolder.STATE_RETRY;
			}
			/*--------------- 更加加载更多的数据,处理LoadMoreHolder的一个状态  end---------------*/

			/*--------------- 定义临时变量  begin ---------------*/
			final int tempState = state;
			final List<ITEMBEANTYPE> tempLoadMoreData = loadMoreData;
			/*--------------- 定义临时变量  end ---------------*/
			UIUtils.postTaskSafely(new Runnable() {
				@Override
				public void run() {
					// 会去刷新loadmore视图-->
					mLoadMoreHolder.setDataAndRefreshHolderView(tempState);
					// 会去刷新adapter
					// 修改数据源
					if (tempLoadMoreData != null && tempLoadMoreData.size() != 0) {
						mDataSource.addAll(tempLoadMoreData);
						notifyDataSetChanged();
					}
				}
			});

			// 置空mLoadMoreTask
			mLoadMoreTask = null;
		}
	}

	private LoadMoreHolder getLoadMoreHolder() {// 有且只有一个loadmore
		if (mLoadMoreHolder == null) {
			mLoadMoreHolder = new LoadMoreHolder();
		}
		return mLoadMoreHolder;
	}

	/**
	 * @des 正在的去加载更多的数据,不是必须实现,定义成空方法
	 * @call 如果子类有加载更多 时候,覆写该方法,修改行为
	 * @return
	 */
	public List<ITEMBEANTYPE> onLoadMore() throws Exception {
		return null;
	};

	/*--------------- 加载更多的逻辑 end ---------------*/
	/**
	 * @param position 
	 * @des 返回BaseHolder的子类,必须实现,但是不知道具体实现,定义成抽象方法,让子类实现
	 * @call convertView == null,也就是需要创建holder时候
	 */
	protected abstract BaseHolder<ITEMBEANTYPE> getSpecialHolder(int position);

	/*--------------- 处理item的点击事件 ---------------*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 如果是listview,position需要做处理
		if (mAbsListView instanceof ListView) {
			ListView listView = (ListView) mAbsListView;
			position = position - listView.getHeaderViewsCount();
		}

		if (getItemViewType(position) == VIEWTYPE_LOADMORE) {
			// 触发重新加载数据
			performLoadMore();
		} else {
			onNormalItemClick(parent, view, position, id);
		}
	}

	/**
	 * @des 点击普通的itemView,空方法
	 * @call 如果子类需要处理itemView的点击事件的时候,覆写该方法,修改行为
	 */
	public void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {

	}
}
