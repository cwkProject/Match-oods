package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import com.port.ocean.shipping.data.IdentityInfoData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.util.BroadcastUtil;

/**
 * 获取身份信息任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class PullIdentityInfo extends DefaultWorkModel<String, IdentityInfoData, IdentityInfoData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return !(parameters == null || parameters.length < 1 || parameters[0] == null);
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.IDENTITY_INFO_URL;
    }

    @Override
    protected IdentityInfoData onRequestSuccessSetResult(IdentityInfoData data) {
        return data;
    }

    @Override
    protected IdentityInfoData onCreateDataModel(String... parameters) {
        IdentityInfoData data = new IdentityInfoData();
        data.setAccount(parameters[0]);

        return data;
    }

    @Override
    protected void onFinish() {
        BroadcastUtil.sendBroadcast(GlobalApplication.getGlobal(), StaticValue.BroadcastAction
                .USER_INFO_CHANGE_TAG);
    }
}
