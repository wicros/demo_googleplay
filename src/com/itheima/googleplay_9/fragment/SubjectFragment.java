package com.itheima.googleplay_9.fragment;

import java.util.List;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.base.SuperBaseAdapter;
import com.itheima.googleplay_9.bean.SubjectInfoBean;
import com.itheima.googleplay_9.factory.ListViewFactory;
import com.itheima.googleplay_9.hodler.SubjectHolder;
import com.itheima.googleplay_9.protocol.SubjectProtocol;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午4:00:42
 * @描述	     TODO
 *
 * @版本       $Rev: 30 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 09:08:28 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class SubjectFragment extends BaseFragment {

	private List<SubjectInfoBean>	mDatas;
	private SubjectProtocol			mProtocol;

	@Override
	protected LoadedResult initData() {// 真正子子线程中
		mProtocol = new SubjectProtocol();
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
		// 设置adapter
		listView.setAdapter(new SubjectAdapter(listView, mDatas));
		return listView;
	}

	class SubjectAdapter extends SuperBaseAdapter<SubjectInfoBean> {

		public SubjectAdapter(AbsListView absListView, List<SubjectInfoBean> datas) {
			super(absListView, datas);
		}

		@Override
		protected BaseHolder<SubjectInfoBean> getSpecialHolder(int position) {
			return new SubjectHolder();
		}

		@Override
		public List<SubjectInfoBean> onLoadMore() throws Exception {
			// TODO
			return mProtocol.loadData(mDatas.size());
		}

	}
}