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
import com.port.ocean.shipping.work.PullVehicleInfo;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.WorkBack;

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
     * 最大车辆数
     */
    private static final int MAX_VEHICLE = 6;

    /**
     * 控件集合
     */
    private class ViewHolder {
        /**
         * 列表的数据适配器
         */
        public VehicleManagementRecyclerViewAdapter adapter = null;

        /**
         * 记录当前操作的车辆位置
         */
        public int position = 0;
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
                        viewHolder.position = dataSource.size() - 1;
                        // 增加绑定
                        onAdd();
                    } else {
                        // 修改
                        viewHolder.position = holder.getAdapterPosition();
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

        PullVehicleInfo pullVehicleInfo = new PullVehicleInfo();

        pullVehicleInfo.setWorkEndListener(new WorkBack<List<Vehicle>>() {
            @Override
            public void doEndWork(boolean state, List<Vehicle> data) {
                if (state) {
                    if (data != null && data.size() < MAX_VEHICLE) {
                        data.add(new Vehicle());
                    }
                    viewHolder.adapter.add(data);
                } else {
                    viewHolder.adapter.add(new Vehicle());
                }
            }
        });

        pullVehicleInfo.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_VEHICLE_TAG:
                // 增加车辆
                if (resultCode == RESULT_OK) {
                    Vehicle vehicle = data.getParcelableExtra(StaticValue.IntentTag
                            .VEHICLE_DETAIL_TAG);

                    if (vehicle != null) {
                        if (viewHolder.adapter.getItemCount() < MAX_VEHICLE) {
                            viewHolder.adapter.add(vehicle, viewHolder.position);
                        } else {
                            viewHolder.adapter.change(viewHolder.position, vehicle);
                        }
                    }
                }
                break;
            case EDIT_VEHICLE_TAG:
                // 编辑车辆
                if (resultCode == RESULT_OK) {
                    // 更新车辆
                    Vehicle vehicle = data.getParcelableExtra(StaticValue.IntentTag
                            .VEHICLE_DETAIL_TAG);

                    if (vehicle != null) {
                        viewHolder.adapter.change(viewHolder.position, vehicle);
                    }
                    break;
                }

                if (resultCode == RESULT_FIRST_USER) {
                    // 删除车辆
                    viewHolder.adapter.remove(viewHolder.position);
                    if (viewHolder.adapter.getItemViewType(viewHolder.adapter.getItemCount() - 1)
                            != VehicleManagementRecyclerViewAdapter.PLUS_ITEM_TYPE) {
                        viewHolder.adapter.add(new Vehicle());
                    }
                }
                break;
        }
    }
}
