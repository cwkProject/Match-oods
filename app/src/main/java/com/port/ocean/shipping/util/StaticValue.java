package com.port.ocean.shipping.util;
/**
 * Created by 超悟空 on 2015/6/23.
 */

/**
 * 全局常量
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public interface StaticValue {

    /**
     * 应用代码
     */
    String APP_CODE = "SPHSJ";

    /**
     * 应用令牌
     */
    String APP_TOKEN = "2E4F0D6341BBE35CE053A8640169E35C";

    /**
     * 数据列表工具标签
     */
    interface DataListTag {
        /**
         * 货物类别标签
         */
        String CARGO_TYPE_LIST = "cargo_type_list";

        /**
         * 车型标签
         */
        String VEHICLE_TYPE_LIST = "vehicle_type_list";
    }

    /**
     * 意图数据传递标签
     */
    interface IntentTag {
        /**
         * 车辆详情取值标签
         */
        String VEHICLE_DETAIL_TAG = "vehicle_detail_tag";

        /**
         * 交易详情取值标签
         */
        String TRANSACTION_CONTENT_TAG = "transaction_content_tag";

        /**
         * 货源详情取值标签
         */
        String GOODS_SUPPLY_CONTENT_TAG = "goods_supply_content_tag";
    }

    /**
     * 网络请求接口地址
     */
    interface Url {
        /**
         * 获取货物类型列表的请求地址
         */
        String CARGO_TYPE_LIST_URL = "http://218.92.115.55/M_Sph/Base/GetCargoType.aspx";

        /**
         * 获取车型列表的请求地址
         */
        String VEHICLE_TYPE_URL = "http://218.92.115.55/M_Sph/Base/GetVehicleType.aspx";

        /**
         * 获取车辆放行信息请求地址
         */
        String VEHICLE_PASSED_URL = "http://218.92.115.55/M_Sph/Vehicle/GetVehiclePassed.aspx";

        /**
         * 获取服务器设备ID
         */
        String GET_DEVICE_ID_URL = "http://218.92.115.55/MobilePlatform/Device/GetDevice.aspx";

        /**
         * 设置服务器设备ID
         */
        String SET_DEVICE_ID_URL = "http://218.92.115.55/MobilePlatform/Device/AddDevice.aspx";

        /**
         * 获取发布中空车状态地址
         */
        String EMPTY_CAR_STATE = "http://218.92.115.55/M_Sph/Vehicle/GetVehicleReleasing.aspx";

        /**
         * 撤销发布空车的请求地址
         */
        String REVOKED_EMPTY_CAR = "http://218.92.115.55/M_Sph/Vehicle/RepealVehicle.aspx";

        /**
         * 付款地址
         */
        String PAYMENT_URL = "http://218.92.115.55/M_Sph/Deal/DealForDriver.aspx";

        /**
         * 网上车源页面地址
         */
        String CAR_FIELD = "http://218.92.115.55/M_Sph/Vehicle/OnlineVehicles.html";

        /**
         * 查看司机当前交易的地址
         */
        String CURRENT_TRANSACTION_URL = "http://218.92.115.55/M_Sph/Deal/DealInfoForDriver.aspx";

        /**
         * 发布空车的请求地址
         */
        String PUBLISH_EMPTY_CAR_URL = "http://218.92.115.55/M_Sph/Vehicle/ReleaseVehicle.aspx";

        /**
         * 查找货源
         */
        String FIND_GOODS_URL = "http://218.92.115.55/M_Sph/Goods/FindGoods2.aspx";

        /**
         * 司机身份认证地址
         */
        String IDENTITY_URL = "http://218.92.115.55/M_Sph/Auth/AuthForDriver.aspx";

        /**
         * 司机身份信息获取地址
         */
        String IDENTITY_INFO_URL = "http://218.92.115.55/M_Sph/UserInfo/InfoForDriver.aspx";

        /**
         * 增加关联车辆
         */
        String ADD_VEHICLE_URL = "http://218.92.115.55/M_Sph/Vehicle/RelateVehicle.aspx";

        /**
         * 获取已关联车辆信息
         */
        String VEHICLE_INFO_URL = "http://218.92.115.55/M_Sph/Vehicle/GetVehicleRelated.aspx";

        /**
         * 删除已关联车辆
         */
        String DELETE_VEHICLE_URL = "http://218.92.115.55/M_Sph/Vehicle/DeleteVehicleRelated.aspx";

        /**
         * 修改已关联车辆
         */
        String UPDATE_VEHICLE_URL = "http://218.92.115.55/M_Sph/Vehicle/UpdateVehicleRelated.aspx";
    }

    /**
     * 广播事件
     */
    interface BroadcastAction {
        /**
         * 用户信息变更
         */
        String USER_INFO_CHANGE_TAG = "user_info_change_tag";
    }
}
