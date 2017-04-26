package com.yam.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.HttpClientUtil;
import com.yam.pojo.TbUser;
import com.yam.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${SSO_BASE_URL}")	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")	public String SSO_PAGE_LOGIN;
	
	@Override
	public TbUser getUserByToken(String token) {
		
		try {
			// 调用sso服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			YamResult result = YamResult.formatToPojo(json, TbUser.class);
			
			if (200 == result.getStatus()) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
