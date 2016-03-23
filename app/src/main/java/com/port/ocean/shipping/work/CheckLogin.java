package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import com.port.ocean.shipping.data.LoginData;
import com.port.ocean.shipping.util.MemoryValue;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 登录任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class CheckLogin extends DefaultWorkModel<String, String, LoginData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 1 && parameters[0] != null &&
                parameters[1] != null;
    }

    @Override
    protected void onParameterError(String... parameters) {
        setResult(GlobalApplication.getGlobal().getString(org.mobile.library.R.string
                .login_error_parameter));
        // 发送广播
        sendBroadcast();
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.LOGIN_URL;
    }

    @Override
    protected String onParseFailedSetResult(LoginData data) {
        // 发送广播
        sendBroadcast();
        return GlobalApplication.getGlobal().getString(org.mobile.library.R.string
                .login_error_field_required);
    }

    @Override
    protected String onRequestFailedSetResult(LoginData data) {
        // 发送广播
        sendBroadcast();
        return data.getMessage();
    }

    @Override
    protected String onRequestSuccessSetResult(LoginData data) {
        // 登录成功设置参数
        MemoryValue config = MemoryValue.getMemoryValue();
        config.setLogin(true);
        config.setUserID(data.getUserID());
        // 发送广播
        sendBroadcast();
        return data.getMessage();
    }

    @Override
    protected LoginData onCreateDataModel(String... parameters) {
        // 新建登录数据对象
        LoginData data = new LoginData();
        data.setUserName(parameters[0]);
        data.setPassword(parameters[1]);

        return data;
    }

    /**
     * 发送广播
     */
    private void sendBroadcast() {
//        BroadcastUtil.sendBroadcast(GlobalApplication.getGlobal(), BroadcastUtil
//                .MEMORY_STATE_LOGIN);
    }
}
