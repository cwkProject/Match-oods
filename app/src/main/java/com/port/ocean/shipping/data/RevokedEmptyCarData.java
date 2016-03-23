package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/7/2.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 撤销发布空车的数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/7/2
 * @since 1.0
 */
public class RevokedEmptyCarData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "RevokedEmptyCarData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
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
        String resultState = jsonResult.getString("IsRepeal");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject jsonResult) throws JSONException {
        return jsonResult.getString("Message");
    }

    @Override
    protected void onRequestSuccess(JSONObject jsonResult) throws JSONException {

    }
}
