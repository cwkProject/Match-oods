package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/7/2.
 */

import android.util.Log;

import com.port.ocean.shipping.util.TypeConvert;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 获取空车发布状态的数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/7/2
 * @since 1.0
 */
public class EmptyCarStateData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "EmptyCarStateData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 车源ID
     */
    private String carId = null;

    private String startProvince = null;

    private String startCity = null;

    private String endProvince = null;

    private String endCity = null;

    private String back = null;

    private String favorable = null;

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    public String getFavorable() {
        return favorable;
    }

    public String getBack() {
        return back;
    }

    public String getEndCity() {
        return endCity;
    }

    public String getEndProvince() {
        return endProvince;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getStartProvince() {
        return startProvince;
    }

    public String getCarId() {
        return carId;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 传入请求参数
        dataMap.put("CodeUser", account);
        Log.i(LOG_TAG + "onFillRequestParameters", "CodeUser is " + account);
    }

    @Override
    protected boolean onRequestResult(JSONObject jsonResult) throws JSONException {
        // 得到执行结果
        String resultState = jsonResult.getJSONArray("IsGet").getString(0);

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject jsonResult) throws JSONException {
        return result ? null : jsonResult.getString("Message");
    }

    @Override
    protected void onRequestSuccess(JSONObject jsonResult) throws JSONException {
        String[] stateStrings = TypeConvert.JsonArrayToString(jsonResult.getJSONArray
                ("VehicleReleasing"));
        Log.i(LOG_TAG + "onRequestSuccess", "jsonArray count is " + stateStrings.length);
        if (stateStrings.length >= 7) {

            carId = stateStrings[0];
            startProvince = stateStrings[1];
            startCity = stateStrings[2];
            endProvince = stateStrings[3];
            endCity = stateStrings[4];
            back = stateStrings[5];
            favorable = stateStrings[6];
        }
    }
}
