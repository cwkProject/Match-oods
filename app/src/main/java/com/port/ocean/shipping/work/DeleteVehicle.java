package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2016/3/31.
 */

import com.port.ocean.shipping.data.VehicleInfoData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.network.factory.NetworkType;

/**
 * 删除已关联车辆
 *
 * @author 超悟空
 * @version 1.0 2016/3/31
 * @since 1.0
 */
public class DeleteVehicle extends DefaultWorkModel<String, Void, VehicleInfoData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 0;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.DELETE_VEHICLE_URL;
    }

    @Override
    protected NetworkType onNetworkType() {
        return NetworkType.HTTP_POST;
    }

    @Override
    protected Void onRequestSuccessSetResult(VehicleInfoData data) {
        return null;
    }

    @Override
    protected VehicleInfoData onCreateDataModel(String... parameters) {
        VehicleInfoData data = new VehicleInfoData();

        data.setVehicleId(parameters[0]);
        return data;
    }
}
