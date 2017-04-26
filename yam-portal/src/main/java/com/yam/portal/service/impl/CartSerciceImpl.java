package com.yam.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.CookieUtils;
import com.yam.common.utils.HttpClientUtil;
import com.yam.common.utils.JsonUtils;
import com.yam.pojo.TbItem;
import com.yam.portal.pojo.CartItem;
import com.yam.portal.service.CartService;

@Service
public class CartSerciceImpl implements CartService {

	@Value("${REST_BASE_URL}") private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}") private String ITEM_INFO_URL;
	
	
	@Override
	public YamResult addCartItem(HttpServletRequest request, 
				HttpServletResponse response, long itemId, int num) {
		
		
		CartItem cartItem = null;
		
		// 0. 先取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		for (CartItem cItem : itemList) {
			// 如果当前商品存在
			if (itemId == cItem.getId()) {
				// 增加购物车内该商品数量
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}
		
		if (null == cartItem) {
			cartItem = new CartItem();
			// 1. 根据商品id查询商品信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			// 2. 把json转换成商品对象
			YamResult result = YamResult.formatToPojo(json, TbItem.class);
			if (200 == result.getStatus()) {
				TbItem item = (TbItem) result.getData();
				// 设置购物车商品属性
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			//3. 添加到购物车
			itemList.add(cartItem);
		}
		
		// -- 吧购物车列表写入cookie
		CookieUtils.setCookie(request, response, "YAM_CART", 
				JsonUtils.objectToJson(itemList), true);
		return null;
	}
	
	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		
		return getCartItemList(request);
	}
	
	
	/**
	 * 私有方法，从Cookie中取商品列表
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		
		// 从cookie中取出商品列表
		String cartJson = 
				CookieUtils.getCookieValue(request, "YAM_CART", true);
		
		if (null == cartJson)
			return new ArrayList<>();
		
		// 将json转换成商品列表
		List<CartItem> list;
		try {
			list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
	}

	@Override
	public YamResult deleteCartItem(HttpServletRequest request, HttpServletResponse response, long itemId) {
		
		// 1. 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 2. 从列表中找到该商品并删除
		for (CartItem item : itemList) {
			if (item.getId() == itemId) {
				itemList.remove(item);
				break;
			}
		}
		
		// 3. 把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "YAM_CART", 
				JsonUtils.objectToJson(itemList), true);
		return YamResult.ok();
	}

}
