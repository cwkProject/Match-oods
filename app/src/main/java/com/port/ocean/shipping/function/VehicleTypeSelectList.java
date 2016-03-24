package com.port.ocean.shipping.function;
/**
 * Created by 超悟空 on 2015/7/18.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.adapter.OnlyTextItemViewHolder;
import com.port.ocean.shipping.adapter.OnlyTextRecyclerViewAdapter;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 车型选择列表
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class VehicleTypeSelectList implements ISelectList<View, String> {

    /**
     * 结果监听器
     */
    private OnSelectedListener<View, String> onSelectedListener = null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 当前的选择布局
     */
    private View view = null;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public VehicleTypeSelectList(Context context) {
        this.context = context;
    }

    @Override
    public void setOnSelectedListener(OnSelectedListener<View, String> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }

    @Override
    public View loadSelect() {
        if (view == null) {
            view = onCreateView();
        }
        return view;
    }

    /**
     * 创建布局
     *
     * @return 布局实例
     */
    private View onCreateView() {
        View view = LayoutInflater.from(context).inflate(R.layout.only_recycler_view_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id
                .only_recycler_view_layout_recyclerView);

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 4);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        BaseDataListFunction baseDataListFunction = DataListManager.get(StaticValue.DataListTag
                .VEHICLE_TYPE_LIST);

        if (baseDataListFunction != null && baseDataListFunction instanceof
                VehicleTypeListFunction) {

            VehicleTypeListFunction listFunction = (VehicleTypeListFunction) baseDataListFunction;

            List<String> dataList;

            if (listFunction.getDataList() != null) {
                dataList = new ArrayList<>(listFunction.getDataList());
                dataList.add(0, context.getString(org.mobile.library.R.string.any));
            } else {
                dataList = new ArrayList<>();
            }

            OnlyTextRecyclerViewAdapter adapter = new OnlyTextRecyclerViewAdapter(dataList);

            if (onSelectedListener != null) {
                // 绑定点击事件
                adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<String>, OnlyTextItemViewHolder>() {
                    @Override
                    public void onClick(List<String> dataSource, OnlyTextItemViewHolder holder) {

                        int position = holder.getAdapterPosition();

                        onSelectedListener.onFinish(position == 0 ? null : dataSource.get
                                (position));
                    }
                });
            }

            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}
