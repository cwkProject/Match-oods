package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import com.port.ocean.shipping.data.CargoTypeData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

import java.util.List;

/**
 * 获取货物类型列表任务
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class PullCargoType extends DefaultWorkModel<String, List<String>, CargoTypeData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return true;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.CARGO_TYPE_LIST_URL;
    }

    @Override
    protected List<String> onRequestSuccessSetResult(CargoTypeData data) {
        return data.getDataList();
    }

    @Override
    protected CargoTypeData onCreateDataModel(String... parameters) {
        return new CargoTypeData();
    }
}
