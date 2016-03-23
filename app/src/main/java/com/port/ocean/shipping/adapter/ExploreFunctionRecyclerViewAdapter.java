package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.FunctionItem;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.List;

/**
 * 功能列表数据适配器
 *
 * @author 超悟空
 * @version 1.0 2016/3/15
 * @since 1.0
 */
public class ExploreFunctionRecyclerViewAdapter extends RecyclerView
        .Adapter<ExploreFunctionItemViewHolder> {

    /**
     * 功能列表数据源
     */
    private List<FunctionItem> dataList = null;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<FunctionItem>,
            ExploreFunctionItemViewHolder> onItemClickListener = null;

    /**
     * 构造函数
     *
     * @param dataList 功能列表数据源
     */
    public ExploreFunctionRecyclerViewAdapter(List<FunctionItem> dataList) {
        this.dataList = dataList;
    }

    /**
     * 设置功能项点击事件
     *
     * @param onItemClickListener 事件监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<FunctionItem>,
            ExploreFunctionItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ExploreFunctionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .explore_function_item, parent, false);

        return new ExploreFunctionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ExploreFunctionItemViewHolder holder, int position) {
        FunctionItem item = dataList.get(position);

        holder.titleTextView.setText(item.getTitle());
        holder.iconImageView.setImageResource(item.getImageId());

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
