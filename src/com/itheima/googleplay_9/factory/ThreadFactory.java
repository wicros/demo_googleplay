package com.itheima.googleplay_9.factory;

import com.itheima.googleplay_9.manager.ThreadPoolProxy;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 上午10:00:47
 * @描述	     创建普通线程池,创建下载线程池
 *
 * @版本       $Rev: 10 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 10:05:19 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public class ThreadFactory {
	static ThreadPoolProxy	mNormalPool;	// 只需初始化一次就行了
	static ThreadPoolProxy	mDownLoadPool;	// 只需初始化一次就行了

	/**创建了一个普通的线程池*/
	public static ThreadPoolProxy getNormalPool() {
		if (mNormalPool == null) {
			synchronized (ThreadFactory.class) {
				if (mNormalPool == null) {
					mNormalPool = new ThreadPoolProxy(5, 5, 3000);
				}
			}
		}
		return mNormalPool;
	}

	/**创建了一个下载的线程池*/
	public static ThreadPoolProxy getDownLoadPool() {
		if (mDownLoadPool == null) {
			synchronized (ThreadFactory.class) {
				if (mDownLoadPool == null) {
					mDownLoadPool = new ThreadPoolProxy(3, 3, 3000);
				}
			}
		}
		return mDownLoadPool;
	}
}
