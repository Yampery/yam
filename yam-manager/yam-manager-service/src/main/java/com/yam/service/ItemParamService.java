package com.yam.service;

import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbItemParam;

/**
 * 
 * 商品规格参数模板服务
 * @author Yampery
 * @date 2017年3月5日 下午7:33:01
 */
public interface ItemParamService {
	
	/**
	 * 通过商品类目id查询商品规格参数
	 * @param cid 商品类目id
	 * @return
	 */
	YamResult getItemParamByCid(long cid);
	
	/**
	 * 插入商品规格参数
	 * @param itemParam 商品规格参数
	 * @return
	 */
	YamResult inserItemParam(TbItemParam itemParam);
}
