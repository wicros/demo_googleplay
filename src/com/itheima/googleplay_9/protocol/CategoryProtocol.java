package com.itheima.googleplay_9.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.googleplay_9.base.BaseProtocol;
import com.itheima.googleplay_9.bean.CategoryBean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午8:42:28
 * @描述	     TODO
 *
 * @版本       $Rev: 30 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 09:08:28 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class CategoryProtocol extends BaseProtocol<List<CategoryBean>> {


	@Override
	protected String getInterfaceKey() {
		return "category";
	}

	@Override
	protected List<CategoryBean> parseJson(String jsonString) {
		/*--------------- android sdk 里面json解析 ---------------*/
		List<CategoryBean> categoryBeans = new ArrayList<CategoryBean>();
		try {

			JSONArray rootJsonArray = new JSONArray(jsonString);
			// 变量jsonArray
			for (int i = 0; i < rootJsonArray.length(); i++) {
				JSONObject itemJsonObject = (JSONObject) rootJsonArray.get(i);
				String title = itemJsonObject.getString("title");

				CategoryBean titleBean = new CategoryBean();
				titleBean.title = title;
				titleBean.isTitle = true;

				// 添加到集合中
				categoryBeans.add(titleBean);

				// 拿到里面的infos
				JSONArray infosJsonArray = itemJsonObject.getJSONArray("infos");
				for (int j = 0; j < infosJsonArray.length(); j++) {
					JSONObject infoJsonObject = (JSONObject) infosJsonArray.get(j);
					// 取出infoJsonObject中的值
					String name1 = infoJsonObject.getString("name1");
					String name2 = infoJsonObject.getString("name2");
					String name3 = infoJsonObject.getString("name3");
					String url1 = infoJsonObject.getString("url1");
					String url2 = infoJsonObject.getString("url2");
					String url3 = infoJsonObject.getString("url3");

					CategoryBean infoCategoryBean = new CategoryBean();
					infoCategoryBean.name1 = name1;
					infoCategoryBean.name2 = name2;
					infoCategoryBean.name3 = name3;
					infoCategoryBean.url1 = url1;
					infoCategoryBean.url2 = url2;
					infoCategoryBean.url3 = url3;
					// 保存到集合中
					categoryBeans.add(infoCategoryBean);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return categoryBeans;
	}

}
