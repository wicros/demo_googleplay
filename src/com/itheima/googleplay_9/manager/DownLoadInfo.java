package com.itheima.googleplay_9.manager;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-19 上午10:07:15
 * @描述	     放置和下载相关的参数
 *
 * @版本       $Rev: 44 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 16:45:06 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public class DownLoadInfo {

	public String	downLoadUrl;								// 下载地址
	public String	savePath;									// 文件保存地址
	public String	packageName;
	public int		state	= DownLoadManager.STATE_UNDOWNLOAD; // 记录最新的状态.默认是未下载

	public long		max;
	public long		progress;

	public Runnable	task;
}
