package com.yam.service;

import com.yam.common.pojo.EUDataGridResult;
import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbItem;

/**
 * 
 * 商品列表查询
 * @author Yampery
 * @date 2017年2月28日 下午11:44:44
 */
public interface ItemService {
	
	/**
	 * 根据商品id查询
	 * @param itemId 商品id
	 * @return
	 */
	TbItem getItemById(long itemId);
	
	/**
	 * 分页查询商品
	 * @param page 当前页
	 * @param rows 行数
	 * @return
	 */
	EUDataGridResult getItemList(int page, int rows);
	
	/**
	 * 添加商品，同时添加商品描述
	 * @param item 商品
	 * @param desc 描述
	 * @param itemParam 商品规格参数
	 * @return
	 */
	YamResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}














