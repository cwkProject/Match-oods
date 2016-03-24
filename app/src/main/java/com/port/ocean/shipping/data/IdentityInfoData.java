package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import android.util.Log;

import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 司机身份信息数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class IdentityInfoData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "IdentityInfoData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 真实姓名
     */
    private String userName = null;

    /**
     * 身份证
     */
    private String identityCard = null;

    /**
     * 车牌号
     */
    private String vehicleNumber = null;

    /**
     * 车长
     */
    private String vehicleLength = null;

    /**
     * 车型
     */
    private String vehicleType = null;

    /**
     * 载重
     */
    private String tons = null;

    /**
     * 手机号
     */
    private String mobile = null;

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户真实姓名
     *
     * @return 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 获取身份证
     *
     * @return 身份证号
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 获取车牌
     *
     * @return 车牌号
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * 获取车长
     *
     * @return 车长
     */
    public String getVehicleLength() {
        return vehicleLength;
    }

    /**
     * 获取车型
     *
     * @return 车型
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 获取载重
     *
     * @return 载重
     */
    public String getTons() {
        return tons;
    }

    /**
     * 获取手机号
     *
     * @return 手机号
     */
    public String getMobile() {
        return mobile;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("CodeUser", account);
        Log.i(LOG_TAG + "serialization", "CodeUser is " + account);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = handleResult.getString("IsInfo");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject handleResult) throws Exception {
        return !result ? handleResult.getString("Message") : null;
    }

    @Override
    protected void onRequestSuccess(JSONObject handleResult) throws Exception {
        userName = handleResult.getString("UserName");
        identityCard = handleResult.getString("IdentityCard");
        vehicleNumber = handleResult.getString("VehicleNum");
        vehicleLength = handleResult.getString("VehicleLen");
        vehicleType = handleResult.getString("VehicleType");
        tons = handleResult.getString("Tons");
        mobile = handleResult.getString("Mobile");
    }
}
