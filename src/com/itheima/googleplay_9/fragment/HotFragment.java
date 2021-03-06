package com.itheima.googleplay_9.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.googleplay_9.base.BaseFragment;
import com.itheima.googleplay_9.base.LoadingPager.LoadedResult;
import com.itheima.googleplay_9.protocol.HotProtocol;
import com.itheima.googleplay_9.utils.UIUtils;
import com.itheima.googleplay_9.views.FlowLayout;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午4:00:42
 * @描述	     TODO
 *
 * @版本       $Rev: 29 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 16:53:49 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class HotFragment extends BaseFragment {

	private List<String>	mDatas;

	@Override
	protected LoadedResult initData() {// 真正子子线程中
		HotProtocol protocol = new HotProtocol();
		try {
			mDatas = protocol.loadData(0);
			return checkState(mDatas);
		} catch (Exception e) {
			e.printStackTrace();
			return LoadedResult.ERROR;
		}
	}

	@Override
	protected View initSuccessView() {
		ScrollView scrollView = new ScrollView(UIUtils.getContext());

		FlowLayout flowlayout = new FlowLayout(UIUtils.getContext());
		// 往flowlayout添加数据
		for (final String data : mDatas) {
			// 创建一个textView
			TextView tv = new TextView(UIUtils.getContext());
			tv.setText(data);

			// tv.setBackgroundResource(R.drawable.shape_hot_flowlayout_tv);
			// 默认的背景图片
			GradientDrawable normalDrawable = new GradientDrawable();
			// 设置填充色-->随机颜色
			Random random = new Random();

			int alpha = 255;
			int red = random.nextInt(190) + 30;// 30-220;
			int green = random.nextInt(190) + 30;
			int blue = random.nextInt(190) + 30;
			int argb = Color.argb(alpha, red, green, blue);
			normalDrawable.setColor(argb);// selector
			// 设置圆角
			normalDrawable.setCornerRadius(UIUtils.dip2Px(5));
			// 设置纯色背景
			// tv.setBackgroundDrawable(drawable);
			// 设置一个状态图片(selector)

			// 按下去的图片
			GradientDrawable pressDrawable = new GradientDrawable();
			pressDrawable.setColor(Color.DKGRAY);
			pressDrawable.setCornerRadius(UIUtils.dip2Px(5));

			// 有状态效果的图片
			StateListDrawable selectorDrawable = new StateListDrawable();
			// @attr ref android.R.styleable#DrawableStates_state_pressed
			selectorDrawable.addState(new int[] {}, normalDrawable);
			selectorDrawable.addState(new int[] { android.R.attr.state_pressed }, pressDrawable);

			tv.setBackgroundDrawable(selectorDrawable);

			tv.setClickable(true);

			// 点击事件
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO
					Toast.makeText(UIUtils.getContext(), data, 0).show();
				}
			});

			int padding = UIUtils.dip2Px(5);
			tv.setPadding(padding, padding, padding, padding);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(Color.WHITE);

			flowlayout.addView(tv);
		}
		scrollView.addView(flowlayout);
		return scrollView;
	}

}