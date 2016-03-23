package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/7/1.
 */

import android.util.Log;

import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 付款数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/7/1
 * @since 1.0
 */
public class PaymentData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PaymentData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 订单ID
     */
    private String formId = null;

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 设置订单ID
     *
     * @param formId 订单号
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("Account", account);
        Log.i(LOG_TAG + "serialization", "Account is " + account);
        dataMap.put("OriginId", formId);
        Log.i(LOG_TAG + "serialization", "OriginId is " + formId);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = handleResult.getString("IsDeal");

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
