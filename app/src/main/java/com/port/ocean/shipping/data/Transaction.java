package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/29.
 */

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * 交易订单数据结构
 *
 * @author 超悟空
 * @version 1.0 2015/6/29
 * @since 1.0
 */
public class Transaction implements Comparator<Transaction>, Serializable {

    private String id = null;

    private String start = null;

    private String end = null;

    private String insurance = null;

    private String goodsId = null;

    private String orderNum = null;

    private String amount = null;

    private String createTime = null;

    private String dealState = null;

    private String goods = null;

    private String weight = null;

    private String volume = null;

    private String vehicleLength = null;

    private String vehicleType = null;

    private String debitName = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDebitName() {
        return debitName;
    }

    public void setDebitName(String debitName) {
        this.debitName = debitName;
    }

    @Override
    public int compare(Transaction lhs, Transaction rhs) {
        Transaction lhsTransaction = lhs;
        Transaction rhsTransaction = rhs;

        if ("1".equals(lhsTransaction.getDealState()) && "0".equals(rhsTransaction.getDealState())) {
            return 1;
        }
        if ("0".equals(lhsTransaction.getDealState()) && "1".equals(rhsTransaction.getDealState())) {
            return -1;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date lhsDate = sdf.parse(lhsTransaction.getCreateTime());
            Date rhsDate = sdf.parse(rhsTransaction.getCreateTime());
            return lhsDate.compareTo(rhsDate);
        } catch (ParseException e) {
            return 0;
        }
    }

}
