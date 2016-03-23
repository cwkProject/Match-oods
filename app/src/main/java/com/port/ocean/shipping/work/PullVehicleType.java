package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import com.port.ocean.shipping.data.VehicleTypeData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

import java.util.List;

/**
 * 获取车型列表的任务
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class PullVehicleType extends DefaultWorkModel<String, List<String>, VehicleTypeData> {
    @Override
    protected boolean onCheckParameters(String... parameters) {
        return true;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.VEHICLE_TYPE_URL;
    }

    @Override
    protected List<String> onRequestSuccessSetResult(VehicleTypeData data) {
        return data.getDataList();
    }

    @Override
    protected VehicleTypeData onCreateDataModel(String... parameters) {
        return new VehicleTypeData();
    }
}
