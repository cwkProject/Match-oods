package com.port.ocean.shipping.bean;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import java.io.Serializable;

/**
 * 货源数据结构
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class Goods implements Serializable {

    /**
     * 货源ID
     */
    private String goodsId = null;

    /**
     * 始发地省
     */
    private String startProvince = null;

    /**
     * 始发地市
     */
    private String startCity = null;

    /**
     * 始发地区
     */
    private String startDistrict = null;

    /**
     * 始发地详细地址
     */
    private String startDetailed = null;

    /**
     * 目的地省
     */
    private String endProvince = null;

    /**
     * 目的地市
     */
    private String endCity = null;

    /**
     * 目的地区
     */
    private String endDistrict = null;

    /**
     * 目的地详细地址
     */
    private String endDetailed = null;

    /**
     * 距离
     */
    private String distance = null;

    /**
     * 货物名称
     */
    private String goodsName = null;

    /**
     * 重量
     */
    private String weight = null;

    /**
     * 体积
     */
    private String volume = null;

    /**
     * 所需车长
     */
    private String vehicleLength = null;

    /**
     * 所需车型
     */
    private String vehicleType = null;

    /**
     * 联系人
     */
    private String contact = null;

    /**
     * 手机号
     */
    private String mobile = null;

    /**
     * 座机号
     */
    private String phone = null;

    /**
     * 发布时间
     */
    private String publishTime = null;

    /**
     * 获取货源ID
     *
     * @return 货源ID
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置货源ID
     *
     * @param goodsId 货源ID
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取始发地省
     *
     * @return 始发地省
     */
    public String getStartProvince() {
        return startProvince;
    }

    /**
     * 设置始发地省
     *
     * @param startProvince 始发地省
     */
    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }

    /**
     * 获取始发地市
     *
     * @return 始发地市
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * 设置始发地市
     *
     * @param startCity 始发地市
     */
    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    /**
     * 获取始发地区
     *
     * @return 始发地区
     */
    public String getStartDistrict() {
        return startDistrict;
    }

    /**
     * 设置始发地区
     *
     * @param startDistrict 始发地区
     */
    public void setStartDistrict(String startDistrict) {
        this.startDistrict = startDistrict;
    }

    /**
     * 获取目的地省
     *
     * @return 目的地省
     */
    public String getEndProvince() {
        return endProvince;
    }

    /**
     * 设置目的地省
     *
     * @param endProvince 目的地省
     */
    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince;
    }

    /**
     * 获取目的地市
     *
     * @return 目的地市
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * 设置目的地市
     *
     * @param endCity 目的地市
     */
    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    /**
     * 获取目的地区
     *
     * @return 目的地区
     */
    public String getEndDistrict() {
        return endDistrict;
    }

    /**
     * 设置目的地区
     *
     * @param endDistrict 目的地区
     */
    public void setEndDistrict(String endDistrict) {
        this.endDistrict = endDistrict;
    }

    /**
     * 获取货物名称
     *
     * @return 货物名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置货物名称
     *
     * @param goodsName 货物名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取重量
     *
     * @return 重量
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置重量
     *
     * @param weight 重量
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 获取体积
     *
     * @return 体积
     */
    public String getVolume() {
        return volume;
    }

    /**
     * 设置体积
     *
     * @param volume 体积
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * 获取所需车长
     *
     * @return 所需车长
     */
    public String getVehicleLength() {
        return vehicleLength;
    }

    /**
     * 设置所需车长
     *
     * @param vehicleLength 所需车长
     */
    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    /**
     * 获取所需车型
     *
     * @return 所需车型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 设置所需车型
     *
     * @param vehicleType 所需车型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 获取手机号
     *
     * @return 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取座机号
     *
     * @return 座机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置座机号
     *
     * @param phone 座机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取发布时间
     *
     * @return 发布时间
     */
    public String getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishTime 发布时间
     */
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取始发地详细地址
     *
     * @return 详细地址
     */
    public String getStartDetailed() {
        return startDetailed;
    }

    /**
     * 设置始发地详细地址
     *
     * @param startDetailed 详细地址
     */
    public void setStartDetailed(String startDetailed) {
        this.startDetailed = startDetailed;
    }

    /**
     * 获取目的地详细地址
     *
     * @return 详细地址
     */
    public String getEndDetailed() {
        return endDetailed;
    }

    /**
     * 设置目的地详细地址
     *
     * @param endDetailed 详细地址
     */
    public void setEndDetailed(String endDetailed) {
        this.endDetailed = endDetailed;
    }

    /**
     * 获取距离
     *
     * @return 距离
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 设置距离
     *
     * @param distance 距离
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * 获取联系人
     *
     * @return 联系人姓名
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人姓名
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
}
