package com.itheima.googleplay_9.fragment;

import java.util.List;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.itheima.googleplay_9.adapter.AppItemAdapter;
import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.factory.ListViewFactory;
import com.itheima.googleplay_9.hodler.AppItemHolder;
import com.itheima.googleplay_9.manager.DownLoadManager;
import com.itheima.googleplay_9.protocol.GameProtocol;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午4:00:42
 * @描述	     TODO
 *
 * @版本       $Rev: 45 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 17:20:02 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class GameFragment extends BaseFragment {

	private List<AppInfoBean>	mDatas;
	private GameProtocol		mProtocol;
	private GameAdpater			mAdapter;

	@Override
	protected LoadedResult initData() {// 真正子子线程中
		mProtocol = new GameProtocol();
		try {
			mDatas = mProtocol.loadData(0);
			return checkState(mDatas);
		} catch (Exception e) {
			e.printStackTrace();
			return LoadedResult.ERROR;
		}
	}

	@Override
	protected View initSuccessView() {
		ListView listView = ListViewFactory.createListView();
		mAdapter = new GameAdpater(listView, mDatas);
		listView.setAdapter(mAdapter);
		return listView;
	}

	class GameAdpater extends AppItemAdapter {

		public GameAdpater(AbsListView absListView, List<AppInfoBean> datas) {
			super(absListView, datas);
		}

		@Override
		public List<AppInfoBean> onLoadMore() throws Exception {
			return mProtocol.loadData(mDatas.size());
		}

	}

	@Override
	public void onPause() {
		// HomeFragment
		if (mAdapter != null) {
			List<AppItemHolder> appItemHolders = mAdapter.getAppItemHolders();
			// 遍历集合,移除观察者
			for (AppItemHolder appItemHolder : appItemHolders) {
				DownLoadManager.getInstance().deleteObserver(appItemHolder);

			}
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		if (mAdapter != null) {
			List<AppItemHolder> appItemHolders = mAdapter.getAppItemHolders();
			// 遍历集合,移除观察者
			for (AppItemHolder appItemHolder : appItemHolders) {
				DownLoadManager.getInstance().addObserver(appItemHolder);
			}
			// 手动刷新
			mAdapter.notifyDataSetChanged();// getview-->
		}
		super.onResume();
	}
}
