package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/6/29.
 */

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.PublishEmptyCarData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 发布空车任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/29
 * @since 1.0
 */
public class PublishEmptyCar extends DefaultWorkModel<String, String, PublishEmptyCarData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 7);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.PUBLISH_EMPTY_CAR_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(PublishEmptyCarData data) {
        return data.getMessage();
    }

    @Override
    protected void onParameterError(String... parameters) {
        setResult(GlobalApplication.getGlobal().getString(R.string.info_incomplete));
    }

    @Override
    protected String onParseFailedSetResult(PublishEmptyCarData data) {
        return GlobalApplication.getGlobal().getString(R.string.info_incomplete);
    }

    @Override
    protected String onRequestFailedSetResult(PublishEmptyCarData data) {
        return data.getMessage();
    }

    @Override
    protected PublishEmptyCarData onCreateDataModel(String... parameters) {
        PublishEmptyCarData data = new PublishEmptyCarData();
        data.setAccount(parameters[0]);
        data.setSFDProvince(parameters[1]);
        data.setSFDCity(parameters[2]);
        data.setMDDProvince(parameters[3]);
        data.setMDDCity(parameters[4]);
        data.setBack(parameters[5]);
        data.setFavorable(parameters[6]);

        return data;
    }
}
