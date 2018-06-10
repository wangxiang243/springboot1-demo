package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.ProductGoods;
import com.wx.springbootdemo.mapper.ProductGoodsMapper;
import com.wx.springbootdemo.service.ProductGoodsService;
import com.wx.springbootdemo.util.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductGoodsServiceImpl implements ProductGoodsService {

    @Autowired
    private ProductGoodsMapper productGoodsMapper;

    @Override
    public Map selectProductGoodsList(Map param) {
        List<ProductGoods> productGoodsList = productGoodsMapper.selectProductGoodsList(param);
        Map result = PageView.getNoPageView(productGoodsList);
        return result;
    }

    @Override
    public Map selectProductGoodsPublishedList(Map param) {
        List<Map> productGoodsList = productGoodsMapper.selectProductGoodsPublishedList(param);
        Map result = PageView.getNoPageView(productGoodsList);
        return result;
    }

}
