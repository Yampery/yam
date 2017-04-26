package com.yam.sso.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.ExceptionUtil;
import com.yam.pojo.TbUser;
import com.yam.sso.service.UserService;

/**
 * 
 * 用户管理Controller
 * @author Yampery
 * @date 2017年3月27日 下午9:35:23
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource private UserService service;
	
	/**
	 * 参数校验
	 * @param param 参数
	 * @param type 参数类型
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	public Object checkData(@PathVariable String param, 
					@PathVariable Integer type, String callback) {
		
		YamResult result = null;
		
		// 参数有效性校验
		if (StringUtils.isBlank(param))
			result = YamResult.build(400, "校验内容不能为空！");
		if (null == type) 
			result = YamResult.build(400, "校验内容类型不能为空！");
		if (1 != type && 2 != type && 3 != type)
			result = YamResult.build(400, "校验内容类型错误");
		
		// 表示校验出错
		if (null != result) {
			// 如果有回调，则需要返回jsonp
			if (null != callback) {
				MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
				jacksonValue.setJsonpFunction(callback);
				return jacksonValue;
			} else {
				return result;
			}	
		}
			 	
		try {
			// 调用服务
			result = service.checkData(param, type);
		} catch (Exception e) {
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		// 如果有回调，则需要返回jsonp
		if (null != callback) {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		} else {
			return result;
		}
	}
	
	/**
	 * 用户注册
	 * @param user 用户pojo参数对象
	 * @return
	 */
	@RequestMapping("/register")
	public YamResult createUser(TbUser user) {
		
		try {
			YamResult result = service.createUser(user);
			return result;
		} catch (Exception e) {
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}			
	}
	
	/**
	 * 用户登录请求控制器
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public YamResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			YamResult result = service.userLogin(username, password, request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return YamResult.build(400, ExceptionUtil.getStackTrace(e));
		}	
	}
	
	/**
	 * 通过token获取用户
	 * @param token 令牌
	 * @return
	 */
	@RequestMapping("/token/{token}")
	public Object getUserbyToken(@PathVariable String token, 
					String callback) {
		
		YamResult result = null;
		
		try {
			result = service.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
		// 支持jsonp
		// 判断callback如果为空
		if (StringUtils.isBlank(callback))
			return result;
		// 如果不为空
		else {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		}
	}
}

















