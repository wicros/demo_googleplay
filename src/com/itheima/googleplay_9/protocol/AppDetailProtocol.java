package com.itheima.googleplay_9.protocol;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.itheima.googleplay_9.base.BaseProtocol;
import com.itheima.googleplay_9.bean.AppInfoBean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 下午2:07:40
 * @描述	     TODO
 *
 * @版本       $Rev: 34 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 14:32:41 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class AppDetailProtocol extends BaseProtocol<AppInfoBean> {

	private String	mPackageName;

	public AppDetailProtocol(String packageName) {
		super();
		mPackageName = packageName;
	}

	// http://localhost:8080/GooglePlayServer/detail?
	// packageName=com.itheima.www
	// http://localhost:8080/GooglePlayServer/detail?
	// index=20
	@Override
	protected String getInterfaceKey() {
		// TODO
		return "detail";
	}

	@Override
	protected AppInfoBean parseJson(String jsonString) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, AppInfoBean.class);
	}

	//
	/**
	 * @des 覆写getExtraParmas方法,传递额外的参数
	 * @call 
	 */
	@Override
	protected Map<String, String> getExtraParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("packageName", mPackageName);
		return params;
	}
}
