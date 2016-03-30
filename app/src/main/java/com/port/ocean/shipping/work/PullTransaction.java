package com.port.ocean.shipping.work;
/**
 * Created by 超悟空 on 2015/6/30.
 */

import com.port.ocean.shipping.data.Transaction;
import com.port.ocean.shipping.data.TransactionData;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.work.DefaultWorkModel;

import java.util.List;

/**
 * 获取司机交易列表
 *
 * @author 超悟空
 * @version 1.0 2015/6/30
 * @since 1.0
 */
public class PullTransaction extends DefaultWorkModel<String, List<Transaction>, TransactionData> {

    @Override
    protected boolean onCheckParameters(String... parameters) {
        return parameters != null && parameters.length > 0;
    }

    @Override
    protected String onTaskUri() {
        return StaticValue.Url.CURRENT_TRANSACTION_URL;
    }

    @Override
    protected List<Transaction> onRequestSuccessSetResult(TransactionData data) {
        return data.getTransactionList();
    }

    @Override
    protected TransactionData onCreateDataModel(String... parameters) {
        TransactionData data = new TransactionData();
        data.setAccount(parameters[0]);

        return data;
    }
}
