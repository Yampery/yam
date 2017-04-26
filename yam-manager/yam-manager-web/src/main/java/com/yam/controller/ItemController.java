package com.yam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.EUDataGridResult;
import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbItem;
import com.yam.service.ItemService;

/**
 * 
 * 商品管理Controller
 * @author Yampery
 * @date 2017年2月28日 下午11:46:38
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 通过id查询商品
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/{itemId}")
	
	public TbItem getItemById(@PathVariable long itemId) {
		
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	/**
	 * 查询商品列表
	 * @param page 页码
	 * @param rows 总数
	 * @return
	 */
	@RequestMapping("/list")
	
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		
		return itemService.getItemList(page, rows);
	}
	
	/**
	 * 添加商品，保存商品描述、规格参数
	 * @param item
	 * @param desc
	 * @param itemParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	private YamResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		
		return itemService.createItem(item, desc, itemParams);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
