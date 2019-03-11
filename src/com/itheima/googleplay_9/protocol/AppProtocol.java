package com.itheima.googleplay_9.protocol;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay_9.base.BaseProtocol;
import com.itheima.googleplay_9.bean.AppInfoBean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 上午8:49:30
 * @描述	     TODO
 *
 * @版本       $Rev: 22 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 09:06:28 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class AppProtocol extends BaseProtocol<List<AppInfoBean>> {

	@Override
	protected String getInterfaceKey() {
		// TODO
		return "app";
	}

	@Override
	protected List<AppInfoBean> parseJson(String jsonString) {
		Gson gson = new Gson();
		// gson.fromJson(jsonString, xxx.class);//解析实体bean
		/*--------------- gson 的泛型解析 ---------------*/
		return gson.fromJson(jsonString, new TypeToken<List<AppInfoBean>>() {
		}.getType());
	}

}
