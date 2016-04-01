package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2016/3/24.
 */

import com.port.ocean.shipping.bean.VehiclePassed;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.data.base.SimpleJsonDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 车辆放行信息数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/24
 * @since 1.0
 */
public class VehiclePassedData extends SimpleJsonDataModel {

    /**
     * 起始行
     */
    private String startRow = null;

    /**
     * 行数
     */
    private String count = null;

    /**
     * 车牌号
     */
    private String licensePlateNumber = null;

    /**
     * 用户id
     */
    private String userId = null;

    /**
     * 放行信息
     */
    private List<VehiclePassed> dataList = null;

    /**
     * 设置起始行数
     *
     * @param startRow 开始行
     */
    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    /**
     * 设置获取条数
     *
     * @param count 要获取的条数
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 设置过滤车牌号
     *
     * @param licensePlateNumber 车牌号
     */
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取车辆放行信息
     *
     * @return 放行信息列表
     */
    public List<VehiclePassed> getDataList() {
        return dataList;
    }

    @Override
    protected void onExtractData(JSONObject jsonData) throws Exception {
        JSONArray jsonArray = jsonData.getJSONArray(DATA_TAG);

        dataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray row = jsonArray.getJSONArray(i);

            if (row.length() > 4) {
                VehiclePassed data = new VehiclePassed();
                data.setLicensePlateNumber(row.getString(0));
                data.setLocation(row.getString(1));
                data.setStorage(row.getString(2));
                data.setAuditTime(row.getString(3));
                data.setAttention("1".equals(row.getString(4)));

                dataList.add(data);
            }
        }
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        dataMap.put("StartRow", startRow);
        dataMap.put("Count", count);
        dataMap.put("VehicleNum", licensePlateNumber);
        dataMap.put("CodeUser", userId);
    }
}
