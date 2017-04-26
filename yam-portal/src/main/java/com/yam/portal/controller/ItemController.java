package com.yam.portal.controller;


import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yam.portal.pojo.ItemInfo;
import com.yam.portal.service.ItemService;

/**
 * 
 * 商品详情展示
 * @author Yampery
 * @date 2017年3月20日 下午10:24:15
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Resource private ItemService service;

	/**
	 * 商品基本信息
	 * @param itemId 商品id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		
		ItemInfo item = service.getItemById(itemId);
		model.addAttribute("item", item);
		
		return "item";
	}
	
	/**
	 * 获取商品描述
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/desc/{itemId}", 
			produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showDesc(@PathVariable Long itemId) {
		
		return service.getItemDescById(itemId);
	}
	
	/**
	 * 获取商品规格参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/param/{itemId}", 
			produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showParam(@PathVariable Long itemId) {
		
		return service.getItemParamById(itemId);
	}
}















