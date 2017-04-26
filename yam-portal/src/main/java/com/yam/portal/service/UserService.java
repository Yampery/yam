package com.yam.portal.service;

import com.yam.pojo.TbUser;

/**
 * 
 * 用户管理服务
 * @author Yampery
 * @date 2017年3月28日 下午10:29:06
 */
public interface UserService {

	/**
	 * 通过cookie获取用户
	 * @param token cookie name
	 * @return
	 */
	TbUser getUserByToken(String token);
}
