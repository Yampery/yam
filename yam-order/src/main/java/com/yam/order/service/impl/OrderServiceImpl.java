package com.yam.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.mapper.TbOrderItemMapper;
import com.yam.mapper.TbOrderMapper;
import com.yam.mapper.TbOrderShippingMapper;
import com.yam.order.dao.JedisClient;
import com.yam.order.service.OrderService;
import com.yam.pojo.TbOrder;
import com.yam.pojo.TbOrderItem;
import com.yam.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService {

	@Resource private TbOrderMapper orderMapper;
	@Resource private TbOrderItemMapper orderItemMapper;
	@Resource private TbOrderShippingMapper orderShippingMapper;
	@Resource private JedisClient jedisClient;
	
	@Value("${ORDER_GEN_KEY}") private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}") private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}") private String ORDER_DETAIL_GEN_KEY;
	
	
	@Override
	public YamResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		
		// 1. 想订单中插入记录
		// 获得订单号
		String key = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(key)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		// 补全pojo属性
		order.setOrderId(orderId + "");
		// 状态：1-未付款；2-已付款；3-未发货；4-已发货；5-交易成功；6-交易失败
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// 0-未评价；1-已评价
		order.setBuyerRate(0);
		// 向订单表插入数据
		orderMapper.insert(order);
		
		// 2. 插入订单明细
		for (TbOrderItem orderItem : itemList) {
			// 补全订单信息
			// 去订单明细
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			orderItem.setId(orderDetailId + "");
			orderItem.setItemId(orderId + "");
			// 想订单明细插入记录
			orderItemMapper.insert(orderItem);
		}
		
		// 3. 插入物流信息
		// 补全物流表属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		
		orderShippingMapper.insert(orderShipping);
		
		return YamResult.ok(orderId);
	}

}




















