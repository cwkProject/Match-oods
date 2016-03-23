package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/24.
 */

import android.util.Log;

import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 注册数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/24
 * @since 1.0
 */
public class RegisterData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "RegisterData.";

    /**
     * 用户输入的用户名
     */
    private String userName = null;

    /**
     * 用户第一次输入的密码
     */
    private String password1 = null;

    /**
     * 用户第二次输入的密码
     */
    private String password2 = null;

    /**
     * 设置用户第一次输入的密码
     *
     * @param password1 密码字符串
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * 设置用户第二次输入的密码
     *
     * @param password2 密码字符串
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名字符串
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("Mobile", userName);
        Log.i(LOG_TAG + "serialization", "Mobile is " + userName);
        dataMap.put("Password1", password1);
        Log.i(LOG_TAG + "serialization", "Password1 is " + password1);
        dataMap.put("Password2", password2);
        Log.i(LOG_TAG + "serialization", "Password1 is " + password2);
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
