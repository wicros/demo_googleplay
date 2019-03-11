package com.itheima.googleplay_9.conf;

import com.itheima.googleplay_9.utils.LogUtils;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-14 下午2:32:08
 * @描述	     TODO
 *
 * @版本       $Rev: 21 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 16:56:52 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public class Constants {
	// LogUtils.LEVEL_ALL-->显示所有日志
	// LogUtils.LEVEL_OFF-->关闭所有日志
	public static final int		DEBUGLEVEL		= LogUtils.LEVEL_ALL;
	public static final int		PAGERSIZE		= 20;
	public static final long	PROTOCOLTIMEOUT	= 5 * 60 * 1000;

	public static final class URLS {
		public static final String	BASEURL			= "http://192.168.1.100:8080/GooglePlayServer/";
		// http://localhost:8080/GooglePlayServer/image
		public static final String	IMAGEBASEURL	= BASEURL + "image?name=";
	}

	public static final class REQ {

	}

	public static final class RES {

	}

	public static final class PAY {
		public static final String	PAYTYPE_ZHIFUBAO	= "1";
		public static final String	PAYTYPE_YL			= "2";
		public static final String	PAYTYPE_WX			= "3";
	}
}
