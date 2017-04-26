package com.yam.portal.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yam.common.pojo.YamResult;
import com.yam.portal.service.ContentService;

@Controller
public class IndexController {

	@Resource
	private ContentService service;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = service.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
	
	/**
	 * 模拟post请求
	 * @return
	 */
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String testPost(String username, String password) {
		
		System.out.println("username: " + username + "\npassword: " + password);
		return "{username: " + username + "\npassword: " + password + "}";
	}
}
