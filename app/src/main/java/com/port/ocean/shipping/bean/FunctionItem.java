package com.port.ocean.shipping.bean;
/**
 * Created by 超悟空 on 2016/3/15.
 */

/**
 * 功能项数据模型
 *
 * @author 超悟空
 * @version 1.0 2016/3/15
 * @since 1.0
 */
public class FunctionItem {

    /**
     * 功能标题
     */
    private String title = null;

    /**
     * 功能图标id
     */
    private int imageId = 0;

    /**
     * 构造函数
     *
     * @param title   标题
     * @param imageId 图标id
     */
    public FunctionItem(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    /**
     * 获取功能标题
     *
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 获取功能图标ID
     *
     * @return 图标ID
     */
    public int getImageId() {
        return imageId;
    }
}
