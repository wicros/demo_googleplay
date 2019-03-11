package com.itheima.googleplay_9.protocol;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.itheima.googleplay_9.base.BaseProtocol;
import com.itheima.googleplay_9.bean.AppInfoBean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-17 上午9:13:07
 * @描述	     TODO
 *
 * @版本       $Rev: 24 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-17 10:25:01 +0800 (星期一, 17 八月 2015) $
 * @更新描述    TODO
 */
public class GameProtocol extends BaseProtocol<List<AppInfoBean>> {

	@Override
	protected String getInterfaceKey() {
		return "game";
	}

	@Override
	protected List<AppInfoBean> parseJson(String jsonString) {
		/*--------------- gson的bean解析 ---------------*/
		/*--------------- gson的泛型解析 ---------------*/
		/*Gson gson = new Gson();
		return gson.fromJson(jsonString, new TypeToken<List<AppInfoBean>>() {
		}.getType());*/
		/*--------------- gson的结点解析 ---------------*/

		List<AppInfoBean> appInfoBeans = new ArrayList<AppInfoBean>();

		// 获得 解析者
		JsonParser parser = new JsonParser();
		// string-->jsonElement
		JsonElement rootJsonElent = parser.parse(jsonString);
		// JsonElement-->JsonArray
		JsonArray rootJsonArray = rootJsonElent.getAsJsonArray();
		// 遍历jsonArrary
		for (JsonElement itemJsonElement : rootJsonArray) {

			/**
			 "id": 1580615,
			"name": "人人",
			"packageName": "com.renren.mobile.android",
			"iconUrl": "app/com.renren.mobile.android/icon.jpg",
			"stars": 2,
			"size": 21803987,
			"downloadUrl": "app/com.renren.mobile.android/com.renren.mobile.android.apk",
			"des": "2005-2014 你的校园一直在这儿。中国最大的实名制SNS网络平台，大学生" 
			 */
			// JsonElement-->JsonObject
			JsonObject itemJsonObject = itemJsonElement.getAsJsonObject();

			JsonElement nameJsonElement = itemJsonObject.get("name");
			// JsonElement-->JsonPrimitive
			JsonPrimitive namePrimitive = nameJsonElement.getAsJsonPrimitive();
			// JsonPrimitive-->String
			String name = namePrimitive.getAsString();

			JsonElement urlJsonElement = itemJsonObject.get("iconUrl");
			// JsonElement-->String
			String iconUrl = urlJsonElement.getAsString();

			long id = itemJsonObject.get("id").getAsLong();
			String packageName = itemJsonObject.get("packageName").getAsString();
			float stars = itemJsonObject.get("stars").getAsFloat();
			long size = itemJsonObject.get("size").getAsLong();
			String downloadUrl = itemJsonObject.get("downloadUrl").getAsString();
			String des = itemJsonObject.get("des").getAsString();

			// 创建AppInfoBean
			AppInfoBean info = new AppInfoBean();
			info.des = des;
			info.downloadUrl = downloadUrl;
			info.iconUrl = iconUrl;
			info.id = id;
			info.name = name;
			info.packageName = packageName;
			info.size = size;
			info.stars = stars;

			// 添加到集合
			appInfoBeans.add(info);
		}

		return appInfoBeans;
	}
}
