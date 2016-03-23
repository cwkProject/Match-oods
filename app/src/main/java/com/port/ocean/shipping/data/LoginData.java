package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.util.Log;

import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.Map;

/**
 * 用户登录的数据模型类
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class LoginData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "LoginData.";

    /**
     * 用户输入的用户名
     */
    private String userName = null;

    /**
     * 用户输入的密码
     */
    private String password = null;

    /**
     * 登录成功后返回的用户唯一标识符
     */
    private String userID = null;

    /**
     * 获取用户的标识符
     *
     * @return 返回标识符字符串
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 设置用户密码
     *
     * @param password 密码字符串
     */
    public void setPassword(String password) {
        this.password = password;
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
        dataMap.put("Account", userName);
        Log.i(LOG_TAG + "serialization", "Account is " + userName);
        dataMap.put("Password", password);
        Log.i(LOG_TAG + "serialization", "Password is " + password);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = handleResult.getString("IsLogin");

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject handleResult) throws Exception {
        return result ? null : handleResult.getString("Message");
    }

    @Override
    protected void onRequestSuccess(JSONObject handleResult) throws Exception {
        userID = handleResult.getString("CodeUser");
    }
}