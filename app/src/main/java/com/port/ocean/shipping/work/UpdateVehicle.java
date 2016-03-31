package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2016/3/31.
 */

import com.port.ocean.shipping.bean.Vehicle;
import com.port.ocean.shipping.data.VehicleInfoData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

/**
 * 修改车辆信息任务
 *
 * @author 超悟空
 * @version 1.0 2016/3/31
 * @since 1.0
 */
public class UpdateVehicle extends DefaultWorkModel<String, Vehicle, VehicleInfoData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 1;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.UPDATE_VEHICLE_URL;
    }

    @Override
    protected Vehicle onRequestSuccessSetResult(VehicleInfoData data) {
        return data.getData();
    }

    @Override
    protected VehicleInfoData onCreateDataModel(String... parameters) {
        VehicleInfoData data = new VehicleInfoData();

        data.setVehicleId(parameters[0]);
        data.setLicensePlateNumber(parameters[1]);

        return data;
    }
}
