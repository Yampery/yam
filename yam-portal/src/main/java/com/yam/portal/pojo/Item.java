package com.yam.portal.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 搜索服务要使用的pojo
 * @author Yampery
 * @date 2017年3月17日 上午1:21:10
 */
public class Item {

	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;
	private String item_des;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getItem_des() {
		return item_des;
	}
	public void setItem_des(String item_des) {
		this.item_des = item_des;
	}
	
	/**
	 * 2017年3月19日07:19:59 添加
	 * 商品搜索展示图片返回数组
	 * @return
	 */
	@JsonIgnore
	public String[] getImages() {
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
	
	
}
