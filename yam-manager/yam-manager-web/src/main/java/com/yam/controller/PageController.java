package com.yam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 页面控制器
 * @author Yampery
 * @date 2017年3月1日 上午1:01:41
 */
@Controller
public class PageController {

	/**
	 * 展示Index主页
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		
		return "index";
	}
	
	/**
	 * 展示页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		
		return page;
	}
}
