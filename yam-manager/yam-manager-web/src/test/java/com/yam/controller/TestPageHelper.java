package com.yam.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yam.mapper.TbItemMapper;
import com.yam.pojo.TbItem;
import com.yam.pojo.TbItemExample;

/**
 * 
 * 分页插件测试
 * @author Yampery
 * @date 2017年3月1日 上午2:40:08
 */
public class TestPageHelper {
	
	/**
	 * 分页插件测试
	 */
	@Test
	public void testPageHelper() {
		// 1. 创建一个spring容器
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		// 2. 从spring容器中获取Mapper代理对象
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		// 3. 执行分页查询
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(1, 10);
		List<TbItem> list = mapper.selectByExample(example);
		for (TbItem item : list) {
			System.out.println(item.getTitle());
		}
		// 获取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品：" + total);
	}
}
