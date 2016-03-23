package com.port.ocean.shipping.function;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import android.content.Context;

import com.port.ocean.shipping.database.CargoTypeOperator;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.work.PullCargoType;

import org.mobile.library.model.database.BaseOperator;
import org.mobile.library.util.BroadcastUtil;

/**
 * 货物类型数据列表工具
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class CargoTypeListFunction extends BaseDataListFunction<String, String> {

    public CargoTypeListFunction(Context context) {
        super(context);
    }

    @Override
    protected BaseOperator<String> onCreateOperator(Context context) {
        return new CargoTypeOperator(context);
    }

    @Override
    protected void onLoadFromNetWork(String parameter) {
        PullCargoType workModel = new PullCargoType();

        boolean state = workModel.execute(parameter);

        netWorkEndSetData(state, workModel.getResult());
    }

    @Override
    protected void onNotify(Context context) {
        BroadcastUtil.sendBroadcast(context, StaticValue.DataListTag.CARGO_TYPE_LIST);
    }
}
