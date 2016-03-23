package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.activity.CarFieldActivity;
import com.port.ocean.shipping.activity.GoodsSupplyFindActivity;
import com.port.ocean.shipping.activity.LoginActivity;
import com.port.ocean.shipping.activity.PublishEmptyCarActivity;
import com.port.ocean.shipping.activity.TransactionListActivity;
import com.port.ocean.shipping.util.MemoryValue;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.common.webview.MobileWebViewFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class MainFragment extends Fragment {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "GridFragment.";

    /**
     * 功能名称取值标签
     */
    private static final String FUNCTION_NAME = "function_name";

    /**
     * 功能图标取值图标
     */
    private static final String FUNCTION_IMAGE = "function_image";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 当前功能片段布局
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // 初始化表格布局
        initGridView(rootView);

        // 初始化网页布局
        initWebView(rootView);

        return rootView;
    }

    /**
     * 初始化网页
     *
     * @param rootView 根布局
     */
    private void initWebView(View rootView) {
        WebView webView = (WebView) rootView.findViewById(R.id.fragment_main_webView);

        MobileWebViewFactory.assemblingWebView(getActivity(), webView);

        webView.loadUrl(StaticValue.MAIN_ADVERTISING);
    }

    /**
     * 设置表格适配器，否则使用默认配置
     *
     * @return 简单适配器对象
     */
    protected SimpleAdapter onGridSimpleAdapter() {
        return new SimpleAdapter(getActivity(), initFunctionResource(), R.layout.function_grid_item, new String[]{FUNCTION_NAME , FUNCTION_IMAGE}, new int[]{R.id.function_grid_item_text , R.id.function_grid_item_image});
    }

    /**
     * 初始化表格布局
     *
     * @param rootView 根布局
     */
    private void initGridView(View rootView) {
        // 片段中的表格布局
        GridView gridView = (GridView) rootView.findViewById(R.id.fragment_main_gridView);

        // 表格使用的数据适配器
        SimpleAdapter adapter = onGridSimpleAdapter();

        // 设置适配器
        gridView.setAdapter(adapter);

        // 设置监听器
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridItemClick(view, position);
            }
        });
    }

    /**
     * 表格项点击事件触发操作，
     * 默认触发功能跳转，
     * 并检测登录状态
     *
     * @param view     当前点击的功能布局对象
     * @param position 点击的位置索引
     */
    protected void onGridItemClick(View view, int position) {

        if (!MemoryValue.getMemoryValue().isLogin()) {
            // 未登录
            // 新建意图,跳转到登录页面
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            // 执行跳转
            startActivity(intent);
            getActivity().finish();
            return;
        }

        switch (position) {
            case 0:
                // 查询货源
                startActivity(new Intent(getActivity(), GoodsSupplyFindActivity.class));
                break;
            case 1:
                // 发布空车
                startActivity(new Intent(getActivity(), PublishEmptyCarActivity.class));
                break;
            case 2:
                // 我的交易
                startActivity(new Intent(getActivity(), TransactionListActivity.class));
                break;
            case 3:
                // 网上车源
                startActivity(new Intent(getActivity(), CarFieldActivity.class));
                break;
        }
    }

    /**
     * 生成功能项标签资源的数据源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> initFunctionResource() {
        // 加载功能项
        // 资源集合
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        // 功能名称数组
        String[] functionTitle = getResources().getStringArray(R.array.grid_item_function_name_list);
        // 资源类型数组
        TypedArray images = getResources().obtainTypedArray(R.array.grid_item_function_image_list);

        for (int i = 0; i < functionTitle.length; i++) {

            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加功能标签名称资源
            function.put(FUNCTION_NAME, functionTitle[i]);
            Log.i(LOG_TAG + "initFunctionResource", "function name is " + function.get(FUNCTION_NAME));

            // 添加功能标签图标资源
            function.put(FUNCTION_IMAGE, images.getResourceId(i, R.mipmap.ic_launcher));

            // 将标签加入数据源
            dataList.add(function);
        }

        return dataList;
    }
}
