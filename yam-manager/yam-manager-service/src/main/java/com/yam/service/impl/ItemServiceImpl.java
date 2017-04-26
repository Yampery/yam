package com.yam.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yam.common.pojo.EUDataGridResult;
import com.yam.common.pojo.YamResult;
import com.yam.common.utils.IDUtils;
import com.yam.mapper.TbItemDescMapper;
import com.yam.mapper.TbItemMapper;
import com.yam.mapper.TbItemParamItemMapper;
import com.yam.mapper.TbItemParamMapper;
import com.yam.pojo.TbItemExample.Criteria;
import com.yam.pojo.TbItemParamItem;
import com.yam.pojo.TbItem;
import com.yam.pojo.TbItemDesc;
import com.yam.pojo.TbItemExample;
import com.yam.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper descMapper;
	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	@Override
	public TbItem getItemById(long itemId) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (null != list && 0 < list.size()) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	
	/**
	 * 分页实现
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		
		TbItemExample example = new TbItemExample();
		
		// 分页处理
		PageHelper.startPage(page, rows);
		
		// 创建一个返回值列表
		List<TbItem> list = itemMapper.selectByExample(example);
		
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 获取记录总数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public YamResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		//1. 补全item字段
		// 生成商品id
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 设置状态
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		// 2. 插入数据库
		itemMapper.insert(item);
		
		// 添加商品描述
		YamResult result = insertItemDesc(itemId, desc);
		 
		if (200 != result.getStatus())
			throw new Exception();
		
		// 添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		
		if (200 != result.getStatus())
			throw new Exception();

		return YamResult.ok();
	}
	
	/**
	 * 添加商品描述
	 * @param itemId 商品id
	 * @param desc 商品描述
	 * @return
	 */
	private YamResult insertItemDesc(Long itemId, String desc) {
		
		TbItemDesc itemDesc = new TbItemDesc();
		
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDesc.setCreated(new Date());
		
		descMapper.insert(itemDesc);
		
		return YamResult.ok();
	}
	
	/**
	 * 添加规格参数
	 * @param itemId 商品id
	 * @param itemParam 规格参数
	 * @return
	 */
	private YamResult insertItemParamItem(
			Long itemId, String itemParam) {
		
		// 补全pojo属性
		TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setItemId(itemId);
		paramItem.setParamData(itemParam);
		paramItem.setCreated(new Date());
		paramItem.setUpdated(new Date());
		// 向表中插入数据
		paramItemMapper.insert(paramItem);
		
		return YamResult.ok();
	}

}












