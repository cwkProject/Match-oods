package com.port.ocean.shipping.bean;
/**
 * Created by 超悟空 on 2016/3/29.
 */

/**
 * 车辆信息数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/29
 * @since 1.0
 */
public class Vehicle {

    /**
     * 车辆id
     */
    private String id = null;

    /**
     * 车牌号
     */
    private String licensePlateNumber = null;

    /**
     * 获取车辆id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置车辆id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

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
}
