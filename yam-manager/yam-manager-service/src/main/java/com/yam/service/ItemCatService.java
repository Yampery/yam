package com.yam.service;

import java.util.List;

import com.yam.common.pojo.EUTreeNode;

/**
 * 
 * 商品类目选择接口（树）
 * @author Yampery
 * @date 2017年3月2日 上午12:47:45
 */
public interface ItemCatService {

	/**
	 * 获取商品树形表
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCatList(long parentId);
}
