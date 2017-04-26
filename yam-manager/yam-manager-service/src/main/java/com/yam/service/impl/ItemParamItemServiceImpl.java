package com.yam.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yam.common.utils.JsonUtils;
import com.yam.mapper.TbItemParamItemMapper;
import com.yam.pojo.TbItemParamItem;
import com.yam.pojo.TbItemParamItemExample;
import com.yam.pojo.TbItemParamItemExample.Criteria;
import com.yam.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper mapper;
	
	@Override
	public String getItemParambyItemId(Long itemId) {

		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		
		// 执行查询
		List<TbItemParamItem> list = mapper.selectByExampleWithBLOBs(example);
		
		if (null == list || 0 == list.size())
			return "";
		
		// 取出规格参数
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		// 生成HTML
		// 将规格参数json转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for(Map m1:jsonList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for(Map m2:list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
				sb.append("            <td>"+m2.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		
		return sb.toString();
	}
	
	
	
	

}












