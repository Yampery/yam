package com.yam.portal.service;

import com.yam.portal.pojo.ItemInfo;

/**
 * 
 * 商品展示服务
 * @author Yampery
 * @date 2017年3月20日 下午10:15:17
 */
public interface ItemService {

	/**
	 * 根据商品id查询商品基本信息
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemById(long itemId);
	
	/**
	 * 根据商品id商品描述信息
	 * @param itemId
	 * @return html片段
	 */
	String getItemDescById(long itemId);
	
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @return html片段
	 */
	String getItemParamById(long itemId);
}