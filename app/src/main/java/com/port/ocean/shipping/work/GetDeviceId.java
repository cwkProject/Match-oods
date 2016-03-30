package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/7/10.
 */

import com.port.ocean.shipping.data.GetDeviceIdData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 获取服务器设备ID任务
 *
 * @author 超悟空
 * @version 1.0 2015/7/10
 * @since 1.0
 */
public class GetDeviceId extends DefaultWorkModel<String, String, GetDeviceIdData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 1);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.GET_DEVICE_ID_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(GetDeviceIdData data) {
        return data.getDeviceId();
    }

    @Override
    protected String onRequestFailedSetResult(GetDeviceIdData data) {
        return data.getMessage();
    }

    @Override
    protected GetDeviceIdData onCreateDataModel(String... parameters) {
        GetDeviceIdData getDeviceIdData = new GetDeviceIdData();
        getDeviceIdData.setUserId(parameters[0]);
        //        getDeviceIdData.setDeviceType(ApplicationStaticValue.DEVICE_TYPE);
        getDeviceIdData.setAppCode(StaticValue.APP_CODE);
        return getDeviceIdData;
    }
}
