package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.ProductGoods;

import java.util.List;
import java.util.Map;

public interface ProductGoodsService {

    /**
     * 查询所有的产品(已发布、未发布)
     * @param param
     * @return
     */
    Map selectProductGoodsList(Map param);

    /**
     * 查询已发布的商品
     * @param param
     * @return
     */
    Map selectProductGoodsPublishedList(Map param);

}
