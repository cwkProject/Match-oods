package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2016/3/9.
 */

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.activity.GoodsSupplyContentActivity;
import com.port.ocean.shipping.adapter.GoodsFindItemViewHolder;
import com.port.ocean.shipping.adapter.GoodsFindRecyclerViewAdapter;
import com.port.ocean.shipping.adapter.GoodsItemDivider;
import com.port.ocean.shipping.bean.Goods;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.work.PullGoodsSupply;

import org.mobile.library.model.operate.DataChangeObserver;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.DefaultWorkModel;
import org.mobile.library.model.work.WorkBack;

import java.util.List;

/**
 * 找货片段
 *
 * @author 超悟空
 * @version 1.0 2016/3/9
 * @since 1.0
 */
public class FindGoodsFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "FindGoodsFragment.";

    /**
     * 一次性加载的数据行数
     */
    private static final int ROW_COUNT = 50;

    /**
     * 加载更多数据的触发剩余行数
     */
    private static final int LAST_ROW_COUNT = 20;

    /**
     * 控件管理器
     */
    private class ViewHolder {

        /**
         * 列表对象
         */
        public RecyclerView recyclerView = null;

        /**
         * 货源查找结果数据适配器
         */
        public GoodsFindRecyclerViewAdapter adapter = null;

        /**
         * 筛选器片段
         */
        public GoodsFilterFragment filterFragment = null;

        /**
         * 下拉刷新控件
         */
        public SwipeRefreshLayout refreshLayout = null;

        /**
         * 筛选器布局
         */
        public View filterLayout = null;

        /**
         * 保存部分当前的筛选数据
         */
        public Goods goods = null;

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
     * 控件工具
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        // 开启追加菜单项
        setHasOptionsMenu(true);
        // 当前功能片段布局
        View rootView = inflater.inflate(R.layout.fragment_find_goods, container, false);

        // 初始化布局引用
        initView(rootView);

        // 初始化筛选器
        initFilter();

        // 初始化列表
        initListView();

        // 初始化刷新控件
        initSwipeRefresh();

        // 加载数据
        initData();

        return rootView;
    }

    /**
     * 初始化控件引用
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {

        viewHolder.recyclerView = (RecyclerView) rootView.findViewById(R.id
                .fragment_find_goods_recyclerView);

        viewHolder.adapter = new GoodsFindRecyclerViewAdapter();

        viewHolder.filterFragment = new GoodsFilterFragment();

        viewHolder.goods = new Goods();

        viewHolder.refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id
                .fragment_find_goods_swipeRefreshLayout);

        viewHolder.filterLayout = rootView.findViewById(R.id
                .fragment_find_goods_filter_frameLayout);
    }

    /**
     * 初始化列表
     */
    private void initListView() {

        // 设置item动画
        viewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // 设置布局管理器
        viewHolder.recyclerView.setLayoutManager(layoutManager);

        viewHolder.recyclerView.setHasFixedSize(true);

        // 设置分割线
        viewHolder.recyclerView.addItemDecoration(new GoodsItemDivider(getActivity()));

        // 设置电话按钮点击事件
        viewHolder.adapter.setOnButtonClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsFindItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsFindItemViewHolder holder) {

                // 打电话
                String phoneNumber = dataSource.get(holder.getAdapterPosition()).getMobile();

                if (phoneNumber == null || phoneNumber.length() == 0) {
                    phoneNumber = dataSource.get(holder.getAdapterPosition()).getPhone();
                }

                Log.i(LOG_TAG + "initListView", "phone number is " + phoneNumber);

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission
                        .CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Log.i(LOG_TAG + "initListView", "no CALL_PHONE permission");
                    Toast.makeText(getActivity(), R.string.permission_phone, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                startActivity(intent);
            }
        });

        // 设置Item点击事件
        viewHolder.adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsFindItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsFindItemViewHolder holder) {
                // 跳转到详情页面
                Intent intent = new Intent(getActivity(), GoodsSupplyContentActivity.class);
                intent.putExtra(StaticValue.IntentTag.GOODS_SUPPLY_CONTENT_TAG, dataSource.get
                        (holder.getAdapterPosition()));
                //startActivity(intent);

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(getActivity(), holder.callButton, "call");

                ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
            }
        });

        viewHolder.recyclerView.setAdapter(viewHolder.adapter);

        // 设置加载更多
        viewHolder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastCount = recyclerView.getAdapter().getItemCount() - recyclerView
                        .getChildAdapterPosition(recyclerView.getChildAt(0)) - recyclerView
                        .getChildCount();
                if (dy > 0 && !viewHolder.loading && viewHolder.hasMoreData && lastCount <=
                        LAST_ROW_COUNT) {
                    // 有必要加载更多
                    Log.i(LOG_TAG + "initListView", "onScrolled now load more");
                    loadData(false);
                }
            }
        });

        // 添加筛选器动作
        viewHolder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * 滚动方向，true表示向下，false表示向上
             */
            private boolean state = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0 && !state || dy < 0 && state) {
                    viewHolder.filterLayout.animate().cancel();
                    Log.i(LOG_TAG + "initListView", "dy:" + dy + " , state:" + state);

                    if (dy > 0) {
                        state = true;
                        hide(viewHolder.filterLayout);
                    }

                    if (dy < 0) {
                        state = false;
                        show(viewHolder.filterLayout);
                    }
                }
            }
        });
    }

    /**
     * 初始化刷新控件
     */
    private void initSwipeRefresh() {
        TypedArray typedArray = getActivity().obtainStyledAttributes(new int[]{R.attr.colorPrimary});
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
     * 初始化筛选器
     */
    private void initFilter() {

        viewHolder.filterFragment.setDataChangeListener(new DataChangeObserver<Goods>() {
            @Override
            public void notifyDataChange(Goods data) {
                viewHolder.goods = data;

                initData();
            }
        });

        getFragmentManager().beginTransaction().replace(R.id
                .fragment_find_goods_filter_frameLayout, viewHolder.filterFragment).commit();
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

        Log.i(LOG_TAG + "loadData", "reload tag is " + reload);

        if (reload) {
            // 属于全新加载数据，清空原数据
            viewHolder.adapter.clear();
            // 中断上次请求
            if (viewHolder.beforeLoadWork != null) {
                viewHolder.beforeLoadWork.cancel();
            }
        }

        PullGoodsSupply pullGoodsSupply = new PullGoodsSupply();

        pullGoodsSupply.setWorkEndListener(new WorkBack<List<Goods>>() {
            @Override
            public void doEndWork(boolean state, List<Goods> data) {
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
        viewHolder.beforeLoadWork = pullGoodsSupply;

        // 打开加载动画
        viewHolder.refreshLayout.setRefreshing(true);

        Goods goods = viewHolder.goods;

        // 执行任务
        pullGoodsSupply.beginExecute(String.valueOf(viewHolder.adapter.getItemCount() + 1),
                String.valueOf(ROW_COUNT), viewHolder.query, goods.getStartProvince(), goods
                        .getStartCity(), goods.getStartDistrict(), goods.getEndProvince(), goods
                        .getEndCity(), goods.getEndDistrict(), goods.getGoodsName(), goods
                        .getVehicleType());
    }

    /**
     * 隐藏布局
     *
     * @param view 要隐藏的布局
     */
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).setDuration
                (200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    /**
     * 显示布局
     *
     * @param view 要显示的布局
     */
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_with_search, menu);

        // 获取SearchView对象
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        if (searchView != null) {
            searchView.setQueryHint(getString(R.string.search_hint));

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
    }

    /**
     * 执行搜索按钮
     *
     * @param query 查询字符串
     */
    private void searchButtonClick(String query) {
        viewHolder.query = query;
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
