package com.yam.portal.pojo;

/**
 * 
 * 购物车中商品的pojo
 * @author Yampery
 * @date 2017年3月28日 下午11:33:31
 */
public class CartItem {

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	private long id;
	private String title;
	private Integer num;
	private long price;
	private String image;
}
