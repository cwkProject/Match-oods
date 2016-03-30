package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2016/3/30.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.adapter.VehicleManagementRecyclerViewAdapter;
import com.port.ocean.shipping.adapter.VehicleManagementViewHolder;
import com.port.ocean.shipping.bean.Vehicle;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.List;

/**
 * 车辆管理Activity
 *
 * @author 超悟空
 * @version 1.0 2016/3/30
 * @since 1.0
 */
public class VehicleManagementActivity extends AppCompatActivity {

    /**
     * 新增车牌标记
     */
    private static final int ADD_VEHICLE_TAG = 100;

    /**
     * 编辑车牌标记
     */
    private static final int EDIT_VEHICLE_TAG = 200;

    /**
     * 控件集合
     */
    private class ViewHolder {
        /**
         * 列表的数据适配器
         */
        public VehicleManagementRecyclerViewAdapter adapter = null;
    }

    /**
     * 控件集对象
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vehicle_management);

        // 初始化布局引用
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        // 设置标题
        setTitle(R.string.title_vehicle_management);
        // 初始化控件集
        initViewHolder();
        // 初始化列表
        initList();
        // 加载数据
        initData();
    }

    /**
     * 初始化标题栏
     */
    @SuppressWarnings("ConstantConditions")
    private void initToolbar() {
        // 得到Toolbar标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // 关联ActionBar
        setSupportActionBar(toolbar);

        // 显示后退
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 与返回键相同
                onBackPressed();
            }
        });
    }

    /**
     * 初始化控件集引用
     */
    private void initViewHolder() {
        viewHolder.adapter = new VehicleManagementRecyclerViewAdapter();
    }

    /**
     * 初始化列表
     */
    private void initList() {
        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_vehicle_management_recyclerView);

        // 设置item动画
        if (recyclerView != null) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            recyclerView.setHasFixedSize(true);

            // 创建布局管理器
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

            // 设置布局管理器
            recyclerView.setLayoutManager(layoutManager);

            viewHolder.adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Vehicle>, VehicleManagementViewHolder>() {
                @Override
                public void onClick(List<Vehicle> dataSource, VehicleManagementViewHolder holder) {
                    if (holder.getItemViewType() == VehicleManagementRecyclerViewAdapter
                            .PLUS_ITEM_TYPE) {
                        // 增加绑定
                        onAdd();
                    } else {
                        // 修改
                        onEdit(dataSource.get(holder.getAdapterPosition()));
                    }
                }
            });

            recyclerView.setAdapter(viewHolder.adapter);
        }
    }

    /**
     * 绑定新车牌
     */
    private void onAdd() {
        Intent intent = new Intent(this, AddVehicleActivity.class);
        startActivityForResult(intent, ADD_VEHICLE_TAG);
    }

    /**
     * 编辑车牌
     *
     * @param data 车牌
     */
    private void onEdit(Vehicle data) {
        Intent intent = new Intent(this, EditVehicleActivity.class);
        intent.putExtra(StaticValue.IntentTag.VEHICLE_DETAIL_TAG, data);
        startActivityForResult(intent, EDIT_VEHICLE_TAG);
    }

    /**
     * 加载数据
     */
    private void initData() {
        viewHolder.adapter.add(new Vehicle());
    }
}
