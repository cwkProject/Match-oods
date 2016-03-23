package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.port.ocean.shipping.R;


/**
 * 查找货源Item的View管理器
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class GoodsFindItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 起点路线文本
     */
    public TextView routeStartTextView = null;

    /**
     * 终点路线文本
     */
    public TextView routeEndTextView = null;

    /**
     * 货名文本
     */
    public TextView goodsNameTextView = null;

    /**
     * 重量文本
     */
    public TextView weightTextView = null;

    /**
     * 车型文本
     */
    public TextView vehicleTypeTextView = null;

    /**
     * 距离文本
     */
    public TextView distanceTextView = null;

    /**
     * 电话按钮
     */
    public ImageButton callButton = null;

    /**
     * 时间
     */
    public TextView timeTextView = null;

    /**
     * 联系人
     */
    public TextView contactTextView = null;

    /**
     * 内容文本布局
     */
    public LinearLayout contentLayout = null;

    /**
     * 构造函数
     *
     * @param itemView 根布局
     */
    public GoodsFindItemViewHolder(View itemView) {
        super(itemView);

        // 设置子控件
        routeStartTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_find_route_start_textView);
        routeEndTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_find_route_end_textView);
        goodsNameTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_find_goods_name_textView);
        weightTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_find_goods_weight_textView);
        distanceTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_find_distance_textView);
        vehicleTypeTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_find_goods_vehicle_type_textView);
        contactTextView = (TextView) itemView.findViewById(R.id.goods_supply_find_contact_textView);
        callButton = (ImageButton) itemView.findViewById(R.id.goods_supply_find_call_button);
        timeTextView = (TextView) itemView.findViewById(R.id.goods_supply_find_time_textView);
        contentLayout = (LinearLayout) itemView.findViewById(R.id.goods_supply_find_content_layout);
    }
}
