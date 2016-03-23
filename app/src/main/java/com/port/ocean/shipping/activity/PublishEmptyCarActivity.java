package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/29.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.EmptyCarStateData;
import com.port.ocean.shipping.fragment.PublishEmptyCarFragment;
import com.port.ocean.shipping.fragment.RevokedEmptyCarFragment;
import com.port.ocean.shipping.fragment.SelectListFragment;
import com.port.ocean.shipping.util.MemoryValue;
import com.port.ocean.shipping.work.PullEmptyCarState;

import org.mobile.library.common.function.CitySelectList;
import org.mobile.library.model.work.WorkBack;

/**
 * 发布空车Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/29
 * @since 1.0
 */
public class PublishEmptyCarActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PublishEmptyCarActivity.";

    /**
     * 子控件集合
     *
     * @author 超悟空
     * @version 1.0 2015/7/17
     * @since 1.0
     */
    private class ViewHolder {
        /**
         * 抽屉布局
         */
        public DrawerLayout drawerLayout = null;

        /**
         * 抽屉布局中的选择列表
         */
        public ListView selectListView = null;

        /**
         * 发布空车页面
         */
        public PublishEmptyCarFragment publishEmptyCarFragment = null;

        /**
         * 撤销空车页面
         */
        public RevokedEmptyCarFragment revokedEmptyCarFragment = null;

        /**
         * 城市选择抽屉工具
         */
        public CitySelectList citySelectDrawer = null;
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
        // 初始化控件集
        initViewHolder();
        // 初始化抽屉布局
        initDrawer();
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
     * 初始化控件集引用
     */
    private void initViewHolder() {
        // 抽屉布局
        viewHolder.drawerLayout = (DrawerLayout) findViewById(R.id
                .activity_publish_empty_car_drawer_layout);

        // 选择列表
        SelectListFragment selectListFragment = (SelectListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_publish_empty_car_navigation_drawer);

        viewHolder.selectListView = selectListFragment.getListView();

        // 城市选择抽屉工具
        viewHolder.citySelectDrawer = new CitySelectList(this);
    }

    /**
     * 初始化抽屉布局
     */
    private void initDrawer() {
        // 抽屉布局
        viewHolder.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    /**
     * 获取状态并初始化片段
     */
    private void initFragment() {
        PullEmptyCarState pullEmptyCarState = new PullEmptyCarState();

        pullEmptyCarState.setWorkEndListener(new WorkBack<EmptyCarStateData>() {
            @Override
            public void doEndWork(boolean state, EmptyCarStateData data) {

                if (state) {
                    showRevokedFragment(data);
                } else {
                    showPublishFragment();
                }
            }
        });

        pullEmptyCarState.beginExecute(MemoryValue.getMemoryValue().getUserID());
    }

    /**
     * 显示发布片段
     */
    private void showPublishFragment() {
        // 发布空车页面
        viewHolder.publishEmptyCarFragment = new PublishEmptyCarFragment();

        // 初始化输入框选择响应
        initSelectEdit();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.activity_publish_empty_car_layout, viewHolder
                .publishEmptyCarFragment).commit();

    }

    /**
     * 初始化输入框选择响应
     */
    private void initSelectEdit() {

//        viewHolder.publishEmptyCarFragment.setEditClickObserver(new EmptyParameterObserver() {
//            @Override
//            public void invoke() {
//                Log.i(LOG_TAG + "initSelectEdit", "Select edit is invoked");
//                // 关闭软键盘
//                InputMethodController.CloseInputMethod(PublishEmptyCarActivity.this);
//                // 先关闭抽屉
//                closeDrawer();
//                // 配置选择器
//                viewHolder.citySelectDrawer.selectSetting();
//                // 再打开抽屉
//                openDrawer();
//            }
//        });
//
//        // 设置选择完成回调
//        viewHolder.citySelectDrawer.setCitySelectFinishedListener(new CitySelectList
//                .CitySelectFinishedListener() {
//
//            @Override
//            public void onCitySelectFinish(String province, @Nullable String city, @Nullable
//            String district) {
//                // 赋值
//                viewHolder.publishEmptyCarFragment.setEditValue(province, city, district);
//                // 关闭抽屉
//                closeDrawer();
//            }
//        });
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

    /**
     * 打开导航抽屉
     */
    public void openDrawer() {
        if (viewHolder.drawerLayout != null) {
            viewHolder.drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    /**
     * 关闭导航抽屉
     */
    public void closeDrawer() {
        if (viewHolder.drawerLayout != null) {
            viewHolder.drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }

    @Override
    public void onBackPressed() {

        // 如果抽屉已打开，则先关闭抽屉
        if (viewHolder.drawerLayout != null && viewHolder.drawerLayout.isDrawerOpen(Gravity
                .RIGHT)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
