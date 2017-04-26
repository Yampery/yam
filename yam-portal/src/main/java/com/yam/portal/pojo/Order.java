package com.yam.portal.pojo;

import java.util.List;

import com.yam.pojo.TbOrder;
import com.yam.pojo.TbOrderItem;
import com.yam.pojo.TbOrderShipping;

/**
 * 
 * 订单类（接收json参数）
 * @author Yampery
 * @date 2017年3月30日 上午1:01:28
 */
public class Order extends TbOrder {

	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}
