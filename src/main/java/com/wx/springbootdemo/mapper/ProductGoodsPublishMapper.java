package com.wx.springbootdemo.mapper;

import com.wx.springbootdemo.entity.ProductGoodsPublish;

public interface ProductGoodsPublishMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_s_product_goods_publish
     *
     * @mbg.generated Fri Jun 08 08:56:41 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_s_product_goods_publish
     *
     * @mbg.generated Fri Jun 08 08:56:41 CST 2018
     */
    int insert(ProductGoodsPublish record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_s_product_goods_publish
     *
     * @mbg.generated Fri Jun 08 08:56:41 CST 2018
     */
    int insertSelective(ProductGoodsPublish record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_s_product_goods_publish
     *
     * @mbg.generated Fri Jun 08 08:56:41 CST 2018
     */
    ProductGoodsPublish selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_s_product_goods_publish
     *
     * @mbg.generated Fri Jun 08 08:56:41 CST 2018
     */
    int updateByPrimaryKeySelective(ProductGoodsPublish record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_s_product_goods_publish
     *
     * @mbg.generated Fri Jun 08 08:56:41 CST 2018
     */
    int updateByPrimaryKey(ProductGoodsPublish record);
}