package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2016/3/25.
 */

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.adapter.RecyclerViewDivider;
import com.port.ocean.shipping.adapter.VehiclePassedRecyclerViewAdapter;
import com.port.ocean.shipping.bean.VehiclePassed;
import com.port.ocean.shipping.work.PullVehiclePassed;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 车辆放行信息Activity
 *
 * @author 超悟空
 * @version 1.0 2016/3/25
 * @since 1.0
 */
public class VehiclePassedActivity extends AppCompatActivity {

    /**
     * 一次性加载的数据行数
     */
    private static final int ROW_COUNT = 50;

    /**
     * 加载更多数据的触发剩余行数
     */
    private static final int LAST_ROW_COUNT = 20;

    /**
     * 控件集合
     */
    private class ViewHolder {
        /**
         * 下拉刷新控件
         */
        public SwipeRefreshLayout refreshLayout = null;

        /**
         * 放行信息数据适配器
         */
        public VehiclePassedRecyclerViewAdapter adapter = null;

        /**
         * 查询关键字
         */
        public String query = null;

        /**
         * 上一个执行的加载任务
         */
        public DefaultWorkModel beforeLoadWork = null;

        /**
         * 表示是否还有更多数据
         */
        public volatile boolean hasMoreData = false;

        /**
         * 表示是否正在加载
         */
        public volatile boolean loading = false;
    }

    /**
     * 控件集对象
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vehicle_passed);

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
        setTitle(R.string.title_vehicle_passed);
        // 初始化控件集
        initViewHolder();
        // 初始化列表
        initList();
        // 初始化刷新控件
        initSwipeRefresh();
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
        viewHolder.refreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .activity_vehicle_passed_swipeRefreshLayout);

        viewHolder.adapter = new VehiclePassedRecyclerViewAdapter();
    }

    /**
     * 初始化列表
     */
    private void initList() {
        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_vehicle_passed_recyclerView);

        // 设置item动画
        if (recyclerView != null) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            recyclerView.setHasFixedSize(true);

            // 创建布局管理器
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

            // 设置布局管理器
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager
                    .VERTICAL));

            recyclerView.setAdapter(viewHolder.adapter);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int lastCount = recyclerView.getAdapter().getItemCount() - recyclerView
                            .getChildAdapterPosition(recyclerView.getChildAt(0)) - recyclerView
                            .getChildCount();

                    if (dy > 0 && !viewHolder.loading && viewHolder.hasMoreData && lastCount <=
                            LAST_ROW_COUNT) {
                        // 有必要加载更多
                        loadData(false);
                    }
                }
            });
        }
    }

    /**
     * 初始化刷新控件
     */
    private void initSwipeRefresh() {
        TypedArray typedArray = obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        viewHolder.refreshLayout.setColorSchemeResources(typedArray.getResourceId(0, 0));
        typedArray.recycle();

        viewHolder.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        loadData(true);
    }

    /**
     * 加载数据
     *
     * @param reload 表示是否为全新加载
     */
    private void loadData(boolean reload) {

        if (reload) {
            // 属于全新加载数据，清空原数据
            viewHolder.adapter.clear();
            // 中断上次请求
            if (viewHolder.beforeLoadWork != null) {
                viewHolder.beforeLoadWork.cancel();
            }
        }

        PullVehiclePassed pullVehiclePassed = new PullVehiclePassed();

        pullVehiclePassed.setWorkEndListener(new WorkBack<List<VehiclePassed>>() {
            @Override
            public void doEndWork(boolean state, List<VehiclePassed> data) {
                if (state && data != null) {
                    // 插入新数据
                    viewHolder.adapter.add(data);

                    if (data.size() == ROW_COUNT) {
                        // 取到了预期条数的数据
                        viewHolder.hasMoreData = true;
                    }
                }

                // 改变请求状态
                viewHolder.loading = false;

                // 停止动画
                viewHolder.refreshLayout.setRefreshing(false);
            }
        });

        // 改变请求状态
        viewHolder.loading = true;
        // 初始化更多预期
        viewHolder.hasMoreData = false;

        // 保存新的加载任务对象
        viewHolder.beforeLoadWork = pullVehiclePassed;

        // 打开加载动画
        viewHolder.refreshLayout.setRefreshing(true);

        // 执行任务
        pullVehiclePassed.beginExecute(GlobalApplication.getLoginStatus().getUserID(), String
                .valueOf(viewHolder.adapter.getItemCount() + 1), String.valueOf(ROW_COUNT),
                viewHolder.query);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_with_search, menu);

        // 获取SearchView对象
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        if (searchView != null) {
            searchView.setQueryHint(getString(R.string.edit_hint_license_plate_number));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchButtonClick(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    if (TextUtils.isEmpty(searchView.getQuery())) {
                        searchButtonClick(null);
                    }
                    return false;
                }
            });
        }

        return true;
    }

    /**
     * 执行搜索按钮
     *
     * @param query 查询字符串
     */
    private void searchButtonClick(String query) {
        viewHolder.query = query == null ? null : query.toUpperCase();
        initData();
    }

    @Override
    public void onDestroy() {
        // 中断上次请求
        if (viewHolder.beforeLoadWork != null) {
            viewHolder.beforeLoadWork.cancel();
        }
        super.onDestroy();
    }
}
