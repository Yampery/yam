package com.yam.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.ExceptionUtil;
import com.yam.order.pojo.Order;
import com.yam.order.service.OrderService;

/**
 * 
 * 订单Controller
 * @author Yampery
 * @date 2017年3月30日 上午1:02:52
 */
@RestController
public class OrderController {

	@Resource private OrderService service;
	
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public YamResult createOrder(@RequestBody Order order) {
		
		try {
			YamResult result = service.createOrder(order, 
					order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}		
	}
}















