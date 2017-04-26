package com.yam.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.utils.JsonUtils;
import com.yam.rest.pojo.CatResult;
import com.yam.rest.service.ITemCatService;

/**
 * 
 * 商品分类列表控制器
 * @author Yampery
 * @date 2017年3月6日 下午11:29:00
 */
@RestController
public class ItemCatController {

	@Resource
	private ITemCatService service;
	
	/*@RequestMapping(value="/itemcat/list", produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	public String getItemCatList(String callback) {
		
		// 1. 调用service查询分类列表
		CatResult catResult = service.getItemCatList();
		
		// 2. 将查询结果序列化为json
		String json = JsonUtils.objectToJson(catResult);
		
		// 3. 拼装返回结果
		String result = callback + "(" + json + ");";
		
		return result;
	}*/
	
	/**
	 * 商品分类列表
	 * @param callback
	 * @return
	 */
	@RequestMapping("/itemcat/list")
	public Object getItemCatList(String callback) {
		
		CatResult catResult = service.getItemCatList();
		MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
}











