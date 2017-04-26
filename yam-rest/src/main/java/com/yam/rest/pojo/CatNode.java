package com.yam.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 商品分类节点类
 * @author Yampery
 * @date 2017年3月6日 下午10:53:07
 */
public class CatNode {

	/**
	 * 类名
	 */
	@JsonProperty("n")
	private String name;
	
	/**
	 * 链接
	 */
	@JsonProperty("u")
	private String url;
	
	/**
	 * 类目
	 */
	@JsonProperty("i")
	private List<?> item;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<?> getItem() {
		return item;
	}
	public void setItem(List<?> item) {
		this.item = item;
	}
}
