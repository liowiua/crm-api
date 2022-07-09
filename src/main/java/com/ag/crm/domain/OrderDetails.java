package com.ag.crm.domain;

import java.util.Date;

public class OrderDetails {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.id
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.order_id
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.goods_name
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private String goodsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.goods_num
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Integer goodsNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.unit
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private String unit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.price
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Float price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.sum
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Float sum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.is_valid
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Integer isValid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.create_date
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_details.update_date
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.id
     *
     * @return the value of t_order_details.id
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.id
     *
     * @param id the value for t_order_details.id
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.order_id
     *
     * @return the value of t_order_details.order_id
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.order_id
     *
     * @param orderId the value for t_order_details.order_id
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.goods_name
     *
     * @return the value of t_order_details.goods_name
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.goods_name
     *
     * @param goodsName the value for t_order_details.goods_name
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.goods_num
     *
     * @return the value of t_order_details.goods_num
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.goods_num
     *
     * @param goodsNum the value for t_order_details.goods_num
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.unit
     *
     * @return the value of t_order_details.unit
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.unit
     *
     * @param unit the value for t_order_details.unit
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.price
     *
     * @return the value of t_order_details.price
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.price
     *
     * @param price the value for t_order_details.price
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.sum
     *
     * @return the value of t_order_details.sum
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Float getSum() {
        return sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.sum
     *
     * @param sum the value for t_order_details.sum
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setSum(Float sum) {
        this.sum = sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.is_valid
     *
     * @return the value of t_order_details.is_valid
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.is_valid
     *
     * @param isValid the value for t_order_details.is_valid
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.create_date
     *
     * @return the value of t_order_details.create_date
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.create_date
     *
     * @param createDate the value for t_order_details.create_date
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_details.update_date
     *
     * @return the value of t_order_details.update_date
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_details.update_date
     *
     * @param updateDate the value for t_order_details.update_date
     *
     * @mbg.generated Wed Jun 15 17:09:56 CST 2022
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
