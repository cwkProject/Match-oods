package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/25.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.port.ocean.shipping.R;

/**
 * 车辆放行信息列表Item
 *
 * @author 超悟空
 * @version 1.0 2016/3/25
 * @since 1.0
 */
public class VehiclePassedViewHolder extends RecyclerView.ViewHolder {

    /**
     * 车牌号
     */
    public TextView licensePlateNumberTextView = null;

    /**
     * 位置
     */
    public TextView locationTextView = null;

    /**
     * 场地
     */
    public TextView storageTextView = null;

    /**
     * 通过时间
     */
    public TextView auditTimeTextView = null;

    public VehiclePassedViewHolder(View itemView) {
        super(itemView);
        licensePlateNumberTextView = (TextView) itemView.findViewById(R.id
                .vehicle_passed_item_license_plate_number_textView);

        locationTextView = (TextView) itemView.findViewById(R.id
                .vehicle_passed_item_location_textView);

        storageTextView = (TextView) itemView.findViewById(R.id
                .vehicle_passed_item_storage_textView);

        auditTimeTextView = (TextView) itemView.findViewById(R.id
                .vehicle_passed_item_audit_time_textView);
    }
}
