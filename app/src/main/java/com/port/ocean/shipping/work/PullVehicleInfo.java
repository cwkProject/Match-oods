package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2016/3/31.
 */

import com.port.ocean.shipping.bean.Vehicle;
import com.port.ocean.shipping.data.VehicleInfoData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.network.factory.NetworkType;

import java.util.List;

/**
 * 获取已关联车辆信息
 *
 * @author 超悟空
 * @version 1.0 2016/3/31
 * @since 1.0
 */
public class PullVehicleInfo extends DefaultWorkModel<String, List<Vehicle>, VehicleInfoData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 0;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.VEHICLE_INFO_URL;
    }

    @Override
    protected NetworkType onNetworkType() {
        return NetworkType.HTTP_POST;
    }

    @Override
    protected List<Vehicle> onRequestSuccessSetResult(VehicleInfoData data) {
        return data.getDataList();
    }

    @Override
    protected VehicleInfoData onCreateDataModel(String... parameters) {
        VehicleInfoData data = new VehicleInfoData();

        data.setUserId(parameters[0]);
        return data;
    }
}
