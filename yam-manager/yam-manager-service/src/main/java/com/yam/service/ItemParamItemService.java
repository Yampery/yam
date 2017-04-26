package com.yam.service;

/**
 * 
 * 商品规格参数相关服务类
 * @author Yampery
 * @date 2017年3月5日 下午10:34:50
 */
public interface ItemParamItemService {

	/**
	 * 通过商品id查询商品规格参数
	 * @param itemId 商品id
	 * @return
	 */
	String getItemParambyItemId(Long itemId);
}
