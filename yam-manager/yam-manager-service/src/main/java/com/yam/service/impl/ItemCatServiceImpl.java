package com.yam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.EUTreeNode;
import com.yam.mapper.TbItemCatMapper;
import com.yam.pojo.TbItemCat;
import com.yam.pojo.TbItemCatExample;
import com.yam.pojo.TbItemCatExample.Criteria;
import com.yam.service.ItemCatService;

/**
 * 商品分类树节点查询实现类
 * @see ItemCatService
 * @author Yampery
 * @date 2017年3月2日 上午1:02:32
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		// 1. 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		// 2. 根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> nodeList = new ArrayList<>();
		
		// 3. 把列表转换成treeNodesList
		for (TbItemCat item : list) {
			// 创建一个节点
			EUTreeNode node = new EUTreeNode();
			// 根据sql查询的结果添加节点属性
			node.setId(item.getId());
			node.setText(item.getName());
			node.setState(item.getIsParent() ? "closed" : "open");
			// 将节点添加到结果集中
			nodeList.add(node);
		}
		
		// 4. 返回结果
		return nodeList;
	}

}
