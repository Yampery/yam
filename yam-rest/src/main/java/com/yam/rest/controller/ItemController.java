package com.yam.rest.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.rest.service.ItemService;

/**
 * 
 * 商品信息查看Controller
 * @author Yampery
 * @date 2017年3月19日 上午10:31:08
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	@Resource private ItemService service;
	
	/**
	 * 查看商品基本信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	public YamResult getItemBaseInfo(@PathVariable Long itemId) {
		
		return service.getItemBaseInfo(itemId);
	}
	
	/**
	 * 查看商品描述
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	public YamResult getItemDesc(@PathVariable Long itemId) {
		
		return service.getItemDesc(itemId);
	}
	
	/**
	 * 查看商品规格参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	public YamResult getItemParam(@PathVariable Long itemId) {
		
		return service.getItemParam(itemId);
	}
}













