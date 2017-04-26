package com.yam.search.service;

import com.yam.common.pojo.YamResult;

/**
 * 
 * 搜索服务层
 * @author Yampery
 * @date 2017年3月14日 下午11:09:41
 */
public interface ItemServcice {

	/**
	 * 导入所有商品数据
	 * @return
	 */
	YamResult importAllItems();
}
