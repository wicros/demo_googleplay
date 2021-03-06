package com.itheima.googleplay_9.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.itheima.googleplay_9.conf.Constants;
import com.itheima.googleplay_9.conf.Constants.URLS;
import com.itheima.googleplay_9.utils.FileUtils;
import com.itheima.googleplay_9.utils.IOUtils;
import com.itheima.googleplay_9.utils.LogUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 下午4:29:50
 * @描述	     TODO
 *
 * @版本       $Rev: 45 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-19 17:20:02 +0800 (星期三, 19 八月 2015) $
 * @更新描述    TODO
 */
public abstract class BaseProtocol<T> {
	/**
	//读取本地文件
			if(文件存在){
				//读取插入时间
				//判断是否过期
				if(未过期){
					//读取缓存内容
					//Json解析解析内容
					if(不为null){
						//返回并结束
					}	
				}
			}else{
				//加载网络数据
				if(网络数据加载成功){
					//读取网络数据
					//保存网络数据到本地
					//Json解析内容
					//返回并结束
			
				}else{
					//返回null
				}
			}

	 */
	public T loadData(int index) throws Exception {
		/*--------------- 首先从本地加载数据 ---------------*/
		T t = getDataFromLocal(index);
		if (t != null) {
			LogUtils.s("###从本地加载数据-->" + getCacheFile(index).getAbsolutePath());
			return t;
		}

		/*--------------- 得到jsonString的过程 ---------------*/
		T parseJson = getDataFromNet(index);
		return parseJson;
	}

	/**
	 *  从网络加载数据
	 */
	public T getDataFromNet(int index) throws HttpException, IOException {
		// 真正子线程中
		// 请求网络
		HttpUtils httpUtils = new HttpUtils();
		// http://localhost:8080/GooglePlayServer/
		// home
		// index=0
		String url = URLS.BASEURL + getInterfaceKey();
		RequestParams params = new RequestParams();

		Map<String, String> extraParams = getExtraParams();

		if (extraParams != null) {// 子类覆写了该方法
			for (Map.Entry<String, String> info : extraParams.entrySet()) {
				String key = info.getKey();// "packageName"
				String packageName = info.getValue();
				params.addQueryStringParameter(key, "" + packageName);
			}
		} else {
			params.addQueryStringParameter("index", "" + index);

		}

		ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET, url, params);
		String jsonString = responseStream.readString();
		/*--------------- json解析的过程,得到具体实体bean的过程 ---------------*/

		/*--------------- 保存对应的jsonString ---------------*/
		File cacheFile = getCacheFile(index);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(cacheFile));
			writer.write(System.currentTimeMillis() + "");
			// 换行
			writer.write("\r\n");
			// 开始写入jsonString
			writer.write(jsonString);
		} catch (Exception e) {

		} finally {
			IOUtils.close(writer);
		}
		// list
		// map
		// object
		// json解析
		T parseJson = parseJson(jsonString);

		return parseJson;
	}

	/**
	 * @des 传递额外的参数,默认情况是null
	 * @call 子类可以覆写该方法,传递具体额外的参数
	 * @return
	 */
	protected Map<String, String> getExtraParams() {
		return null;
	}

	/**
	 * 从本地加载数据
	 * @param index
	 * @return
	 */
	private T getDataFromLocal(int index) {
		/*	if(文件存在){
				//读取插入时间
				//判断是否过期
				if(未过期){
					//读取缓存内容
					//Json解析解析内容
					if(不为null){
						//返回并结束
					}	
				}
			}*/
		try {
			File cacheFile = getCacheFile(index);
			if (cacheFile.exists()) {
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(cacheFile));
					// 读取插入时间
					String timeStr = reader.readLine();
					long time_ = Long.parseLong(timeStr);
					// 判断是否过期
					if (System.currentTimeMillis() - time_ < Constants.PROTOCOLTIMEOUT) {// 未过期
						String cacheJsonString = reader.readLine();
						return parseJson(cacheJsonString);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					IOUtils.close(reader);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public File getCacheFile(int index) {
		String dir = FileUtils.getDir("json");// sdcard/Android/data/包名/json
		Map<String, String> extraParams = getExtraParams();
		String name = "";
		if (extraParams != null) {// 走的详情协议
			for (Map.Entry<String, String> info : extraParams.entrySet()) {
				String packageName = info.getValue();
				name = getInterfaceKey() + "." + packageName;
			}
		} else {
			name = getInterfaceKey() + "." + index;
		}
		File cacheFile = new File(dir, name);
		return cacheFile;
	}

	/**
	 * @des 返回协议的关键字home/game/subject
	 * @des 必须实现,但是不知道具体实现,定义成抽象方法交给子类具体实现
	 */
	protected abstract String getInterfaceKey();

	/**
	 * @des jsonString->实体bean的过程
	 * @des 必须实现,但是不知道具体实现,定义成抽象方法交给子类具体实现
	 */
	protected abstract T parseJson(String jsonString);
}
