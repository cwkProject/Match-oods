package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/7/10.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 获取服务器设备ID数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/7/10
 * @since 1.0
 */
public class GetDeviceIdData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "GetDeviceIdData.";

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 设备类型
     */
    private String deviceType = null;

    /**
     * 应用代码
     */
    private String appCode = null;

    /**
     * 设备ID
     */
    private String deviceId = null;

    /**
     * 设置手机号
     *
     * @param userId 手机号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 设置设备类型
     *
     * @param deviceType 设备类型
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 设置应用标识
     *
     * @param appCode 应用代码
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * 获取设备ID
     *
     * @return 设备ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 传入请求参数
        dataMap.put("CodeUser", userId);
        Log.i(LOG_TAG + "onFillRequestParameters", "CodeUser is " + userId);
        dataMap.put("DeviceType", deviceType);
        Log.i(LOG_TAG + "onFillRequestParameters", "DeviceType is " + deviceType);
        dataMap.put("AppName", appCode);
        Log.i(LOG_TAG + "onFillRequestParameters", "AppName is " + appCode);
    }

    @Override
    protected boolean onRequestResult(JSONObject jsonResult) throws JSONException {
        // 得到执行结果
        String resultState = jsonResult.getString("IsGet");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject jsonResult) throws JSONException {
        return result ? null : jsonResult.getString("Message");
    }

    @Override
    protected void onRequestSuccess(JSONObject jsonResult) throws JSONException {
        deviceId = jsonResult.getString("DeviceToken");
        Log.i(LOG_TAG + "onRequestSuccess", "DeviceToken is " + deviceId);
    }
}
