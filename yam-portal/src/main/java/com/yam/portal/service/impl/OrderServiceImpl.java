package com.yam.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.HttpClientUtil;
import com.yam.common.utils.JsonUtils;
import com.yam.portal.pojo.Order;
import com.yam.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}") private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}") private String ORDER_CREATE_URL;
	
	@Override
	public String submitOrder(Order order) {
		
		// 在创建order之前应该补全用户的信息
		
		// 调用yam-order创建订单服务
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, 
				JsonUtils.objectToJson(order));
		// 将json转换为YamResult
		YamResult yamResult = YamResult.format(json);
		
		if (200 == yamResult.getStatus()) {
			Object orderId = yamResult.getData();
			return orderId.toString();
		}
		
		return "";
	}

}
