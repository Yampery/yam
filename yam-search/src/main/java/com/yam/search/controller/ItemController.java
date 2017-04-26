package com.yam.search.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.search.service.ItemServcice;

/**
 * 
 * Solr索引维护
 * @author Yampery
 * @date 2017年3月14日 下午11:36:02
 */
@RestController
@RequestMapping("/manager")
public class ItemController {

	@Resource
	private ItemServcice service;
	
	/**
	 * 导入所有商品数据到索引库
	 * @return
	 */
	@RequestMapping("/importAll")
	public YamResult importAllItems() {
		return service.importAllItems();
	}
}


















