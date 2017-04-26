package com.yam.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yam.common.utils.ExceptionUtil;
import com.yam.portal.pojo.CartItem;
import com.yam.portal.pojo.Order;
import com.yam.portal.service.CartService;
import com.yam.portal.service.OrderService;

/**
 * 
 * 订单页面
 * @author Yampery
 * @date 2017年3月30日 下午10:28:04
 */
@RequestMapping("/order")
public class OrderController {

	@Resource private CartService cartService;
	@Resource private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		
		// 查询购物车订单列表
		List<CartItem> list = cartService.getCartItemList(request, response);
		// 添加到model
		model.addAttribute("cartList", list);
		
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String submitOrder(Order order, Model model) {
		
		try {
			// 调用订单服务获取订单id
			String orderId = orderService.submitOrder(order);
			
			// 向model中添加前端需要展示的属性
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", 
					new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			
			e.printStackTrace();
			model.addAttribute("message", "提交订单出错，请重试！");
			return "error/exception";
		}
		
	}
}














