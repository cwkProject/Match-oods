package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/29.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.port.ocean.shipping.R;

/**
 * 车辆管理列表Item
 *
 * @author 超悟空
 * @version 1.0 2016/3/29
 * @since 1.0
 */
public class VehicleManagementViewHolder extends RecyclerView.ViewHolder {

    /**
     * 车牌文本
     */
    public TextView textView = null;

    public VehicleManagementViewHolder(View itemView) {
        super(itemView);
        if (itemView.getId() == R.id.vehicle_management_item_cardView) {
            textView = (TextView) itemView.findViewById(R.id.vehicle_management_item_textView);
        }
    }
}
