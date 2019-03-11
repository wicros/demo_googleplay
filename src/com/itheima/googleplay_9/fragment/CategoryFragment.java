package com.itheima.googleplay_9.fragment;

import java.util.List;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.base.SuperBaseAdapter;
import com.itheima.googleplay_9.bean.CategoryBean;
import com.itheima.googleplay_9.factory.ListViewFactory;
import com.itheima.googleplay_9.hodler.CategoryInfoHolder;
import com.itheima.googleplay_9.hodler.CategoryTitleHolder;
import com.itheima.googleplay_9.protocol.CategoryProtocol;
import com.itheima.googleplay_9.utils.LogUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午4:00:42
 * @描述	     TODO
 *
 * @版本       $Rev: 32 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 10:57:03 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class CategoryFragment extends BaseFragment {

	private List<CategoryBean>	mDatas;

	@Override
	protected LoadedResult initData() {// 真正子子线程中
		CategoryProtocol protocol = new CategoryProtocol();
		try {
			mDatas = protocol.loadData(0);
			LogUtils.printList(mDatas);
			return checkState(mDatas);
		} catch (Exception e) {
			e.printStackTrace();
			return LoadedResult.ERROR;
		}
	}

	@Override
	protected View initSuccessView() {
		ListView listView = ListViewFactory.createListView();
		listView.setAdapter(new CategoryAdapter(listView, mDatas));
		return listView;
	}

	class CategoryAdapter extends SuperBaseAdapter<CategoryBean> {

		public CategoryAdapter(AbsListView absListView, List<CategoryBean> datas) {
			super(absListView, datas);
		}

		@Override
		protected BaseHolder<CategoryBean> getSpecialHolder(int position) {
			CategoryBean categoryBean = mDatas.get(position);
			//getItemViewType(positon)
			if (categoryBean.isTitle) {
				return new CategoryTitleHolder();
			} else {
				return new CategoryInfoHolder();
			}
		}

		// 因为现在实际有3种ViewType,但是我们的SuperBaseAdapter中getViewTypeCount还是返回的2种
		// 所以会出现混乱
		// 还需要覆写SuperBaseAdapter里面的getViewTypeCount

		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount() + 1;// 2+1=3-->正好就是我们实际有的viewType的count值
		}

		// Integers must be in the range 0 to getViewTypeCount - 1
		@Override
		protected int getNormalItemType(int position) {// 0 1 2
			CategoryBean categoryBean = mDatas.get(position);
			if (categoryBean.isTitle) {
				return 2;
			} else {
				return 1;
			}
		}
		@Override
		protected boolean hasLoadMore() {
			// TODO
			return false;
		}
	}

}
