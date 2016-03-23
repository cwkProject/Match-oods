package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/27.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.adapter.GoodsFindItemViewHolder;
import com.port.ocean.shipping.adapter.GoodsFindRecyclerViewAdapter;
import com.port.ocean.shipping.data.Goods;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.work.PullGoodsSupply;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 货源查找结果列表Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/27
 * @since 1.0
 */
public class GoodsSupplyFindResultListActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "GoodsSupplyFindResultListActivity.";

    /**
     * 始发地省
     */
    private String startProvince = null;

    /**
     * 始发地市
     */
    private String startCity = null;

    /**
     * 始发地区
     */
    private String startDistrict = null;

    /**
     * 目的地省
     */
    private String endProvince = null;

    /**
     * 目的地市
     */
    private String endCity = null;

    /**
     * 目的地区
     */
    private String endDistrict = null;

    private GoodsFindRecyclerViewAdapter adapter = new GoodsFindRecyclerViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_supply_find_result);

        // 初始化布局
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        // 设置标题
        setTitle(R.string.title_goods_find);
        // 初始化列表
        initListView();
        // 提取数据
        loadData();
        // 获取数据
        initData();
    }

    /**
     * 初始化标题栏
     */
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
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_goods_supply_find_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 设置电话按钮点击事件
        adapter.setOnButtonClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>,
                GoodsFindItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsFindItemViewHolder holder) {

                // 打电话
                String phoneNumber = dataSource.get(holder.getAdapterPosition()).getMobile();

                if (phoneNumber == null || phoneNumber.length() == 0) {
                    phoneNumber = dataSource.get(holder.getAdapterPosition()).getPhone();
                }

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (ActivityCompat.checkSelfPermission(GoodsSupplyFindResultListActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[]
                    // permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the
                    // documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        // 设置Item点击事件
        adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>,
                GoodsFindItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsFindItemViewHolder holder) {
                // 跳转到详情页面
                Intent intent = new Intent(GoodsSupplyFindResultListActivity.this,
                        GoodsSupplyContentActivity.class);
                intent.putExtra(StaticValue.IntentTag.GOODS_SUPPLY_CONTENT_TAG, dataSource.get(holder
                        .getAdapterPosition()));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * 提取数据
     */
    private void loadData() {
        Intent intent = getIntent();
        startProvince = intent.getStringExtra(StaticValue.IntentTag.START_PROVINCE_TAG);
        startCity = intent.getStringExtra(StaticValue.IntentTag.START_CITY_TAG);
        endProvince = intent.getStringExtra(StaticValue.IntentTag.END_PROVINCE_TAG);
        endCity = intent.getStringExtra(StaticValue.IntentTag.END_CITY_TAG);
    }

    /**
     * 初始化数据
     */
    private void initData() {

        PullGoodsSupply pullGoodsSupply = new PullGoodsSupply();

        pullGoodsSupply.setWorkEndListener(new WorkBack<List<Goods>>() {
            @Override
            public void doEndWork(boolean state, List<Goods> data) {
                if (state) {
                    adapter.add(data);
                }
            }
        });

        pullGoodsSupply.beginExecute(startProvince, startCity, endProvince, endCity);
    }
}
