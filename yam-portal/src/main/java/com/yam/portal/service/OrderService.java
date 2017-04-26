package com.yam.portal.service;

import com.yam.portal.pojo.Order;

/**
 * 
 * 订单提交服务
 * @author Yampery
 * @date 2017年3月30日 下午11:04:50
 */
public interface OrderService {

	/**
	 * 订单提交
	 * @param order 订单pojo
	 * @return
	 */
	String submitOrder(Order order);
}
