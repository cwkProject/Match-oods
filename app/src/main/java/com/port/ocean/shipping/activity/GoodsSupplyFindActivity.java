package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/27.
 */

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.fragment.RationingFragment;
import com.port.ocean.shipping.fragment.SelectListFragment;

import org.mobile.library.common.function.CitySelectList;

/**
 * 货源查找Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/27
 * @since 1.0
 */
public class GoodsSupplyFindActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "GoodsSupplyFindActivity.";

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
         * 配货页面
         */
        public RationingFragment rationingFragment = null;

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
        setContentView(R.layout.activity_goods_supply_find);

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
        // 初始化控件集
        initViewHolder();
        // 初始化抽屉布局
        initDrawer();
        // 初始化输入框选择响应
        initSelectEdit();
    }

    /**
     * 初始化控件集引用
     */
    private void initViewHolder() {
        // 抽屉布局
        viewHolder.drawerLayout = (DrawerLayout) findViewById(R.id
                .activity_goods_supply_find_drawer_layout);

        // 选择列表
        SelectListFragment selectListFragment = (SelectListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_goods_supply_find_navigation_drawer);

        viewHolder.selectListView = selectListFragment.getListView();

        // 配货页面
        viewHolder.rationingFragment = (RationingFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_goods_supply_find_rationing_fragment);

        // 城市选择抽屉工具
        //viewHolder.citySelectDrawer = new CitySelectList(this, viewHolder.selectListView);
    }

    /**
     * 初始化抽屉布局
     */
    private void initDrawer() {
        // 抽屉布局
        viewHolder.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    /**
     * 初始化输入框选择响应
     */
    private void initSelectEdit() {

//        viewHolder.rationingFragment.setEditClickObserver(new EmptyParameterObserver() {
//            @Override
//            public void invoke() {
//                Log.i(LOG_TAG + "initSelectEdit", "Select edit is invoked");
//                // 关闭软键盘
//                InputMethodController.CloseInputMethod(GoodsSupplyFindActivity.this);
//                // 先关闭抽屉
//                closeDrawer();
//                // 配置选择列表
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
//                viewHolder.rationingFragment.setEditValue(province, city, district);
//                // 关闭抽屉
//                closeDrawer();
//            }
//        });

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
