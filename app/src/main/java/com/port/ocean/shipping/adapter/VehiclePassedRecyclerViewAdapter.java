package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/25.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.VehiclePassed;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆放行信息数据适配器
 *
 * @author 超悟空
 * @version 1.0 2016/3/25
 * @since 1.0
 */
public class VehiclePassedRecyclerViewAdapter extends RecyclerView
        .Adapter<VehiclePassedViewHolder> {

    /**
     * 数据源列表
     */
    private List<VehiclePassed> dataList = null;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<VehiclePassed>, VehiclePassedViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public VehiclePassedRecyclerViewAdapter() {
        this(null);
    }

    /**
     * 构造函数
     *
     * @param dataList 数据源
     */
    public VehiclePassedRecyclerViewAdapter(List<VehiclePassed> dataList) {
        this.dataList = dataList;

        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
    }

    /**
     * 设置Item点击事件
     *
     * @param onItemClickListener 监听器实例
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<VehiclePassed
            >, VehiclePassedViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 在列表尾部添加一组数据
     *
     * @param dataList 数据集
     */
    public void add(List<VehiclePassed> dataList) {
        int position = this.dataList.size();
        this.dataList.addAll(dataList);
        this.notifyItemRangeInserted(position, dataList.size());
    }

    /**
     * 清空数据
     */
    public void clear() {
        int position = this.dataList.size();
        this.dataList.clear();
        this.notifyItemRangeRemoved(0, position);
    }

    @Override
    public VehiclePassedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .vehicle_passed_item, parent, false);

        // 创建Item布局管理器
        return new VehiclePassedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VehiclePassedViewHolder holder, int position) {
        VehiclePassed data = this.dataList.get(position);

        holder.licensePlateNumberTextView.setText(data.getLicensePlateNumber());
        holder.locationTextView.setText(data.getLocation());
        holder.storageTextView.setText(data.getStorage());
        holder.auditTimeTextView.setText(data.getAuditTime());

        if (data.isAttention()) {
            holder.licensePlateNumberTextView.setTextColor(holder.itemView.getResources()
                    .getColor(android.R.color.holo_blue_dark));
        } else {
            holder.licensePlateNumberTextView.setTextColor(Color.BLACK);
        }

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(dataList, holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
