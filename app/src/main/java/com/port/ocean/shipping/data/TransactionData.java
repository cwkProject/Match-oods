package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/30.
 */

import android.util.Log;

import com.port.ocean.shipping.util.TypeConvert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 我的交易数据模型
 *
 * @author 超悟空
 * @version 1.0 2015/6/30
 * @since 1.0
 */
public class TransactionData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "TransactionData.";

    /**
     * 用户ID
     */
    private String account = null;

    /**
     * 货源列表
     */
    private List<Transaction> transactionList = new ArrayList<>();

    /**
     * 设置用户ID
     *
     * @param account 用户ID
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取交易列表
     *
     * @return
     */
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("Account", account);
        Log.i(LOG_TAG + "serialization", "Account is " + account);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = TypeConvert.JsonArrayToString(handleResult.getJSONArray
                ("IsDealingInfo"))[0];

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject handleResult) throws Exception {
        return null;
    }

    @Override
    protected void onRequestSuccess(JSONObject handleResult) throws Exception {
        JSONArray jsonArray = handleResult.getJSONArray("DealingInfo");
        Log.i(LOG_TAG + "parse", "jsonArray count is " + jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            String[] transactionStrings = TypeConvert.JsonArrayToString(jsonArray.getJSONArray(i));
            Log.i(LOG_TAG + "parse", "transactionStrings count is " + transactionStrings.length);
            if (transactionStrings.length >= 15) {
                Transaction transaction = new Transaction();
                transaction.setId(transactionStrings[0]);
                transaction.setStart(transactionStrings[1]);
                transaction.setEnd(transactionStrings[2]);
                transaction.setInsurance(transactionStrings[3]);
                transaction.setGoodsId(transactionStrings[4]);
                transaction.setOrderNum(transactionStrings[5]);
                transaction.setAmount(transactionStrings[6]);
                transaction.setCreateTime(transactionStrings[7]);
                transaction.setDealState(transactionStrings[8]);
                transaction.setGoods(transactionStrings[9]);
                transaction.setWeight(transactionStrings[10]);
                transaction.setVolume(transactionStrings[11]);
                transaction.setVehicleLength(transactionStrings[12]);
                transaction.setVehicleType(transactionStrings[13]);
                transaction.setDebitName(transactionStrings[14]);

                transactionList.add(transaction);
            }
        }
    }
}
