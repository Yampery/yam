package com.yam.rest.service;

import com.yam.common.pojo.YamResult;

/**
 * 
 * 商品信息查询服务
 * @author Yampery
 * @date 2017年3月19日 上午10:24:25
 */
public interface ItemService {

	/**
	 * 商品基本信息查询
	 * @param itemId 商品id
	 * @return
	 */
	YamResult getItemBaseInfo(long itemId);
	
	/**
	 * 商品描述查询
	 * @param itemId 商品id
	 * @return
	 */
	YamResult getItemDesc(long itemId);
	
	/**
	 * 商品规格参数查询
	 * @param itemId
	 * @return
	 */
	YamResult getItemParam(long itemId);
}
