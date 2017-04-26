package com.yam.rest.pojo;

import java.util.List;

/**
 * 
 * 返回商品分类结果类
 * @author Yampery
 * @date 2017年3月6日 下午10:56:44
 */
public class CatResult {

	/**
	 * 返回结果数据
	 */
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
}
