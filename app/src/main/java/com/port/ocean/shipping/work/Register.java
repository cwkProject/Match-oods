package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.RegisterData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 注册任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class Register extends DefaultWorkModel<String, String, RegisterData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 3 || parameters[0] == null ||
                parameters[1] == null || parameters[2] == null);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.REGISTER_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(RegisterData data) {
        return data.getMessage();
    }

    @Override
    protected void onParameterError(String... parameters) {
        setResult(GlobalApplication.getGlobal().getString(R.string.info_incomplete));
    }

    @Override
    protected String onRequestFailedSetResult(RegisterData data) {
        return data.getMessage();
    }

    @Override
    protected String onParseFailedSetResult(RegisterData data) {
        return GlobalApplication.getGlobal().getString(R.string.info_incomplete);
    }

    @Override
    protected RegisterData onCreateDataModel(String... parameters) {
        // 新建注册数据对象
        RegisterData data = new RegisterData();
        data.setUserName(parameters[0]);
        data.setPassword1(parameters[1]);
        data.setPassword2(parameters[2]);

        return data;
    }
}
