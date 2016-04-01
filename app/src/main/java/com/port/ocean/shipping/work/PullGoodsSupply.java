package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import com.port.ocean.shipping.bean.Goods;
import com.port.ocean.shipping.data.GoodsSupplyData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

import java.util.List;

/**
 * 获取货源列表任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class PullGoodsSupply extends DefaultWorkModel<String, List<Goods>, GoodsSupplyData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 1;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.FIND_GOODS_URL;
    }

    @Override
    protected List<Goods> onRequestSuccessSetResult(GoodsSupplyData data) {
        return data.getGoodsList();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected GoodsSupplyData onCreateDataModel(String... parameters) {
        GoodsSupplyData data = new GoodsSupplyData();
        data.setStartRow(parameters[0]);
        data.setCount(parameters[1]);
        data.setSearchKey(parameters.length > 2 ? parameters[2] : null);
        data.setStartProvince(parameters.length > 3 ? parameters[3] : null);
        data.setStartCity(parameters.length > 4 ? parameters[4] : null);
        data.setStartDistrict(parameters.length > 5 ? parameters[5] : null);
        data.setEndProvince(parameters.length > 6 ? parameters[6] : null);
        data.setEndCity(parameters.length > 7 ? parameters[7] : null);
        data.setEndDistrict(parameters.length > 8 ? parameters[8] : null);
        data.setGoodsType(parameters.length > 9 ? parameters[9] : null);
        data.setVehicleType(parameters.length > 10 ? parameters[10] : null);

        return data;
    }
}
