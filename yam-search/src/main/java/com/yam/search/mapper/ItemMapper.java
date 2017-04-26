package com.yam.search.mapper;

import java.util.List;

import com.yam.search.pojo.Item;

/**
 * 
 * 商品dao层
 * @author Yampery
 * @date 2017年3月14日 下午10:59:34
 */
public interface ItemMapper {
	
	/**
	 * 获取商品列表
	 * @return
	 */
	List<Item> getItemList(); 
}
