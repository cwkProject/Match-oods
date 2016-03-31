package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2016/3/31.
 */

import com.port.ocean.shipping.bean.Vehicle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.data.base.SimpleJsonDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息管理数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/31
 * @since 1.0
 */
public class VehicleInfoData extends SimpleJsonDataModel {

    /**
     * 用户id
     */
    private String userId = null;

    /**
     * 车辆id
     */
    private String vehicleId = null;

    /**
     * 车牌信息
     */
    private String licensePlateNumber = null;

    /**
     * 关联的车辆信息
     */
    private List<Vehicle> dataList = null;

    /**
     * 新增或修改后的车辆信息
     */
    private Vehicle data = null;

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 设置车辆id
     *
     * @param vehicleId 车辆id
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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
     * 获取已关联的车辆信息列表
     *
     * @return 车辆信息列表
     */
    public List<Vehicle> getDataList() {
        return dataList;
    }

    /**
     * 获取增加或修改后的新车辆信息
     *
     * @return 车辆信息
     */
    public Vehicle getData() {
        return data;
    }

    @Override
    protected void onExtractData(JSONObject jsonData) throws Exception {
        if (licensePlateNumber != null) {
            // 此为增加或修改关联车辆接口

            JSONArray jsonArray = jsonData.getJSONArray(DATA_TAG);
            if (jsonArray.length() > 2) {
                data = new Vehicle();
                data.setId(jsonArray.getString(0));
                data.setLicensePlateNumber(jsonArray.getString(1));
                data.setAddTime(jsonArray.getString(2));
            }
        } else {
            // 此为获取关联车辆信息接口
            JSONArray jsonArray = jsonData.getJSONArray(DATA_TAG);
            dataList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonRow = jsonArray.getJSONArray(i);
                if (jsonRow.length() > 2) {
                    data = new Vehicle();
                    data.setId(jsonRow.getString(0));
                    data.setLicensePlateNumber(jsonRow.getString(1));
                    data.setAddTime(jsonRow.getString(2));
                }
                dataList.add(data);
            }
        }
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        dataMap.put("CodeUser", userId);
        dataMap.put("VehicleNum", licensePlateNumber);
        dataMap.put("Id", vehicleId);
    }
}
