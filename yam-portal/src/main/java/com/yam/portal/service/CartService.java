package com.yam.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yam.common.pojo.YamResult;
import com.yam.portal.pojo.CartItem;

/**
 * 
 * 购物车相关服务
 * @author Yampery
 * @date 2017年3月28日 下午11:25:09
 */
public interface CartService {
	
	/**
	 * 添加商品到购物车
	 * @param request
	 * @param response
	 * @param itemId 商品id
	 * @param num 数量
	 * @return
	 */
	YamResult addCartItem(HttpServletRequest request, 
			HttpServletResponse response,long itemId, int num);
	
	/**
	 * 从Cookie中获取购物车商品列表
	 * @param request
	 * @param response
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 删除购物车商品
	 * @param itemId 商品id
	 * @return
	 */
	YamResult deleteCartItem(HttpServletRequest request, HttpServletResponse response, long itemId);
}
