package com.yam.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbItemParam;
import com.yam.service.ItemParamService;

/**
 * 
 * 商品规格参数控制器
 * @author Yampery
 * @date 2017年3月5日 下午7:50:12
 */
@RestController
@RequestMapping("/item/param")
public class ItemParamController {

	@Resource ItemParamService service;
	
	/**
	 * 查询商品规格参数信息
	 * @param itemCatId 商品类目id
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	public YamResult getItemParamByCid(@PathVariable Long itemCatId) {
		
		return service.getItemParamByCid(itemCatId);
	} 
	
	@RequestMapping("/save/{cid}")
	public YamResult insertItemParam(
			@PathVariable Long cid, String paramData) {
		// 1. 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		YamResult result = service.inserItemParam(itemParam);
		
		return result;
	}
	
	
}
