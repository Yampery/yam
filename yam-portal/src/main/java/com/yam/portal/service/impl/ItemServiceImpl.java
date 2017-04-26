package com.yam.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.HttpClientUtil;
import com.yam.common.utils.JsonUtils;
import com.yam.pojo.TbItemDesc;
import com.yam.pojo.TbItemParamItem;
import com.yam.portal.pojo.ItemInfo;
import com.yam.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${ITEM_INFO_URL}") private String ITEM_INFO_URL;
	@Value("${REST_BASE_URL}") private String REST_BASE_URL;
	@Value("${ITEM_DESC_URL}") private String ITEM_DESC_URL;
	@Value("${ITEM_PARAM_URL}") private String ITEM_PARAM_URL;
	
	@Override
	public ItemInfo getItemById(long itemId) {
		
		try {
			// 调用res服务
			String jsonResult = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			if (!StringUtils.isBlank(jsonResult)) {
				YamResult yamResult = YamResult.formatToPojo(jsonResult, ItemInfo.class);
				if (200 == yamResult.getStatus()) {
					ItemInfo item = (ItemInfo) yamResult.getData();
					return item;
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemDescById(long itemId) {
		try {
			// 查询商品描述
			String jsonResult = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
			// 转换成pojo
			YamResult yamResult = YamResult.formatToPojo(jsonResult, TbItemDesc.class);
			if (null != yamResult) {
				TbItemDesc desc = (TbItemDesc) yamResult.getData();
				// 去出商品描述信息
				String result = desc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getItemParamById(long itemId) {
		
		try {
			// 查询商品规格参数
			String jsonResult = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
			// 把json转换成java对象
			YamResult yamResult = YamResult.formatToPojo(jsonResult, TbItemParamItem.class);
			if (200 == yamResult.getStatus()) {
				TbItemParamItem itemParamItem = (TbItemParamItem) yamResult.getData();
				String paramData = itemParamItem.getParamData();
				
				// 生成HTML
				// 将规格参数json转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}

}



















