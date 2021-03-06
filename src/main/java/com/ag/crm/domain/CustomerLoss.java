package com.ag.crm.domain;

import java.util.Date;

public class CustomerLoss {

    private Integer id;

    private String cusNo;

    private Date lastOrderTime;

    private Date confirmLossTime;

    private Integer state;

    private String lossReason;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.id
     *
     * @param id the value for t_customer_loss.id
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.cus_no
     *
     * @return the value of t_customer_loss.cus_no
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public String getCusNo() {
        return cusNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.cus_no
     *
     * @param cusNo the value for t_customer_loss.cus_no
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.last_order_time
     *
     * @return the value of t_customer_loss.last_order_time
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public Date getLastOrderTime() {
        return lastOrderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.last_order_time
     *
     * @param lastOrderTime the value for t_customer_loss.last_order_time
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setLastOrderTime(Date lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.confirm_loss_time
     *
     * @return the value of t_customer_loss.confirm_loss_time
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public Date getConfirmLossTime() {
        return confirmLossTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.confirm_loss_time
     *
     * @param confirmLossTime the value for t_customer_loss.confirm_loss_time
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setConfirmLossTime(Date confirmLossTime) {
        this.confirmLossTime = confirmLossTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.state
     *
     * @return the value of t_customer_loss.state
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.state
     *
     * @param state the value for t_customer_loss.state
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.loss_reason
     *
     * @return the value of t_customer_loss.loss_reason
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public String getLossReason() {
        return lossReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.loss_reason
     *
     * @param lossReason the value for t_customer_loss.loss_reason
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setLossReason(String lossReason) {
        this.lossReason = lossReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.create_date
     *
     * @return the value of t_customer_loss.create_date
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.create_date
     *
     * @param createDate the value for t_customer_loss.create_date
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer_loss.update_date
     *
     * @return the value of t_customer_loss.update_date
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer_loss.update_date
     *
     * @param updateDate the value for t_customer_loss.update_date
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
