package com.port.ocean.shipping.bean;
/**
 * Created by 超悟空 on 2016/3/24.
 */

/**
 * 车辆放行信息数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/24
 * @since 1.0
 */
public class VehiclePassed {

    /**
     * 车牌号
     */
    private String licensePlateNumber = null;

    /**
     * 位置
     */
    private String location = null;

    /**
     * 场地
     */
    private String storage = null;

    /**
     * 通过时间
     */
    private String auditTime = null;

    /**
     * 标识是否为关注车辆
     */
    private boolean attention = false;

    /**
     * 获取车牌号
     *
     * @return 车牌号
     */
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    /**
     * 设置车牌号
     *
     * @param licensePlateNumber 车牌号
     */
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    /**
     * 获取位置
     *
     * @return 位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置位置
     *
     * @param location 位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取场地
     *
     * @return 场地
     */
    public String getStorage() {
        return storage;
    }

    /**
     * 设置场地
     *
     * @param storage 场地
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 获取通过时间
     *
     * @return 时间字符串
     */
    public String getAuditTime() {
        return auditTime;
    }

    /**
     * 设置通过时间
     *
     * @param auditTime 时间字符串
     */
    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 是否为关注车辆
     *
     * @return true表示关注车辆
     */
    public boolean isAttention() {
        return attention;
    }

    /**
     * 设置是否为关注车辆
     *
     * @param attention true表示关注车辆
     */
    public void setAttention(boolean attention) {
        this.attention = attention;
    }
}
