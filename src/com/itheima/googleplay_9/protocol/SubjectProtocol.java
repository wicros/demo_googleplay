package com.itheima.googleplay_9.protocol;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay_9.base.BaseProtocol;
import com.itheima.googleplay_9.bean.SubjectInfoBean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 下午2:35:19
 * @描述	     TODO
 *
 * @版本       $Rev: 27 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 15:41:42 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class SubjectProtocol extends BaseProtocol<List<SubjectInfoBean>> {

	@Override
	protected String getInterfaceKey() {
		// TODO
		return "subject";
	}

	@Override
	protected List<SubjectInfoBean> parseJson(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, new TypeToken<List<SubjectInfoBean>>() {
		}.getType());
	}

}
