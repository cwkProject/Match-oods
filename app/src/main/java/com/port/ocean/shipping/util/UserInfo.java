package com.port.ocean.shipping.util;

import org.mobile.library.model.config.TemporaryConfigModel;

/**
 * 用户信息
 *
 * @author 超悟空
 * @version 1.0 2016/3/24
 * @since 1.0
 */
public class UserInfo extends TemporaryConfigModel {

    private static UserInfo ourInstance = new UserInfo();

    /**
     * 用户姓名
     */
    private String realName = null;

    public static UserInfo getInstance() {
        return ourInstance;
    }

    private UserInfo() {
    }

    @Override
    protected void onCreate() {
        realName = null;
    }

    /**
     * 获取用户真实姓名
     *
     * @return 用户姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置用户真实姓名
     *
     * @param realName 用户姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }
}
