package com.itheima.googleplay_9.protocol;

import com.google.gson.Gson;
import com.itheima.googleplay_9.base.BaseProtocol;
import com.itheima.googleplay_9.bean.HomeBean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 下午4:25:05
 * @描述	     TODO
 *
 * @版本       $Rev: 20 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-15 16:36:22 +0800 (星期六, 15 八月 2015) $
 * @更新描述    TODO
 */
public class HomeProtocol extends BaseProtocol<HomeBean> {

	@Override
	protected String getInterfaceKey() {
		return "home";
	}

	@Override
	protected HomeBean parseJson(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, HomeBean.class);
	}
}
