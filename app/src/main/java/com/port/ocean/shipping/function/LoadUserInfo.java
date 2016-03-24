package com.port.ocean.shipping.function;


import com.port.ocean.shipping.data.IdentityInfoData;
import com.port.ocean.shipping.util.UserInfo;
import com.port.ocean.shipping.work.PullIdentityInfo;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;

/**
 * 加载用户数据
 *
 * @author 超悟空
 * @version 1.0 2015/7/8
 * @since 1.0
 */
public class LoadUserInfo {
    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "LoadUserInfo.";

    /**
     * 用户信息回调接口
     */
    public interface OnLoadUserInfoEndListener {
        /**
         * 加载用户信息结束回调
         *
         * @param state 执行结果
         */
        void onFinish(boolean state);
    }

    /**
     * 加载用户数据
     */
    public static void onLoadUserInfo() {
        onLoadUserInfo(null);
    }

    /**
     * 加载用户数据
     *
     * @param onLoadUserInfoEndListener 回调接口
     */
    public static void onLoadUserInfo(final OnLoadUserInfoEndListener onLoadUserInfoEndListener) {
        UserInfo.getInstance().Reset();

        // 新建任务
        PullIdentityInfo pullIdentityInfo = new PullIdentityInfo();

        pullIdentityInfo.setWorkEndListener(new WorkBack<IdentityInfoData>() {
            @Override
            public void doEndWork(boolean state, IdentityInfoData data) {
                if (state) {
                    // 填充数据
                    UserInfo.getInstance().setRealName(data.getUserName());
                }

                if (onLoadUserInfoEndListener != null) {
                    onLoadUserInfoEndListener.onFinish(state);
                }
            }
        });

        pullIdentityInfo.beginExecute(GlobalApplication.getLoginStatus().getUserID());

        // 尝试更新设备ID
        //onCheckDeviceId();
    }

    /**
     * 检测服务器设备ID并更新设备ID
     */
    //    public static void onCheckDeviceId() {
    //        if (!MemoryValue.getMemoryValue().isLogin()) {
    //            return;
    //        }
    //
    //        // 当前设备ID
    //        final String nowDeviceId = NotifyConfig.getNotifyConfig().getNotifyUserName();
    //
    //        Log.i(LOG_TAG + "onCheckDeviceId", "local device id is " + nowDeviceId);
    //
    //        // 获取服务器设备ID任务
    //        GetDeviceId getDeviceId = new GetDeviceId();
    //        getDeviceId.setWorkBackListener(new WorkBack<String>() {
    //            @Override
    //            public void doEndWork(boolean state, String data) {
    //
    //                Log.i(LOG_TAG + "onCheckDeviceId", "remote device id is " + data);
    //
    //                if (nowDeviceId != null && !nowDeviceId.equals(data)) {
    //                    // 获取成功并且服务器设备ID与本地设备ID不相同
    //                    // 更新设备ID到服务器
    //                    onUpdateDeviceId(nowDeviceId);
    //                }
    //            }
    //        });
    //
    //        getDeviceId.beginExecute(MemoryValue.getMemoryValue().getUserID());
    //    }
    //
    //    /**
    //     * 更新服务器设备ID
    //     */
    //    public static void onUpdateDeviceId(String newDeviceId) {
    //        if (!MemoryValue.getMemoryValue().isLogin()) {
    //            return;
    //        }
    //
    //        // 设置服务器设备ID任务
    //        SetDeviceId setDeviceId = new SetDeviceId();
    //
    //        setDeviceId.beginExecute(MemoryValue.getMemoryValue().getUserID(), newDeviceId);
    //    }
}