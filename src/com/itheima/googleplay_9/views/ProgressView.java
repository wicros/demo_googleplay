package com.itheima.googleplay_9.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplay_9.R;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-19 下午4:00:12
 * @描述	     TODO
 *
 * @版本       $Rev: 44 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 16:45:06 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class ProgressView extends LinearLayout {
	private TextView	mTvNote;
	private ImageView	mIvIcon;

	private boolean		isProgressEnable;
	private long		mMax	= 100;
	private long		mProgress;

	/**设置是否允许进度*/
	public void setProgressEnable(boolean isProgressEnable) {
		this.isProgressEnable = isProgressEnable;
	}

	/**设置进度的最大值*/
	public void setMax(long max) {
		mMax = max;
	}

	/**设置进度的当前值*/
	public void setProgress(long progress) {
		mProgress = progress;
		// 触发ProgressView的disPatchDraw
		invalidate();
	}

	/**修改文本内容*/
	public void setNote(String note) {
		mTvNote.setText(note);
	}

	/**修改图标内容*/
	public void setIcon(int resId) {
		mIvIcon.setImageResource(resId);
	}

	public ProgressView(Context context) {
		this(context, null);
	}

	public ProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = View.inflate(context, R.layout.inflate_progressview, this);
		initView(view);
	}

	private void initView(View view) {
		mTvNote = (TextView) view.findViewById(R.id.tv_progressView_note);
		mIvIcon = (ImageView) view.findViewById(R.id.iv_progressView_icon);
	}

	// onlayout onmesure ondraw
	@Override
	protected void onDraw(Canvas canvas) {// 必须设置背景才可以进来
		super.onDraw(canvas);// 绘制背景-->透明图片
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {// 绘制我们容器里面的孩子
		// TODO
		super.dispatchDraw(canvas);// 绘制icon和文本
		if (isProgressEnable) {
			Paint paint = new Paint();
			paint.setColor(Color.RED);
			paint.setStrokeWidth(3);

			paint.setAntiAlias(true);

			paint.setStyle(Style.STROKE);

			RectF oval = new RectF(mIvIcon.getLeft(), mIvIcon.getTop(), mIvIcon.getRight(), mIvIcon.getBottom());
			float startAngle = -90;
			float sweepAngle = mProgress * 360.f / mMax;
			boolean useCenter = false;// 是否包含半径到弧形中
			canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
		}
	}
}
