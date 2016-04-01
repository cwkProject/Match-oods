package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/7/20.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.Goods;
import com.port.ocean.shipping.util.StaticValue;

/**
 * 货源详情Activity
 *
 * @author 超悟空
 * @version 2.0 2016/3/18
 * @since 1.0
 */
public class GoodsSupplyContentActivity extends AppCompatActivity {

    /**
     * 子控件集合
     *
     * @author 超悟空
     * @version 1.0 2015/7/20
     * @since 1.0
     */
    private class ViewHolder {

        /**
         * 联系人文本框
         */
        public TextView contactTextView = null;

        /**
         * 始发地文本框
         */
        public TextView startTextView = null;

        /**
         * 目的地文本框
         */
        public TextView endTextView = null;

        /**
         * 货物名称文本框
         */
        public TextView goodsNameTextView = null;

        /**
         * 货物重量文本框
         */
        public TextView goodsWeightTextView = null;

        /**
         * 货物体积文本框
         */
        public TextView goodsVolumeTextView = null;

        /**
         * 车型文本框
         */
        public TextView vehicleTypeTextView = null;

        /**
         * 车长文本框
         */
        public TextView vehicleLengthTextView = null;

        /**
         * 距离文本框
         */
        public TextView distanceTextView = null;

        /**
         * 发布时间文本框
         */
        public TextView timeTextView = null;

        /**
         * 手机号文本框
         */
        public TextView mobileTextView = null;

        /**
         * 手机号布局
         */
        public LinearLayout mobileLinearLayout = null;

        /**
         * 座机号文本框
         */
        public TextView phoneTextView = null;

        /**
         * 座机号布局
         */
        public LinearLayout phoneLinearLayout = null;
    }

    /**
     * 子控件集合对象
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_supply_content);

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
        setTitle(R.string.title_goods_content);
        // 初始化控件集
        initViewHolder();
        // 数据填充
        fillData();
        // 控件事件绑定
        eventBinding();
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
     * 初始化控件集引用
     */
    private void initViewHolder() {
        // 联系人
        viewHolder.contactTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_contact_textView);

        // 始发地
        viewHolder.startTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_start_textView);
        // 目的地
        viewHolder.endTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_end_textView);
        // 货物名称
        viewHolder.goodsNameTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_goods_name_textView);
        // 货物重量
        viewHolder.goodsWeightTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_goods_weight_textView);
        // 货物体积
        viewHolder.goodsVolumeTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_goods_volume_textView);
        // 距离
        viewHolder.distanceTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_distance_textView);
        // 车型
        viewHolder.vehicleTypeTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_vehicle_type_textView);
        // 车长
        viewHolder.vehicleLengthTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_vehicle_length_textView);
        // 发布时间
        viewHolder.timeTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_time_textView);
        // 手机号
        viewHolder.mobileTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_mobile_textView);
        // 座机号
        viewHolder.phoneTextView = (TextView) findViewById(R.id
                .activity_goods_supply_content_phone_textView);
        // 手机号布局
        viewHolder.mobileLinearLayout = (LinearLayout) findViewById(R.id
                .activity_goods_supply_content_mobile_layout);
        // 座机号布局
        viewHolder.phoneLinearLayout = (LinearLayout) findViewById(R.id
                .activity_goods_supply_content_phone_layout);
    }

    /**
     * 数据填充
     */
    private void fillData() {
        Object object = getIntent().getSerializableExtra(StaticValue.IntentTag
                .GOODS_SUPPLY_CONTENT_TAG);

        if (object instanceof Goods) {
            // 得到货源对象
            Goods goods = (Goods) object;
            // 填充数据
            viewHolder.timeTextView.setText(goods.getPublishTime());
            viewHolder.goodsNameTextView.setText(goods.getGoodsName());
            viewHolder.vehicleTypeTextView.setText(goods.getVehicleType().trim());
            viewHolder.vehicleLengthTextView.setText(goods.getVehicleLength().trim());
            viewHolder.contactTextView.setText(goods.getContact());

            // 始发地
            String start = goods.getStartProvince().trim();
            start += goods.getStartCity().trim();
            start += goods.getStartDistrict().trim();
            start += goods.getStartDetailed().trim();

            // 目的地
            String end = goods.getEndProvince().trim();
            end += goods.getEndCity().trim();
            end += goods.getEndDistrict().trim();
            end += goods.getEndDetailed().trim();

            viewHolder.startTextView.setText(start);
            viewHolder.endTextView.setText(end);

            if (goods.getWeight().trim().length() != 0) {
                viewHolder.goodsWeightTextView.setText(String.format("%s%s", goods.getWeight()
                        .trim(), getString(R.string.unit_quality_tons)));
            }

            if (goods.getDistance().trim().length() != 0) {
                viewHolder.distanceTextView.setText(String.format("%s %s%s", getString(R.string
                        .approximately), goods.getDistance().trim(), getString(R.string
                        .unit_distance_kilometer)));
            }

            if (goods.getVolume().trim().length() != 0) {
                viewHolder.goodsVolumeTextView.setText(String.format("%s%s", goods.getDistance()
                        .trim(), getString(R.string.unit_volume_cubic_meter)));
            }

            if (goods.getMobile().trim().length() != 0) {
                viewHolder.mobileTextView.setText(goods.getMobile());
            } else {
                viewHolder.mobileLinearLayout.setVisibility(View.GONE);
            }

            if (goods.getPhone().trim().length() != 0) {
                viewHolder.phoneTextView.setText(goods.getPhone());
            } else {
                viewHolder.phoneLinearLayout.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 控件事件绑定
     */
    private void eventBinding() {
        if (viewHolder.mobileLinearLayout.getVisibility() == View.VISIBLE) {
            // 有手机号
            viewHolder.mobileLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 打电话
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewHolder
                            .mobileTextView.getText()));
                    if (ActivityCompat.checkSelfPermission(GoodsSupplyContentActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(GoodsSupplyContentActivity.this, R.string
                                .permission_phone, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(intent);
                }
            });
        }

        if (viewHolder.phoneLinearLayout.getVisibility() == View.VISIBLE) {
            // 有座机号
            viewHolder.phoneLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 打电话
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewHolder
                            .phoneTextView.getText()));

                    if (ActivityCompat.checkSelfPermission(GoodsSupplyContentActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(GoodsSupplyContentActivity.this, R.string
                                .permission_phone, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
