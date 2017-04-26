package com.yam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yam.common.pojo.EUTreeNode;
import com.yam.common.pojo.YamResult;
import com.yam.mapper.TbContentCategoryMapper;
import com.yam.mapper.TbContentMapper;
import com.yam.pojo.TbContentCategory;
import com.yam.pojo.TbContentCategoryExample;
import com.yam.pojo.TbContentCategoryExample.Criteria;
import com.yam.service.ContentCategoryService;

/**
 * 
 * 树节点内容分类管理实现类
 * @author Yampery
 * @date 2017年3月2日 下午10:08:12
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Resource
	private TbContentCategoryMapper mapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		
		// 1. 根据父节点查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		// 2. 执行查询
		List<TbContentCategory> list = mapper.selectByExample(example);
		// 树节点列表
		List<EUTreeNode> nodeList = new ArrayList<>();
		
		// 3. 将节点列表转换为树
		for (TbContentCategory item : list) {
			// 新建一个节点
			EUTreeNode node = new EUTreeNode();
			// 设置节点属性
			node.setId(item.getId());
			node.setText(item.getName());
			node.setState(item.getIsParent() ? "closed" : "open");
			
			// 添加节点
			nodeList.add(node);
		}
		
		// 返回树节点列表
		return nodeList;
	}

	@Override
	public YamResult insertContentCategory(long parentId, String name) {
		
		// 1. 创建一个ContentCategory类，设置属性
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		// 默认设置不是父节点
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		// 2. 添加记录
		mapper.insert(contentCategory);
		
		// 3. 查看父节点状态是否为true，如果不是则将其父节点更新为true，如果是忽略
		TbContentCategory parentCat =  mapper.selectByPrimaryKey(parentId);
		
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			// 更新父节点
			mapper.updateByPrimaryKey(parentCat);
		}
		
		return YamResult.ok(contentCategory);
	}

	@Override
	public YamResult deleteContentCategory(long parentId, long id) {
		
		// 删除当前节点
		mapper.deleteByPrimaryKey(id);
		
		// 判断父节点是否还有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> child = mapper.selectByExample(example);
		
		// 如果删除后没有了子节点，则要更新其父节点状态为false
		if (null == child || 1 > child.size()) {
			// 执行修改
			TbContentCategory parentCat =  mapper.selectByPrimaryKey(parentId);
			parentCat.setIsParent(false);
			parentCat.setUpdated(new Date());
			mapper.updateByPrimaryKey(parentCat);
		}
		
		return YamResult.ok();
	}

	@Override
	public YamResult updateContentCategory(long id, String name) {
		
		// 将此节点查询出来
		TbContentCategory contentCategory = mapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		
		// 执行更新
		mapper.updateByPrimaryKey(contentCategory);
		return null;
	}

}


















