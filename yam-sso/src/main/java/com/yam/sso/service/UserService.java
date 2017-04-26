package com.yam.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbUser;

/**
 * 
 * 用户管理服务
 * @author Yampery
 * @date 2017年3月27日 上午1:04:06
 */
public interface UserService {

	/**
	 * 数据检查
	 * @param content 内容
	 * @param type 内容类型
	 * @return
	 */
	YamResult checkData(String content, Integer type);
	
	/**
	 * 用户注册，创建用户
	 * @param user 用户对象
	 * @return
	 */
	YamResult createUser(TbUser user);
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	YamResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response);

	/**
	 * 通过令牌token在缓存中查看用户
	 * @param token 用户令牌
	 * @return
	 */
	YamResult getUserByToken(String token);
}
