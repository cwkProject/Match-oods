package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/7/2.
 */

import com.port.ocean.shipping.data.RevokedEmptyCarData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 撤销发布空车任务
 *
 * @author 超悟空
 * @version 1.0 2015/7/2
 * @since 1.0
 */
public class RevokedEmptyCar extends DefaultWorkModel<String, String, RevokedEmptyCarData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 1);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.REVOKED_EMPTY_CAR;
    }

    @Override
    protected String onRequestSuccessSetResult(RevokedEmptyCarData data) {
        return data.getMessage();
    }

    @Override
    protected String onRequestFailedSetResult(RevokedEmptyCarData data) {
        return data.getMessage();
    }

    @Override
    protected RevokedEmptyCarData onCreateDataModel(String... parameters) {
        RevokedEmptyCarData revokedEmptyCarData = new RevokedEmptyCarData();
        revokedEmptyCarData.setAccount(parameters[0]);
        return revokedEmptyCarData;
    }
}
