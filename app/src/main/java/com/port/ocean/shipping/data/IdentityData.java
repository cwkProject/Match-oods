package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/24.
 */

import android.util.Log;

import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 身份认证数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/24
 * @since 1.0
 */
public class IdentityData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "identityData.";

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
     * 认证结果
     */
    private boolean authenticate = false;

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 设置用户真实姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 设置身份证
     *
     * @param identityCard 身份证号
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * 设置车牌
     *
     * @param vehicleNumber 车牌号
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * 设置车长
     *
     * @param vehicleLength 车长
     */
    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    /**
     * 设置车型
     *
     * @param vehicleType 车型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 设置载重
     *
     * @param tons 载重
     */
    public void setTons(String tons) {
        this.tons = tons;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("CodeUser", account);
        Log.i(LOG_TAG + "serialization", "CodeUser is " + account);
        dataMap.put("UserName", userName);
        Log.i(LOG_TAG + "serialization", "UserName is " + userName);
        dataMap.put("IdentityCard", identityCard);
        Log.i(LOG_TAG + "serialization", "IdentityCard is " + identityCard);
        dataMap.put("VehicleNum", vehicleNumber);
        Log.i(LOG_TAG + "serialization", "VehicleNum is " + vehicleNumber);
        dataMap.put("VehicleLen", vehicleLength);
        Log.i(LOG_TAG + "serialization", "VehicleLen is " + vehicleLength);
        dataMap.put("VehicleType", vehicleType);
        Log.i(LOG_TAG + "serialization", "VehicleType is " + vehicleType);
        dataMap.put("Tons", tons);
        Log.i(LOG_TAG + "serialization", "Tons is " + tons);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = handleResult.getString("IsAuth");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject handleResult) throws Exception {
        return handleResult.getString("Message");
    }

    @Override
    protected void onRequestSuccess(JSONObject handleResult) throws Exception {

    }
}
