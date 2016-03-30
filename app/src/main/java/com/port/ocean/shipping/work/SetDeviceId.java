package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/7/10.
 */

import com.port.ocean.shipping.data.SetDeviceIdData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 设置服务器设备ID任务
 *
 * @author 超悟空
 * @version 1.0 2015/7/10
 * @since 1.0
 */
public class SetDeviceId extends DefaultWorkModel<String, String, SetDeviceIdData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 2);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.SET_DEVICE_ID_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(SetDeviceIdData data) {
        return data.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(SetDeviceIdData data) {
        return data.getMessage();
    }

    @Override
    protected SetDeviceIdData onCreateDataModel(String... parameters) {
        SetDeviceIdData setDeviceIdData = new SetDeviceIdData();
        setDeviceIdData.setUserId(parameters[0]);
        setDeviceIdData.setDeviceId(parameters[1]);
        //        setDeviceIdData.setDeviceType(ApplicationStaticValue.DEVICE_TYPE);
        setDeviceIdData.setAppCode(StaticValue.APP_CODE);
        return setDeviceIdData;
    }
}
