package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/29.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.EmptyCarStateData;
import com.port.ocean.shipping.fragment.PublishEmptyCarFragment;
import com.port.ocean.shipping.fragment.RevokedEmptyCarFragment;
import com.port.ocean.shipping.work.PullEmptyCarState;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;

/**
 * 发布空车Activity
 *
 * @author 超悟空
 * @version 1.0 2016/3/24
 * @since 1.0
 */
public class PublishEmptyCarActivity extends AppCompatActivity {

    /**
     * 子控件集合
     *
     * @author 超悟空
     * @version 2.0 2016/3/24
     * @since 1.0
     */
    private class ViewHolder {

        /**
         * 获取发布空车状态的任务
         */
        public PullEmptyCarState pullEmptyCarState = null;

        /**
         * 发布空车页面
         */
        public PublishEmptyCarFragment publishEmptyCarFragment = null;

        /**
         * 撤销空车页面
         */
        public RevokedEmptyCarFragment revokedEmptyCarFragment = null;
    }

    /**
     * 子控件集合对象
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_empty_car);

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
        setTitle(R.string.title_publish_empty_car);
        // 获取状态并初始化片段
        initFragment();
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
     * 获取状态并初始化片段
     */
    private void initFragment() {
        viewHolder.pullEmptyCarState = new PullEmptyCarState();

        viewHolder.pullEmptyCarState.setWorkEndListener(new WorkBack<EmptyCarStateData>() {
            @Override
            public void doEndWork(boolean state, EmptyCarStateData data) {

                if (state) {
                    showRevokedFragment(data);
                } else {
                    showPublishFragment();
                }
            }
        });

        viewHolder.pullEmptyCarState.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }

    /**
     * 显示发布片段
     */
    private void showPublishFragment() {
        // 发布空车页面
        viewHolder.publishEmptyCarFragment = new PublishEmptyCarFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.activity_publish_empty_car_layout, viewHolder
                .publishEmptyCarFragment).commit();

    }

    /**
     * 显示撤销片段
     *
     * @param data 空车数据
     */
    private void showRevokedFragment(EmptyCarStateData data) {
        // 撤销空车页面
        viewHolder.revokedEmptyCarFragment = new RevokedEmptyCarFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.activity_publish_empty_car_layout, viewHolder
                .revokedEmptyCarFragment.setData(data)).commit();
    }

    @Override
    protected void onDestroy() {
        viewHolder.pullEmptyCarState.cancel();
        super.onDestroy();
    }
}
