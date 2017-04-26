package com.yam.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yam.portal.pojo.CartItem;
import com.yam.portal.service.CartService;

/**
 * 
 * 购物车商品Controller
 * @author Yampery
 * @date 2017年3月29日 上午12:12:33
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Resource private CartService service;
	
	/**
	 * 添加商品到购物车
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, 
			@RequestParam(defaultValue="1") Integer num,
			HttpServletRequest req, HttpServletResponse resp) {
		
		service.addCartItem(req, resp, itemId, num);
		return "redirect:/cart/success";
	}
	
	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}
	
	/**
	 * 展示购物车商品列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest req,
				HttpServletResponse resp, Model model) {
		
		List<CartItem> list = service.getCartItemList(req, resp);
		model.addAttribute("cartList", list);
		return "cart";
	}
	
	/**
	 * 删除购物车商品
	 * @param req
	 * @param resp
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(HttpServletRequest req,
			HttpServletResponse resp, @PathVariable Long itemId) {
		
		service.deleteCartItem(req, resp, itemId);
		return "redirect:/cart/cart.html";
	}
}

















