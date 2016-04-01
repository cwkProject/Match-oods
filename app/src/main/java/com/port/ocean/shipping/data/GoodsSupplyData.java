package com.port.ocean.shipping.data;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.util.Log;

import com.port.ocean.shipping.bean.Goods;
import com.port.ocean.shipping.util.TypeConvert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mobile.library.model.data.base.JsonDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 货源列表数据模型
 *
 * @author 超悟空
 * @version 2.0 2016/3/15
 * @since 1.0
 */
public class GoodsSupplyData extends JsonDataModel {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "GoodsSupplyData.";

    /**
     * 始发地省
     */
    private String startProvince = null;

    /**
     * 始发地市
     */
    private String startCity = null;

    /**
     * 始发地区
     */
    private String startDistrict = null;

    /**
     * 目的地省
     */
    private String endProvince = null;

    /**
     * 目的地市
     */
    private String endCity = null;

    /**
     * 目的地区
     */
    private String endDistrict = null;

    /**
     * 车型
     */
    private String vehicleType = null;

    /**
     * 货物类型
     */
    private String goodsType = null;

    /**
     * 搜索关键字
     */
    private String searchKey = null;

    /**
     * 起始行
     */
    private String startRow = null;

    /**
     * 行数
     */
    private String count = null;

    /**
     * 货源列表
     */
    private List<Goods> goodsList = new ArrayList<>();

    /**
     * 获取货源列表
     *
     * @return 货源列表
     */
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    /**
     * 设置始发地省
     *
     * @param startProvince 始发地省
     */
    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }

    /**
     * 设置始发地市
     *
     * @param startCity 始发地市
     */
    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    /**
     * 设置目的地省
     *
     * @param endProvince 目的地省
     */
    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince;
    }

    /**
     * 设置目的地市
     *
     * @param endCity 目的地市
     */
    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    /**
     * 设置车型
     *
     * @param vehicleType 车型
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 设置货物类型
     *
     * @param goodsType 货物类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 设置搜索关键词
     *
     * @param searchKey 关键词字符串
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * 设置始发地区
     *
     * @param startDistrict 始发地区
     */
    public void setStartDistrict(String startDistrict) {
        this.startDistrict = startDistrict;
    }

    /**
     * 设置目的地区
     *
     * @param endDistrict 目的地区
     */
    public void setEndDistrict(String endDistrict) {
        this.endDistrict = endDistrict;
    }

    /**
     * 设置起始行
     *
     * @param startRow 起始行索引
     */
    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    /**
     * 设置获取的行数
     *
     * @param count 行数
     */
    public void setCount(String count) {
        this.count = count;
    }

    @Override
    protected void onFillRequestParameters(Map<String, String> dataMap) {
        // 加入用户名和密码
        dataMap.put("SFDProvince", startProvince);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDProvince is " + startProvince);
        dataMap.put("SFDCity", startCity);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDCity is " + startCity);
        dataMap.put("SFDCounty", startDistrict);
        Log.i(LOG_TAG + "onFillRequestParameters", "SFDCounty is " + startDistrict);
        dataMap.put("MDDProvince", endProvince);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDProvince is " + endProvince);
        dataMap.put("MDDCity", endCity);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDCity is " + endCity);
        dataMap.put("MDDCounty", endDistrict);
        Log.i(LOG_TAG + "onFillRequestParameters", "MDDCounty is " + endDistrict);
        dataMap.put("StartRow", startRow);
        Log.i(LOG_TAG + "onFillRequestParameters", "StartRow is " + startRow);
        dataMap.put("Count", count);
        Log.i(LOG_TAG + "onFillRequestParameters", "Count is " + count);
        dataMap.put("Cargo", goodsType);
        Log.i(LOG_TAG + "onFillRequestParameters", "Cargo is " + goodsType);
        dataMap.put("VehicleType", vehicleType);
        Log.i(LOG_TAG + "onFillRequestParameters", "VehicleType is " + vehicleType);
        dataMap.put("KeyWord", searchKey);
        Log.i(LOG_TAG + "onFillRequestParameters", "KeyWord is " + searchKey);
    }

    @Override
    protected boolean onRequestResult(JSONObject handleResult) throws Exception {
        // 得到执行结果
        String resultState = TypeConvert.JsonArrayToString(handleResult.getJSONArray("IsFind"))[0];

        return resultState != null && "yes".equals(resultState.trim().toLowerCase());
    }

    @Override
    protected String onRequestMessage(boolean result, JSONObject handleResult) throws Exception {
        return null;
    }

    @Override
    protected void onRequestSuccess(JSONObject handleResult) throws Exception {
        if (!handleResult.isNull("GoodsReleasing")) {
            JSONArray jsonArray = handleResult.getJSONArray("GoodsReleasing");
            Log.i(LOG_TAG + "onRequestSuccess", "jsonArray count is " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                String[] goodsStrings = TypeConvert.JsonArrayToString(jsonArray.getJSONArray(i));
                Log.i(LOG_TAG + "onRequestSuccess", "goodsStrings count is " + goodsStrings.length);
                if (goodsStrings.length > 18) {
                    Goods goods = new Goods();
                    goods.setGoodsId(goodsStrings[0]);
                    goods.setStartProvince(goodsStrings[1]);
                    goods.setStartCity(goodsStrings[2]);
                    goods.setStartDistrict(goodsStrings[3]);
                    goods.setEndProvince(goodsStrings[4]);
                    goods.setEndCity(goodsStrings[5]);
                    goods.setEndDistrict(goodsStrings[6]);
                    goods.setGoodsName(goodsStrings[7]);
                    goods.setWeight(goodsStrings[8]);
                    goods.setVolume(goodsStrings[9]);
                    goods.setVehicleLength(goodsStrings[10]);
                    goods.setVehicleType(goodsStrings[11]);
                    goods.setMobile(goodsStrings[12]);
                    goods.setPhone(goodsStrings[13]);
                    goods.setPublishTime(goodsStrings[14]);
                    goods.setDistance(goodsStrings[15]);
                    goods.setContact(goodsStrings[16]);
                    goods.setStartDetailed(goodsStrings[17]);
                    goods.setEndDetailed(goodsStrings[18]);

                    goodsList.add(goods);
                }
            }
        }
    }
}
