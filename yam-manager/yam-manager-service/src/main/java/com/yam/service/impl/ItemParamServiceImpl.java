package com.yam.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.mapper.TbItemParamMapper;
import com.yam.pojo.TbItemParam;
import com.yam.pojo.TbItemParamExample;
import com.yam.pojo.TbItemParamExample.Criteria;
import com.yam.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper mapper;
	@Override
	public YamResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		// 这里注意要使用WithBLOBs将大文本列包含
		List<TbItemParam> list = mapper.selectByExampleWithBLOBs(example);
		
		if (null != list && 0 < list.size())
			return YamResult.ok(list.get(0));
		return YamResult.ok();
	}
	
	@Override
	public YamResult inserItemParam(TbItemParam itemParam) {
		// 补全pojo属性
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		// 插入数据库
		mapper.insert(itemParam);
		
		return YamResult.ok();
	}

}
