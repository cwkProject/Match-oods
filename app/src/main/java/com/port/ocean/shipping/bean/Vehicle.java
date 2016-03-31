package com.port.ocean.shipping.bean;
/**
 * Created by 超悟空 on 2016/3/29.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 车辆信息数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/29
 * @since 1.0
 */
public class Vehicle implements Parcelable {

    /**
     * 车辆id
     */
    private String id = null;

    /**
     * 车牌号
     */
    private String licensePlateNumber = null;

    /**
     * 增加时间(关联时间)
     */
    private String addTime = null;

    public Vehicle() {
    }

    protected Vehicle(Parcel in) {
        id = in.readString();
        licensePlateNumber = in.readString();
        addTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(licensePlateNumber);
        dest.writeString(addTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

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

    /**
     * 获取关联时间
     *
     * @return 关联时间
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * 设置关联时间
     *
     * @param addTime 关联时间
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
