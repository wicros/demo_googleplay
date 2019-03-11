package com.itheima.googleplay_9.bean;

/**
 * @创建者	 Administrator
 * @创时间 	 2015-8-18 上午8:45:22
 * @描述	     TODO
 *
 * @版本       $Rev: 31 $
 * @更新者     $Author: admin $
 * @更新时间    $Date: 2015-08-18 09:52:29 +0800 (星期二, 18 八月 2015) $
 * @更新描述    TODO
 */
public class CategoryBean {
	public String	title;
	public String	name1;		// 休闲
	public String	name2;		// 棋牌
	public String	name3;		// 益智
	public String	url1;		// image/category_game_0.jpg
	public String	url2;		// image/category_game_1.jpg
	public String	url3;		// image/category_game_2.jpg
	public boolean	isTitle;	// 判断是否是title

	@Override
	public String toString() {
		return "CategoryBean [title=" + title + ", name1=" + name1 + ", name2=" + name2 + ", name3=" + name3
				+ ", url1=" + url1 + ", url2=" + url2 + ", url3=" + url3 + ", isTitle=" + isTitle + "]";
	}

}
