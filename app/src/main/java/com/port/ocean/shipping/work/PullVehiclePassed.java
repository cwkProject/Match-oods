package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2016/3/24.
 */

import com.port.ocean.shipping.bean.VehiclePassed;
import com.port.ocean.shipping.data.VehiclePassedData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

import java.util.List;

/**
 * 获取车辆放行信息任务
 *
 * @author 超悟空
 * @version 1.0 2016/3/24
 * @since 1.0
 */
public class PullVehiclePassed extends DefaultWorkModel<String, List<VehiclePassed>,
        VehiclePassedData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 1;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.VEHICLE_PASSED_URL;
    }

    @Override
    protected List<VehiclePassed> onRequestSuccessSetResult(VehiclePassedData data) {
        return data.getDataList();
    }

    @Override
    protected VehiclePassedData onCreateDataModel(String... parameters) {
        VehiclePassedData data = new VehiclePassedData();

        data.setStartRow(parameters[0]);
        data.setCount(parameters[1]);
        data.setLicensePlateNumber(parameters.length > 2 ? parameters[2] : null);

        return data;
    }
}
