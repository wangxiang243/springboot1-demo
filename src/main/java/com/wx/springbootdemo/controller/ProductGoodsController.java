package com.wx.springbootdemo.controller;

import com.wx.springbootdemo.entity.ProductGoods;
import com.wx.springbootdemo.service.ProductGoodsService;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/productgoods/")
public class ProductGoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductGoodsController.class);

    @Autowired
    private ProductGoodsService productGoodsService;

    @RequestMapping("selectProductGoodsList")
    @RequiresPermissions("goods:publish")
    public AjaxResult selectProductGoodsList(Map param) {
        Map result = productGoodsService.selectProductGoodsList(param);
        return AjaxResult.success(result, "查询产品成功");
    }

    @RequestMapping("selectProductGoodsPublishedList")
    @RequiresPermissions("goods:list")
    public AjaxResult selectProductGoodsPublishedList(Map param) {
        Map result = productGoodsService.selectProductGoodsPublishedList(param);
        return AjaxResult.success(result, "查询已发布商品成功");
    }

}
