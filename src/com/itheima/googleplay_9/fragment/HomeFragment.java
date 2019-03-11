package com.itheima.googleplay_9.fragment;

import java.util.List;

import android.os.SystemClock;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.itheima.googleplay_9.adapter.AppItemAdapter;
import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.bean.HomeBean;
import com.itheima.googleplay_9.factory.ListViewFactory;
import com.itheima.googleplay_9.hodler.AppItemHolder;
import com.itheima.googleplay_9.hodler.PictureHolder;
import com.itheima.googleplay_9.manager.DownLoadManager;
import com.itheima.googleplay_9.protocol.HomeProtocol;

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
public class HomeFragment extends BaseFragment {

	private List<AppInfoBean>	mDatas;
	private List<String>		mPictures;
	private HomeProtocol		mProtocol;
	private HomeAdapter			mHomeAdapter;

	@Override
	protected LoadedResult initData() {// 真正子线程中
		mProtocol = new HomeProtocol();
		try {
			HomeBean homeBean = mProtocol.loadData(0);
			LoadedResult state = checkState(homeBean);
			if (state != LoadedResult.SUCCESS) {// 有问题
				return state;
			}
			// 检查home.list
			state = checkState(homeBean.list);

			if (state != LoadedResult.SUCCESS) {// 有问题
				return state;
			}
			// 走到这里.说明没有问题
			mDatas = homeBean.list;
			mPictures = homeBean.picture;

			return LoadedResult.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return LoadedResult.ERROR;
		}
	}

	@Override
	protected View initSuccessView() {
		/*ListView listView = new ListView(UIUtils.getContext());
		// 简单设置
		listView.setCacheColorHint(Color.TRANSPARENT);
		listView.setFastScrollEnabled(true);*/
		ListView listView = ListViewFactory.createListView();

		// 给listView添加一个headerView
		PictureHolder pictureHolder = new PictureHolder();
		listView.addHeaderView(pictureHolder.mHolderView);
		// 触发加载数据
		pictureHolder.setDataAndRefreshHolderView(mPictures);

		mHomeAdapter = new HomeAdapter(listView, mDatas);
		listView.setAdapter(mHomeAdapter);
		return listView;
	}

	class HomeAdapter extends AppItemAdapter {
		public HomeAdapter(AbsListView absListView, List<AppInfoBean> datas) {
			super(absListView, datas);
		}

		@Override
		public List<AppInfoBean> onLoadMore() throws Exception {
			SystemClock.sleep(1000);
			List<AppInfoBean> loadMoreData = getLoadMoreHolder();
			return loadMoreData;
		}

		/*@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			--------------- 视图部分,决定根视图 ---------------
			HomeHolder homeHolder = null;
			if (convertView == null) {
				// 1.创建了一个holder对象
				homeHolder = new HomeHolder();
						// 2.决定根布局
						convertView = View.inflate(UIUtils.getContext(), R.layout.item_temp, null);
						// 3.初始化孩子对象
						holder.tvTemp1 = (TextView) convertView.findViewById(R.id.tmp_tv_1);
						holder.tvTemp2 = (TextView) convertView.findViewById(R.id.tmp_tv_2);
						// 4.绑定tag
						convertView.setTag(holder);// 就是convertView去找了一个holder
				
			} else {
				// 5.从convert身上拿回holder
				homeHolder = (HomeHolder) convertView.getTag();
			}
			--------------- 数据展示部分 ---------------
			// 得到数据
			String data = mDatas.get(position);
			// 展示数据
			homeHolder.setDataAndRefreshHolderView(data);
			homeHolder.tvTemp1.setText("我是头-" + data);
			homeHolder.tvTemp2.setText("我是尾巴-" + data);
			return homeHolder.mHolderView;
		}*/

		/**
		 * 1.普通的类
		 * 2.条件-->成员变量里面需要持有根布局里面对应的 孩子对象
		 * 3.条件-->持有根布局就可以(convertView)
		 */
		/*class ViewHolder {
			TextView	tvTemp1;
			TextView	tvTemp2;
		}*/

	}

	private List<AppInfoBean> getLoadMoreHolder() throws Exception {// 真正子线程中
		/*	// 请求网络
			HttpUtils httpUtils = new HttpUtils();
			// http://localhost:8080/GooglePlayServer/
			// home
			// index=0
			String url = URLS.BASEURL + "home";
			RequestParams params = new RequestParams();
			params.addQueryStringParameter("index", mDatas.size() + "");
			ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET, url, params);
			String result = responseStream.readString();
			// json解析
			Gson gson = new Gson();
			HomeBean homeBean = gson.fromJson(result, HomeBean.class);
			if (homeBean == null) {
				return null;
			}
			if (homeBean.list != null) {
				return homeBean.list;
			}
			return null;*/
		/*--------------- 协议简单封装 ---------------*/
		HomeBean homeBean = mProtocol.loadData(mDatas.size());
		if (homeBean == null) {
			return null;
		}
		if (homeBean.list != null) {
			return homeBean.list;
		}
		return null;
	}

	@Override
	public void onPause() {
		// HomeFragment
		if (mHomeAdapter != null) {
			List<AppItemHolder> appItemHolders = mHomeAdapter.getAppItemHolders();
			// 遍历集合,移除观察者
			for (AppItemHolder appItemHolder : appItemHolders) {
				DownLoadManager.getInstance().deleteObserver(appItemHolder);

			}
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		if (mHomeAdapter != null) {
			List<AppItemHolder> appItemHolders = mHomeAdapter.getAppItemHolders();
			// 遍历集合,移除观察者
			for (AppItemHolder appItemHolder : appItemHolders) {
				DownLoadManager.getInstance().addObserver(appItemHolder);
			}
			//手动刷新
			mHomeAdapter.notifyDataSetChanged();//getview-->
		}
		super.onResume();
	}

}
