package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/7/1.
 */

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.PaymentData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 付款任务
 *
 * @author 超悟空
 * @version 1.0 2015/7/1
 * @since 1.0
 */
public class Payment extends DefaultWorkModel<String, String, PaymentData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 2 || parameters[0] == null ||
                parameters[1] == null);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.PAYMENT_URL;
    }

    @Override
    protected String onRequestSuccessSetResult(PaymentData data) {
        return data.getMessage();
    }

    @Override
    protected String onParseFailedSetResult(PaymentData data) {
        return GlobalApplication.getGlobal().getString(R.string.info_incomplete);
    }

    @Override
    protected String onRequestFailedSetResult(PaymentData data) {
        return data.getMessage();
    }

    @Override
    protected void onParameterError(String... parameters) {
        setResult(GlobalApplication.getGlobal().getString(R.string.info_incomplete));
    }

    @Override
    protected PaymentData onCreateDataModel(String... parameters) {
        // 新建数据模型对象
        PaymentData data = new PaymentData();
        data.setAccount(parameters[0]);
        data.setFormId(parameters[1]);

        return data;
    }
}
