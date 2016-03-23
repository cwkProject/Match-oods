package com.port.ocean.shipping.util;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import org.mobile.library.model.config.TemporaryConfigModel;

/**
 * 全局变量
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class MemoryValue extends TemporaryConfigModel {

    /**
     * 自身的静态对象
     */
    private static MemoryValue memoryValue = new MemoryValue();

    /**
     * 标记是否已登录
     */
    private boolean login = false;

    /**
     * 用户标识
     */
    private String userID = null;

    /**
     * 用户姓名
     */
    private String userName = null;

    /**
     * 私有构造函数
     */
    private MemoryValue() {
        super();
    }

    /**
     * 获取全局临时数据对象
     *
     * @return 数据对象
     */
    public static MemoryValue getMemoryValue() {
        return memoryValue;
    }

    @Override
    protected void onCreate() {
        // 初始化参数
        login = false;
        userID = null;
        userName = null;
    }

    /**
     * 判断是否登录
     *
     * @return 返回状态
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * 设置登录状态
     *
     * @param flag 状态标识
     */
    public void setLogin(boolean flag) {
        this.login = flag;
    }

    /**
     * 获取用户标识
     *
     * @return 用户标识串
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 设置用户标识
     *
     * @param userID 用户标识串
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * 获取用户姓名
     *
     * @return 姓名字符串
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 姓名字符串
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
