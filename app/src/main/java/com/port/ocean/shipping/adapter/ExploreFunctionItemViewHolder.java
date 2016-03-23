package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.port.ocean.shipping.R;

/**
 * 功能列表Item的View管理器
 *
 * @author 超悟空
 * @version 1.0 2016/3/15
 * @since 1.0
 */
public class ExploreFunctionItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 功能图标
     */
    public ImageView iconImageView = null;

    /**
     * 功能标题
     */
    public TextView titleTextView = null;

    public ExploreFunctionItemViewHolder(View itemView) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.explore_function_item_imageView);

        titleTextView = (TextView) itemView.findViewById(R.id.explore_function_item_textView);
    }
}
