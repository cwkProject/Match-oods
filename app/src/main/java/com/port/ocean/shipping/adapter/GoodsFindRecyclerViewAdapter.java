package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.Goods;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

import static org.mobile.library.global.GlobalApplication.getGlobal;

/**
 * 货源查找列表数据适配器
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class GoodsFindRecyclerViewAdapter extends RecyclerView.Adapter<GoodsFindItemViewHolder> {

    /**
     * 消息列表
     */
    private List<Goods> goodsList = null;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsFindItemViewHolder>
            onItemClickListener = null;

    /**
     * 按钮点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsFindItemViewHolder>
            onButtonClickListener = null;

    /**
     * 空构造函数
     */
    public GoodsFindRecyclerViewAdapter() {
        this(null);
    }

    /**
     * 构造函数
     *
     * @param goodsList 初始化数据集
     */
    public GoodsFindRecyclerViewAdapter(List<Goods> goodsList) {
        this.goodsList = goodsList;

        if (this.goodsList == null) {
            this.goodsList = new ArrayList<>();
        }
    }

    /**
     * 在列表尾部添加一组数据
     *
     * @param goodsList 数据集
     */
    public void add(List<Goods> goodsList) {
        int position = this.goodsList.size();
        this.goodsList.addAll(goodsList);
        this.notifyItemRangeInserted(position, goodsList.size());
    }

    /**
     * 清空数据
     */
    public void clear() {
        int position = this.goodsList.size();
        this.goodsList.clear();
        this.notifyItemRangeRemoved(0, position);
    }

    /**
     * 设置数据集
     *
     * @param goodsList 数据集
     */
    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * 设置Item点击事件
     *
     * @param onItemClickListener 点击事件
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<Goods>,
            GoodsFindItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置按钮点击事件监听器
     *
     * @param onButtonClickListener 点击事件
     */
    public void setOnButtonClickListener(OnItemClickListenerForRecyclerViewItem<List<Goods>,
            GoodsFindItemViewHolder> onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public GoodsFindItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .goods_supply_find_item, viewGroup, false);

        // 创建Item布局管理器
        return new GoodsFindItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GoodsFindItemViewHolder goodsFindItemViewHolder, int
            position) {

        // 数据绑定
        Goods goods = goodsList.get(position);
        String start = null;

        if (goods.getStartDistrict().trim().length() != 0) {
            start = goods.getStartDistrict().trim();
        } else if (goods.getStartCity().trim().length() != 0) {
            start = goods.getStartCity().trim();
        } else if (goods.getStartProvince().trim().length() != 0) {
            start = goods.getStartProvince().trim();
        }

        String end = null;

        if (goods.getEndDistrict().trim().length() != 0) {
            end = goods.getEndDistrict().trim();
        } else if (goods.getEndCity().trim().length() != 0) {
            end = goods.getEndCity().trim();
        } else if (goods.getEndProvince().trim().length() != 0) {
            end = goods.getEndProvince().trim();
        }

        goodsFindItemViewHolder.routeStartTextView.setText(start);
        goodsFindItemViewHolder.routeEndTextView.setText(end);
        goodsFindItemViewHolder.goodsNameTextView.setText(goods.getGoodsName().trim());
        goodsFindItemViewHolder.contactTextView.setText(goods.getContact().trim());

        if (goods.getWeight().trim().length() != 0) {
            goodsFindItemViewHolder.weightTextView.setText(String.format("%s%s", goods.getWeight
                    ().trim(), getGlobal().getString(R.string.unit_quality_tons)));
        } else {
            goodsFindItemViewHolder.weightTextView.setText(null);
        }

        if (goods.getVehicleType().trim().length() != 0) {
            goodsFindItemViewHolder.vehicleTypeTextView.setText(goods.getVehicleType().trim());
        } else {
            goodsFindItemViewHolder.vehicleTypeTextView.setText(null);
        }

        if (goods.getDistance().trim().length() != 0) {
            goodsFindItemViewHolder.distanceTextView.setText(String.format("%s %s%s", getGlobal()
                    .getString(R.string.approximately), goods.getDistance().trim(), getGlobal()
                    .getString(R.string.unit_distance_kilometer)));
        } else {
            goodsFindItemViewHolder.distanceTextView.setText(null);
        }

        goodsFindItemViewHolder.timeTextView.setText(goods.getPublishTime().trim());

        if (onItemClickListener != null) {
            goodsFindItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(goodsList, goodsFindItemViewHolder);
                }
            });
        }

        if (onButtonClickListener != null) {
            goodsFindItemViewHolder.callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClickListener.onClick(goodsList, goodsFindItemViewHolder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }
}
