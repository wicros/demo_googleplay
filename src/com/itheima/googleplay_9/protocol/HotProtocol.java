package com.itheima.googleplay_9.protocol;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay_9.base.BaseProtocol;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 下午4:24:21
 * @描述	     TODO
 *
 * @版本       $Rev: 29 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 16:53:49 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class HotProtocol extends BaseProtocol<List<String>> {

	@Override
	protected String getInterfaceKey() {
		// TODO
		return "hot";
	}

	@Override
	protected List<String> parseJson(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, new TypeToken<List<String>>() {
		}.getType());
	}
}
