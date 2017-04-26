package com.yam.search.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.ExceptionUtil;
import com.yam.search.pojo.SearchResult;
import com.yam.search.service.SearchService;

/**
 * 
 * 商品搜索Controller
 * @author Yampery
 * @date 2017年3月15日 上午1:43:44
 */
@RestController
public class SearchController {

	@Resource private SearchService service;
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	public YamResult search(
			@RequestParam("q") String queryString,
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="60") Integer rows) {
		
		// 查询结果对象
		SearchResult result;
		
		// 查询条件不能为null
		if (StringUtils.isBlank(queryString))
			return YamResult.build(400, "查询条件不能为空！");
				
		try {
			// 处理get乱码
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			result = service.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		// 返回
		return YamResult.ok(result);
	}
	
}















