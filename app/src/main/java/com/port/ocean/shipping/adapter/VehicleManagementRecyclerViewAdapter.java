package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/29.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.Vehicle;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆管理列表适配器
 *
 * @author 超悟空
 * @version 1.0 2016/3/29
 * @since 1.0
 */
public class VehicleManagementRecyclerViewAdapter extends RecyclerView
        .Adapter<VehicleManagementViewHolder> {

    /**
     * 底部加号布局
     */
    public static final int PLUS_ITEM_TYPE = 1;

    /**
     * 数据源集合
     */
    private List<Vehicle> dataList = null;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Vehicle>, VehicleManagementViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public VehicleManagementRecyclerViewAdapter() {
        this(null);
    }

    /**
     * 构造函数
     *
     * @param dataList 数据源
     */
    public VehicleManagementRecyclerViewAdapter(List<Vehicle> dataList) {
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
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<Vehicle>,
            VehicleManagementViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 在列表头部添加一组数据
     *
     * @param dataList 数据集
     */
    public void add(List<Vehicle> dataList) {
        this.dataList.addAll(0, dataList);
        this.notifyItemRangeInserted(0, dataList.size());
    }

    /**
     * 在列表尾部添加一条数据
     *
     * @param data 数据
     */
    public void add(Vehicle data) {
        int position = this.dataList.size();
        this.dataList.add(data);
        this.notifyItemInserted(position);
    }

    /**
     * 在指定位置添加一条数据
     *
     * @param data     数据
     * @param position 指定位置
     */
    public void add(Vehicle data, int position) {
        this.dataList.add(position, data);
        this.notifyItemInserted(position);
    }

    /**
     * 移除一条数据
     *
     * @param position 索引
     */
    public void remove(int position) {
        this.dataList.remove(position);
        this.notifyItemRemoved(position);
    }

    /**
     * 变更一条数据
     *
     * @param position 索引
     * @param data     新数据
     */
    public void change(int position, Vehicle data) {
        this.dataList.remove(position);
        this.dataList.add(position, data);
        this.notifyItemChanged(position);
    }

    @Override
    public VehicleManagementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView;

        if (viewType == PLUS_ITEM_TYPE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .vehicle_management_plus_item, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .vehicle_management_item, parent, false);
        }

        // 创建Item布局管理器
        return new VehicleManagementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VehicleManagementViewHolder holder, int position) {
        if (holder.getItemViewType() != PLUS_ITEM_TYPE) {
            Vehicle data = dataList.get(position);
            holder.textView.setText(data.getLicensePlateNumber());
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
    public int getItemViewType(int position) {
        if (dataList.get(position).getId() == null) {
            return PLUS_ITEM_TYPE;
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
