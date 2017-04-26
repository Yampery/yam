package com.yam.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.JsonUtils;
import com.yam.mapper.TbItemDescMapper;
import com.yam.mapper.TbItemMapper;
import com.yam.mapper.TbItemParamItemMapper;
import com.yam.pojo.TbItem;
import com.yam.pojo.TbItemDesc;
import com.yam.pojo.TbItemParamItem;
import com.yam.pojo.TbItemParamItemExample;
import com.yam.pojo.TbItemParamItemExample.Criteria;
import com.yam.rest.dao.JedisClient;
import com.yam.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Resource private TbItemMapper itemMapper;
	@Resource private TbItemDescMapper itemDescMapper;
	@Resource private TbItemParamItemMapper itemParamItemMapper;
	
	@Resource
	private JedisClient jedisClient;
	
	@Override
	public YamResult getItemBaseInfo(long itemId) {
		
		// 添加缓存
		// 从缓存获取商品信息
		try {
			 String jsonResult = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			 if (!StringUtils.isBlank(jsonResult)) {
				 TbItem item = JsonUtils.jsonToPojo(jsonResult, TbItem.class);
				 return YamResult.ok(item);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		// 写入缓存  设置key的有效期
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			// 设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return YamResult.ok(item);
	}

	@Override
	public YamResult getItemDesc(long itemId) {
		
		// 添加缓存
		// 从缓存获取商品信息
		try {
			 String jsonResult = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			 if (!StringUtils.isBlank(jsonResult)) {
				 TbItemDesc desc = JsonUtils.jsonToPojo(jsonResult, TbItemDesc.class);
				 return YamResult.ok(desc);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 创建查询条件
		TbItemDesc desc = itemDescMapper.selectByPrimaryKey(itemId);
		
		// 写入缓存  设置key的有效期
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(desc));
			// 设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return YamResult.ok(desc);
	}

	@Override
	public YamResult getItemParam(long itemId) {
		
		// 添加缓存
		// 从缓存获取商品信息
		try {
			 String jsonResult = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			 if (!StringUtils.isBlank(jsonResult)) {
				 TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(jsonResult, TbItemParamItem.class);
				 return YamResult.ok(itemParamItem);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 设置查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (null != list && 0 < list.size()) {
			
			TbItemParamItem itemParamItem = list.get(0);
			// 写入缓存  设置key的有效期
			try {
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItem));
				// 设置过期时间
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return YamResult.ok(itemParamItem);
		}
		
		
		return YamResult.build(400, "无此商品规格");
	}

}

















