package com.yam.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yam.mapper.TbItemCatMapper;
import com.yam.pojo.TbItemCat;
import com.yam.pojo.TbItemCatExample;
import com.yam.pojo.TbItemCatExample.Criteria;
import com.yam.rest.pojo.CatNode;
import com.yam.rest.pojo.CatResult;
import com.yam.rest.service.ITemCatService;

@Service
public class ItemCatServiceImpl implements ITemCatService {

	@Resource
	private TbItemCatMapper mapper;
	
	@Override
	public CatResult getItemCatList() {
		
		CatResult result = new CatResult();
		result.setData(getCatList(0));
		
		return result;
	}
	
	/**
	 * 查询分类列表
	 * <p>Title: getCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	/*private List<?> getCatList(long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = mapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList<>();
		//向list中添加节点
		// int count = 0;
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count ++;
				//第一层只取14条记录
				if (parentId == 0 && count >=14) {
					break;
				}
			//如果是叶子节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}*/
	
	/**
	 * 查询分类列表
	 * @param parentId 父节点
	 * @return
	 */
	private List<?> getCatList(long parentId) {
	
		// 定义一个计数器，控制层级，防止显示过多覆盖
		int count = 0;
		// 1. 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		// 2. 执行查询
		List<TbItemCat> list = mapper.selectByExample(example);
		
		// 3. 构造resultList返回
		List resultList = new ArrayList<>();
		// 向List中添加节点
		for (TbItemCat itemCat : list) { 
			if (itemCat.getIsParent()) { // 如果是父节点
				CatNode node = new CatNode();
				if (0 == parentId) // parentId如果为0需要a标签
					node.setName("<a href='/products/'" 
							+ itemCat.getId() + ".html>" + itemCat.getName() + "</a>");
				else
					node.setName(itemCat.getName());
				// 设置u属性
				node.setUrl("/products" + itemCat.getId() + ".html");
				node.setItem(getCatList(itemCat.getId()));
				
				resultList.add(node);
				
				count++;
				
				// 第一层级只取14个
				if (0 == parentId && 14 <= count)
					break;
				
			} else {	// 如果是叶子结点 
				resultList.add("/products" + itemCat.getId() + ".html|" + itemCat.getName());
			}
			
		} /// for end~		
		return resultList;
	}

}





















