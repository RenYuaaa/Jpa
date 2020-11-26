package com.ren.domain;

import javax.persistence.*;

/**
 * @author : renjiahui
 * @date : 2020/11/15 18:08
 * @desc : 客户的实体类
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    /**
     * @Id: 声明主键的配置
     * @GeneratedValue： 配置主键的生成策略
     *  strategy
     *      GenerationType.IDENTITY 自增，mysql
     *          底层数据库必须支持自动增长（底层数据库支持的自动增长方式，对ID自增）
     *      GenerationType.SEQUENCE 序列，orcal
     *          底层数据库必须支持序列
     *      GenerationType.TABLE jpa提供的一种机制，通过一张数据库表的形式帮助完成主键自增
     *      GenerationType.AUTO 由程序自动帮助完成主键生成策略
     *
     * Jpa
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT COMMENT '客户ID'")
    private Integer id;

    /**
     * 客户名称
     */
    @Column(name = "cust_name", columnDefinition = "VARCHAR(255) COMMENT '客户名称'")
    private String custName;

    /**
     * 客户来源
     */
    @Column(name = "cust_source", columnDefinition = "VARCHAR(255) COMMENT '客户来源'")
    private String custSource;

    /**
     * 客户等级
     */
    @Column(name = "cust_level", columnDefinition = "VARCHAR(255) COMMENT '客户等级'")
    private String custLevel;

    /**
     * 客户所属行业
     */
    @Column(name = "cust_insdustry", columnDefinition = "VARCHAR(255) COMMENT '客户所属行业'")
    private String custInsdustry;

    /**
     * 客户电话
     */
    @Column(name = "cust_phone", columnDefinition = "VARCHAR(255) COMMENT '客户电话'")
    private String custPhone;

    /**
     * 客户地址
     */
    @Column(name = "cust_address", columnDefinition = "VARCHAR(255) COMMENT '客户地址'")
    private String custAddress;

    public Customer() {
    }

    public Customer(Integer id, String custName, String custSource, String custLevel, String custInsdustry, String custPhone, String custAddress) {
        this.id = id;
        this.custName = custName;
        this.custSource = custSource;
        this.custLevel = custLevel;
        this.custInsdustry = custInsdustry;
        this.custPhone = custPhone;
        this.custAddress = custAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustInsdustry() {
        return custInsdustry;
    }

    public void setCustInsdustry(String custInsdustry) {
        this.custInsdustry = custInsdustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custInsdustry='" + custInsdustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
