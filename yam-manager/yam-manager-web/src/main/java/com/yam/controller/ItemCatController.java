package com.yam.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.EUTreeNode;
import com.yam.service.ItemCatService;

/**
 * 
 * 商品类目选择前端控制层
 * @author Yampery
 * @date 2017年3月2日 上午1:04:16
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

	@Resource
	private ItemCatService service;
	
	@RequestMapping("/list")
	public List<EUTreeNode> getCatList(
			@RequestParam(value="id", defaultValue="0") long parentId) {
		
		return service.getCatList(parentId);
	}
}















