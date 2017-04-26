package com.yam.order.service;

import java.util.List;

import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbOrder;
import com.yam.pojo.TbOrderItem;
import com.yam.pojo.TbOrderShipping;

/**
 * 
 * 订单服务接口
 * @author Yampery
 * @date 2017年3月30日 上午12:24:53
 */
public interface OrderService {

	/**
	 * 创建订单
	 * @param order 订单对象
	 * @param itemList 订单商品列表
	 * @param orderShipping 订单物流
	 * @return
	 */
	YamResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
