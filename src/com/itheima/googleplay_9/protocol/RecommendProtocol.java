package com.itheima.googleplay_9.protocol;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay_9.base.BaseProtocol;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午10:19:53
 * @描述	     TODO
 *
 * @版本       $Rev: 32 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 10:57:03 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class RecommendProtocol extends BaseProtocol<List<String>> {

	@Override
	protected String getInterfaceKey() {
		// TODO
		return "recommend";
	}

	@Override
	protected List<String> parseJson(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, new TypeToken<List<String>>() {
		}.getType());
	}

}
