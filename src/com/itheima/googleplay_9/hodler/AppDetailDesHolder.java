package com.itheima.googleplay_9.hodler;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.googleplay_9.R;
import com.itheima.googleplay_9.base.BaseHolder;
import com.itheima.googleplay_9.bean.AppInfoBean;
import com.itheima.googleplay_9.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 下午2:46:20
 * @描述	     TODO
 *
 * @版本       $Rev: 37 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 17:20:53 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class AppDetailDesHolder extends BaseHolder<AppInfoBean> implements OnClickListener {
	@ViewInject(R.id.app_detail_des_tv_author)
	TextView			mTvAuthor;
	@ViewInject(R.id.app_detail_des_iv_arrow)
	ImageView			mIvArrow;
	@ViewInject(R.id.app_detail_des_tv_des)
	TextView			mTvDes;
	private boolean		isOpen	= true;
	private int			mTvDesMeasureHeight;
	private AppInfoBean	mData;

	@Override
	protected View initHolderView() {
		View view = View.inflate(UIUtils.getContext(), R.layout.item_app_detail_des, null);
		ViewUtils.inject(this, view);
		view.setOnClickListener(this);
		return view;
	}

	@Override
	protected void rerefreshHolderView(AppInfoBean data) {
		mTvAuthor.setText(data.author);
		mTvDes.setText(data.des);
		// 保存到成员变量
		mData = data;

		mTvDes.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				mTvDesMeasureHeight = mTvDes.getMeasuredHeight();
				// 下面的代码不能忘记
				mTvDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);

				// 默认折叠
				toggle(false);
			}
		});
	}

	@Override
	public void onClick(View v) {
		toggle(true);
	}

	private void toggle(boolean isAnimation) {
		if (isOpen) {// 折叠
			// 折叠 mTvDes的高度从 应有的高度--->7行的高度
			// 用measure(0,0) 的方式得到的高度是一行的高度,是不正确的
			// mTvDes.measure(0, 0);
			// int measuredHeight = mTvDes.getMeasuredHeight();
			// Toast.makeText(UIUtils.getContext(), mTvDesMeasureHeight + "", 0).show();
			int start = mTvDesMeasureHeight;
			int end = getShowHeight(7, mData);
			if (isAnimation) {
				doAnimation(start, end);
			} else {
				mTvDes.setHeight(end);
			}
		} else {// 展开
			// 展开 mTvDes的高度从 7行的高度 --->应有的高度
			int start = getShowHeight(7, mData);
			int end = mTvDesMeasureHeight;
			if (isAnimation) {
				doAnimation(start, end);
			} else {
				mTvDes.setHeight(end);
			}
		}
		if (isAnimation) {// 文字折叠的时候,箭头就应该旋转
			if (isOpen) {
				ObjectAnimator.ofFloat(mIvArrow, "rotation", 180, 0).start();
			} else {
				ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
			}
		}
		isOpen = !isOpen;
	}

	public void doAnimation(int start, int end) {
		ObjectAnimator animator = ObjectAnimator.ofInt(mTvDes, "height", start, end);
		animator.start();

		animator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animator) {// 动画开始
				// TODO

			}

			@Override
			public void onAnimationRepeat(Animator animator) {// 动画重复
				// TODO

			}

			@Override
			public void onAnimationEnd(Animator animator) {// 动画结束
				ViewParent parent = mTvDes.getParent();// 爹
				while (true) {
					parent = parent.getParent();// 爹 爹 爹 爹
					if (parent == null) {// 没有爹了
						break;
					}
					if (parent instanceof ScrollView) {// 找到我们想找的对象
						ScrollView sv = (ScrollView) parent;
						sv.fullScroll(View.FOCUS_DOWN);
						break;
					}
				}
			}

			@Override
			public void onAnimationCancel(Animator animator) {// 动画取消
				// TODO

			}
		});
	}

	/**
	 * 返回具体行高对应的measureHeight
	 * @param i 行高
	 * @param data 具体的内容
	 * @return
	 */
	private int getShowHeight(int i, AppInfoBean data) {
		TextView tempTextView = new TextView(UIUtils.getContext());
		tempTextView.setText(data.des);
		tempTextView.setLines(7);
		tempTextView.measure(0, 0);
		int measuredHeight = tempTextView.getMeasuredHeight();
		return measuredHeight;
	}

}
