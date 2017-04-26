package com.yam.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.CookieUtils;
import com.yam.common.utils.JsonUtils;
import com.yam.mapper.TbUserMapper;
import com.yam.pojo.TbUser;
import com.yam.pojo.TbUserExample;
import com.yam.pojo.TbUserExample.Criteria;
import com.yam.sso.dao.JedisClient;
import com.yam.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource private TbUserMapper mapper;
	@Resource private JedisClient jedisClient;
	
	@Override
	public YamResult checkData(String content, Integer type) {
		
		/**
		 * 校验数据：对应表中primary key
		 * 1. username
		 * 2. phone
		 * 3. email
		 */
		// 创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (1 == type)
			criteria.andUsernameEqualTo(content);
		else if (2 == type)
			criteria.andPhoneEqualTo(content);
		else 
			criteria.andEmailEqualTo(content);
		
		// 执行查询
		List<TbUser> list = mapper.selectByExample(example);
		if (null == list || 1 > list.size())
			return YamResult.ok(true);
		
		return YamResult.ok(list);
	}

	@Override
	public YamResult createUser(TbUser user) {
		
		user.setCreated(new Date());
		user.setUpdated(new Date());
		/*
		 * 表单提交在controller中会自动转为对象
		 * user.setUsername(user.getUsername());
		 * user.setEmail(user.getEmail());*/
		// 使用spring提供的md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));		
		mapper.insert(user);
		
		return YamResult.ok();
	}
	
	
	// 用户session在redis中的id
	@Value("${USER_SESSION_KEY}")	private String USER_SESSION_KEY;
	@Value("${USER_SESSION_EXPIRE}") private Integer USER_SESSION_EXPIRE;
	
	@Override
	public YamResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {

		// 通过参数查询用户
		// 1. 创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		// 2. 执行查询
		List<TbUser> list = mapper.selectByExample(example);
		
		// 3. 判断输入
		if (null == list || 1 > list.size())
			return YamResult.build(400, "该用户不存在");
		
		TbUser user = list.get(0);
		
		// 4. 校验密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword()))
			return YamResult.build(400, "用户密码错误！");
		
		// 5. 如果以上步骤正确，生成令牌token
		String token = UUID.randomUUID().toString();
		
		// 为了安全，在保存用户信息之前将密码清空
		user.setPassword(null);
		
		// 6. 将用户信息写入redis缓存
		jedisClient.set(USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		// 设置过期时间
		jedisClient.expire(USER_SESSION_KEY + ":" + token, USER_SESSION_EXPIRE);
		
		// 2017-3-28 21:55:10-添加cookie逻辑
		// cookie的有效期-关闭浏览器失效
		CookieUtils.setCookie(request, response, "YAM_TOKEN", token);
		
		// 7. 返回token
		return YamResult.ok(token);
	}

	@Override
	public YamResult getUserByToken(String token) {

		// 通过token从redis中查询用户信息
		String json = jedisClient.get(USER_SESSION_KEY + ":" + token);
		
		if (StringUtils.isBlank(json))	// 如果结果为null
			return YamResult.build(400, "session为空");
		
		// 如果不为空，则更新过期时间
		jedisClient.expire(USER_SESSION_KEY + ":" + token, USER_SESSION_EXPIRE);
		
		return YamResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
}



















