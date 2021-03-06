package com.itheima.googleplay_9.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-19 上午9:09:07
 * @描述	     添加了一个进度展示的效果
 *
 * @版本       $Rev: 41 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 14:58:07 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class ProgressButton extends Button {
	private boolean	isProgressEnable;
	private long	mMax	= 100;
	private long	mProgress;

	/**设置是否有progress的效果*/
	public void setProgressEnable(boolean isProgressEnable) {
		this.isProgressEnable = isProgressEnable;
	}

	/**设置进度的最大值*/
	public void setMax(long max) {
		mMax = max;
	}

	/**设置当前的进度*/
	public void setProgress(long progress) {
		mProgress = progress;
		// 重绘button
		invalidate();
	}

	public ProgressButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ProgressButton(Context context) {
		super(context);
	}

	// onlayout onmeasure ondraw

	@Override
	protected void onDraw(Canvas canvas) {
		if (isProgressEnable) {
			ColorDrawable colorDrawable = new ColorDrawable(Color.BLUE);

			int left = 0;
			int top = 0;
			int right = (int) (mProgress * 1.0f / mMax * getMeasuredWidth() + .5f);
			int bottom = getBottom();
			// 设置drawable绘制的rect--->必须设置
			colorDrawable.setBounds(left, top, right, bottom);
			// 绘制colorDrawable到canvas
			colorDrawable.draw(canvas);
		}

		// TODO
		super.onDraw(canvas);// (绘制文字,可以有点击事件)
	}
}
