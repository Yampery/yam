package com.yam.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yam.portal.pojo.SearchResult;
import com.yam.portal.service.SearchService;

/**
 * 
 * 商品搜索
 * @author Yampery
 * @date 2017年3月17日 上午1:37:26
 */

@Controller
public class SearchController {

	@Resource private SearchService service;
	
	@RequestMapping("/search")
	public String search(
			@RequestParam("q") String queryString, 
			@RequestParam(defaultValue="1")Integer page, Model model) {
		
		try {
			if (null != queryString)
				queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 执行查询
		SearchResult searchResult = service.search(queryString, page);
		
		// 像页面传递参数
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", searchResult.getCurPage());
		
		return "search";
	}
}


















