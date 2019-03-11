package com.itheima.googleplay_9.utils;


import com.itheima.googleplay_9.manager.DownLoadInfo;
import com.itheima.googleplay_9.manager.DownLoadManager;

/**
 * @author  Administrator
 * @time 	2015-7-13 下午11:12:12
 * @des	TODO
 *
 * @version $Rev: 41 $
 * @updateAuthor $Author: admin $
 * @updateDate $Date: 2015-08-19 14:58:07 +0800 (星期三, 19 八月 2015) $
 * @updateDes TODO
 */
public class PrintDownLoadInfo {
	public static void printDownLoadInfo(DownLoadInfo info) {
		String result = "";
		switch (info.state) {
		case DownLoadManager.STATE_UNDOWNLOAD:// 未下载
			result = "未下载";
			break;
		case DownLoadManager.STATE_DOWNLOADING:// 下载中
			result = "下载中";
			break;
		case DownLoadManager.STATE_PAUSEDOWNLOAD:// 暂停下载
			result = "暂停下载";
			break;
		case DownLoadManager.STATE_WAITINGDOWNLOAD:// 等待下载
			result = "等待下载";
			break;
		case DownLoadManager.STATE_DOWNLOADFAILED:// 下载失败
			result = "等待下载";
			break;
		case DownLoadManager.STATE_DOWNLOADED:// 下载完成
			result = "下载完成";
			break;
		case DownLoadManager.STATE_INSTALLED:// 已安装
			result = "已安装";
			break;

		default:
			break;
		}
		LogUtils.sf(result);
	}
}
