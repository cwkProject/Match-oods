package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/6/30.
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.activity.CarFieldActivity;
import com.port.ocean.shipping.activity.LoginActivity;
import com.port.ocean.shipping.activity.PublishEmptyCarActivity;
import com.port.ocean.shipping.activity.TransactionListActivity;
import com.port.ocean.shipping.activity.VehiclePassedActivity;
import com.port.ocean.shipping.adapter.ExploreFunctionItemViewHolder;
import com.port.ocean.shipping.adapter.ExploreFunctionRecyclerViewAdapter;
import com.port.ocean.shipping.bean.FunctionItem;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能片段
 *
 * @author 超悟空
 * @version 2.0 2016/3/15
 * @since 1.0
 */
public class ExploreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        // 初始化功能列表
        initListView(rootView);

        return rootView;
    }

    /**
     * 初始化功能表格布局
     *
     * @param rootView 根布局
     */
    private void initListView(View rootView) {

        // 片段中的列表布局
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id
                .fragment_explore_recyclerView);

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        // 片段适配器
        ExploreFunctionRecyclerViewAdapter adapter = new ExploreFunctionRecyclerViewAdapter
                (getFunctionItemSource());

        // 设置监听器
        adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<FunctionItem>, ExploreFunctionItemViewHolder>() {
            @Override
            public void onClick(List<FunctionItem> dataSource, ExploreFunctionItemViewHolder
                    holder) {
                onItemClick(holder.getAdapterPosition());
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * 生成功能项标签资源的数据源
     *
     * @return 返回适配器使用的数据源
     */
    private List<FunctionItem> getFunctionItemSource() {
        // 加载功能项
        List<FunctionItem> dataList = new ArrayList<>();

        // 功能名称数组
        String[] functionTitle = getResources().getStringArray(R.array
                .grid_item_function_name_list);
        // 资源类型数组
        TypedArray images = getResources().obtainTypedArray(R.array.grid_item_function_image_list);

        for (int i = 0; i < functionTitle.length; i++) {
            // 新建一个功能项，添加标题，添加功能标签图标资源
            FunctionItem item = new FunctionItem(functionTitle[i], images.getResourceId(i, R
                    .mipmap.ic_launcher));

            // 将标签加入数据源
            dataList.add(item);
        }

        images.recycle();

        return dataList;
    }

    /**
     * 表格项点击事件触发操作，
     * 默认触发功能跳转，
     * 并检测登录状态
     *
     * @param position 点击的位置索引
     */
    private void onItemClick(int position) {

        if (!GlobalApplication.getLoginStatus().isLogin()) {
            // 未登录
            // 新建意图,跳转到登录页面
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            // 执行跳转
            startActivity(intent);
            getActivity().finish();
            return;
        }

        // 跳转意图
        Intent function = null;

        switch (position) {
            case 0:
                // 放行信息
                function = new Intent(getActivity(), VehiclePassedActivity.class);
                break;
            case 1:
                // 发布空车
                function = new Intent(getActivity(), PublishEmptyCarActivity.class);
                break;
            case 2:
                // 我的交易
                function = new Intent(getActivity(), TransactionListActivity.class);
                break;
            case 3:
                // 网上车源
                function = new Intent(getActivity(), CarFieldActivity.class);
                break;
        }

        if (function != null) {
            startActivity(function);
        }
    }
}
