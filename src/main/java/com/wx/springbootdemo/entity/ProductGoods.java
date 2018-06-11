package com.wx.springbootdemo.entity;

import java.util.Date;

public class ProductGoods {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.id
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.name
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.category
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private Integer category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.describe
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private String describe;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.pic
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private String pic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.create_user_id
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private Integer createUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.create_time
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_s_product_goods.update_time
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.id
     *
     * @return the value of t_s_product_goods.id
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.id
     *
     * @param id the value for t_s_product_goods.id
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.name
     *
     * @return the value of t_s_product_goods.name
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.name
     *
     * @param name the value for t_s_product_goods.name
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.category
     *
     * @return the value of t_s_product_goods.category
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.category
     *
     * @param category the value for t_s_product_goods.category
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.describe
     *
     * @return the value of t_s_product_goods.describe
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.describe
     *
     * @param describe the value for t_s_product_goods.describe
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.pic
     *
     * @return the value of t_s_product_goods.pic
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.pic
     *
     * @param pic the value for t_s_product_goods.pic
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.create_user_id
     *
     * @return the value of t_s_product_goods.create_user_id
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.create_user_id
     *
     * @param createUserId the value for t_s_product_goods.create_user_id
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.create_time
     *
     * @return the value of t_s_product_goods.create_time
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.create_time
     *
     * @param createTime the value for t_s_product_goods.create_time
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_s_product_goods.update_time
     *
     * @return the value of t_s_product_goods.update_time
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_s_product_goods.update_time
     *
     * @param updateTime the value for t_s_product_goods.update_time
     *
     * @mbg.generated Fri Jun 08 08:55:59 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}