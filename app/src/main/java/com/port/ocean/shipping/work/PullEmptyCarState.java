package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/7/2.
 */

import com.port.ocean.shipping.data.EmptyCarStateData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 获取空车发布状态任务
 *
 * @author 超悟空
 * @version 1.0 2015/7/2
 * @since 1.0
 */
public class PullEmptyCarState extends DefaultWorkModel<String, EmptyCarStateData,
        EmptyCarStateData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 1);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.EMPTY_CAR_STATE;
    }

    @Override
    protected EmptyCarStateData onRequestSuccessSetResult(EmptyCarStateData data) {
        return data;
    }

    @Override
    protected EmptyCarStateData onRequestFailedSetResult(EmptyCarStateData data) {
        return null;
    }

    @Override
    protected EmptyCarStateData onCreateDataModel(String... parameters) {
        EmptyCarStateData emptyCarStateData = new EmptyCarStateData();
        emptyCarStateData.setAccount(parameters[0]);
        return emptyCarStateData;
    }
}
