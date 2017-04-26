package com.yam.portal.pojo;

import com.yam.pojo.TbItem;

/**
 * 
 * 商品信息
 * @author Yampery
 * @date 2017年3月20日 下午10:36:13
 */
public class ItemInfo extends TbItem {

	public String[] getImages() {
		
		String image = getImage();
		if (null != image) {
			String[] images = image.split(",");
			return images;
		}
		
		return null;
	}
}
