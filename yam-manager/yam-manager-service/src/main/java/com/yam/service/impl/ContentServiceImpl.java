package com.yam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yam.common.pojo.EUDataGridResult;
import com.yam.common.pojo.YamResult;
import com.yam.common.utils.HttpClientUtil;
import com.yam.mapper.TbContentCategoryMapper;
import com.yam.mapper.TbContentMapper;
import com.yam.pojo.TbContent;
import com.yam.pojo.TbContentCategory;
import com.yam.pojo.TbContentExample;
import com.yam.pojo.TbContentExample.Criteria;
import com.yam.service.ContentService;

/**
 * 
 * 内容管理实现
 * @author Yampery
 * @date 2017年3月7日 下午10:33:21
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Resource
	private TbContentMapper mapper;
	@Resource
	private TbContentCategoryMapper categoryMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	
	@Override
	public EUDataGridResult getContentList(int page, int rows, long id) {
		
		
		// 1. 创建查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		
		PageHelper.startPage(page, rows);
		
		// 2. 执行查询
		List<TbContent> list = mapper.selectByExample(example);
		
		// 3. 创建一个返回值列表
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		
		// 获取记录总数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}
	
	@Override
	public YamResult insertContent(TbContent content) {
		
		// 1. 创建一个Content对象，设置属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		
		// 2. 插入
		mapper.insert(content);
		
		// 添加缓存处理				--2017年3月9日 by Yampery
		try {
			HttpClientUtil.doGet(REST_BASE_URL + 
					REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return YamResult.ok();
	}

	

}
