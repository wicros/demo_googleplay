package com.itheima.googleplay_9.bean;

import java.util.List;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-15 下午2:23:42
 * @描述	     TODO
 *
 * @版本       $Rev: 37 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 17:20:53 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class AppInfoBean {
	public String					des;			// 应用的描述
	public String					downloadUrl;	// 应用的下载地址
	public String					iconUrl;		// 应用的图标地址
	public long						id;			// 应用的id
	public String					name;			// 应用的名字
	public String					packageName;	// 应用的包名
	public long						size;			// 应用的大小
	public float					stars;			// 应用的评分

	/*--------------- 添加detail里面的字段 ---------------*/
	public String					author;		// 应用的所属公司
	public String					date;			// 应用的更新时间
	public String					downloadNum;	// 应用的下载量
	public String					version;		// 应用的版本号

	public List<AppInfoSafeBean>	safe;			// Array
	public List<String>				screen;		// Array

	public class AppInfoSafeBean {
		public String	safeDes;		// 安全的描述
		public int		safeDesColor;	// 安全描述对应的文字颜色
		public String	safeDesUrl;	// 安全描述的图标
		public String	safeUrl;		// 安全图标
	}

	@Override
	public String toString() {
		return "AppInfoBean [des=" + des + ", downloadUrl=" + downloadUrl + ", iconUrl=" + iconUrl + ", id=" + id
				+ ", name=" + name + ", packageName=" + packageName + ", size=" + size + ", stars=" + stars
				+ ", author=" + author + ", date=" + date + ", downloadNum=" + downloadNum + ", version=" + version
				+ ", safe=" + safe + ", screen=" + screen + "]";
	}

}
