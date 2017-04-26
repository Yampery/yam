package com.yam.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 页面控制
 * @author Yampery
 * @date 2017年3月28日 上午12:07:47
 */
@Controller
@RequestMapping("page")
public class PageController {

	/**
	 * 展示注册页面
	 * @return
	 */
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	/**
	 * 跳转登录界面
	 * @return
	 */
	@RequestMapping("/showLogin")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
