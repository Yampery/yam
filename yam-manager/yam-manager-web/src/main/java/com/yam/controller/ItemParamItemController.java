package com.yam.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yam.service.ItemParamItemService;

/**
 * 
 * 展示商品规格参数
 * @author Yampery
 * @date 2017年3月5日 下午11:11:22
 */
@Controller
@RequestMapping("/showItem")
public class ItemParamItemController {

	@Resource
	private ItemParamItemService service;
	
	/**
	 * 商品规格参数展示控制器
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		
		String string = service.getItemParambyItemId(itemId);
		
		model.addAttribute("itemParam", string);
		
		return "item";
		
	}
}
