package com.yam.sso.dao;

/**
 * 
 * 客户端对缓存的操作
 * @author Yampery
 * @date 2017年3月9日 下午9:35:44
 */
public interface JedisClient {

	String get(String key);
	
	String set(String key, String value);
	
	String hget(String hkey, String key);
	
	long hset(String hkey, String key, String value);
	
	long incr(String key);
	
	long expire(String key, int second);
	
	long ttl(String key);
	
	long del(String key);
	
	long hdel(String hkey, String key);
	
}
