package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/29.
 */

import android.util.Log;

import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 发布空车数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/29
 * @since 1.0
 */
public class PublishEmptyCarData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "EmptyCarData.";

    /**
     * 用户ID
     */
    private String account = null;

    private String SFDProvince = null;

    private String SFDCity = null;

    private String MDDProvince = null;

    private String MDDCity = null;

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

    public void setSFDProvince(String SFDProvince) {
        this.SFDProvince = SFDProvince;
    }

    public void setSFDCity(String SFDCity) {
        this.SFDCity = SFDCity;
    }

    public void setMDDProvince(String MDDProvince) {
        this.MDDProvince = MDDProvince;
    }

    public void setMDDCity(String MDDCity) {
        this.MDDCity = MDDCity;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setFavorable(String favorable) {
        this.favorable = favorable;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("CodeUser", account);
        Log.i(LOG_TAG + "serialization", "CodeUser is " + account);
        dataMap.put("SFDProvince", SFDProvince);
        Log.i(LOG_TAG + "serialization", "SFDProvince is " + SFDProvince);
        dataMap.put("SFDCity", SFDCity);
        Log.i(LOG_TAG + "serialization", "SFDCity is " + SFDCity);
        dataMap.put("MDDProvince", MDDProvince);
        Log.i(LOG_TAG + "serialization", "MDDProvince is " + MDDProvince);
        dataMap.put("MDDCity", MDDCity);
        Log.i(LOG_TAG + "serialization", "MDDCity is " + MDDCity);
        dataMap.put("BackMark", back);
        Log.i(LOG_TAG + "serialization", "BackMark is " + back);
        dataMap.put("favorableMark", favorable);
        Log.i(LOG_TAG + "serialization", "favorableMark is " + favorable);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = handleResult.getString("IsReg");

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
