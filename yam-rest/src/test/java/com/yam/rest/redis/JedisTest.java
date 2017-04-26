package com.yam.rest.redis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 
 * Jedis测试
 * @author Yampery
 * @date 2017年3月9日 上午2:03:48
 */
public class JedisTest {

	/**
	 * 测试连接
	 */
	@Test
	public void testJedisSingle() {
		
		// 1. 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.1.130", 6379);
		
		// 2. 调用方法
		jedis.set("yam", "yampery");
		System.out.println(jedis.get("yam"));
		
		// 3. 关闭资源
		jedis.close();
	}
	
	/**
	 * 测试连接池
	 */
	public void testJedisPool() {
		
	}
	
	/**
	 * 集群测试
	 */
	@Test
	public void testJedisCluster() {
		
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.130", 7001));
		nodes.add(new HostAndPort("192.168.1.130", 7002));
		nodes.add(new HostAndPort("192.168.1.130", 7003));
		nodes.add(new HostAndPort("192.168.1.130", 7004));
		nodes.add(new HostAndPort("192.168.1.130", 7005));
		nodes.add(new HostAndPort("192.168.1.130", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("k1", "1000");
		System.out.println(cluster.get("k1"));
		cluster.close();
	}
	
	/**
	 * 测试spring-jedis整合
	 */
	@Test
	public void testSpringJedisSingle() {
		
		// 1. 加载配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-jedis.xml");
		//2. 连接池
		JedisPool pool = (JedisPool) context.getBean("redisClient");
		
		Jedis jedis = pool.getResource();
		jedis.set("k1", "spring");
		String string = jedis.get("k1");
		
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群-spring测试
	 */
	@Test
	public void testSpringJedisCluster() {
		
		// 1. 加载配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-jedis.xml");
		//2. 连接池
		JedisCluster cluster = (JedisCluster) context.getBean("redisClient");
	
		System.out.println(cluster.get("k1"));
		
		cluster.close();
	}
}












