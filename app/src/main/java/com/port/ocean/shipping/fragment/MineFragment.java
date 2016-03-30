package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.activity.IdentityActivity;
import com.port.ocean.shipping.activity.LoginActivity;
import com.port.ocean.shipping.activity.VehicleManagementActivity;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.util.UserInfo;

import org.mobile.library.global.GlobalApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我页面
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class MineFragment extends Fragment implements AdapterView.OnItemClickListener {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MineFragment.";

    /**
     * 功能标题的取值标签，用于数据适配器中
     */
    private static final String FUNCTION_TITLE = "function_title";

    /**
     * 功能图标取值图标
     */
    private static final String FUNCTION_IMAGE = "function_image";

    /**
     * 控件管理器
     */
    private class ViewHolder {
        /**
         * 用户姓名文本框
         */
        public TextView userTextView = null;

        /**
         * 数据加载结果的广播接收者
         */
        public LoadingReceiver loadingReceiver = null;

        /**
         * 功能列表
         */
        public ListView listView = null;
    }

    /**
     * 控件工具
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        // 开启追加菜单项
        setHasOptionsMenu(true);
        // 初始化控件引用
        initView(rootView);

        // 初始化功能列表
        initListView();

        // 注册广播
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(viewHolder
                .loadingReceiver, viewHolder.loadingReceiver.getRegisterIntentFilter());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // 初始化个人信息布局
        initUserLayout();
    }

    @Override
    public void onDestroy() {

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(viewHolder
                .loadingReceiver);

        super.onDestroy();
    }

    /**
     * 初始化控件引用
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        viewHolder.loadingReceiver = new LoadingReceiver();
        viewHolder.userTextView = (TextView) rootView.findViewById(R.id
                .fragment_mine_user_textView);
        viewHolder.listView = (ListView) rootView.findViewById(R.id.fragment_mine_list_view);
    }

    /**
     * 初始化个人信息布局
     */
    private void initUserLayout() {
        if (UserInfo.getInstance().getRealName() != null && viewHolder.userTextView != null) {
            viewHolder.userTextView.setText(UserInfo.getInstance().getRealName());
        }
    }

    /**
     * 初始化功能表格布局
     */
    private void initListView() {

        // 列表使用的数据适配器
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getFunctionTitle(), R.layout
                .mine_function_item, new String[]{FUNCTION_TITLE , FUNCTION_IMAGE}, new int[]{R.id.mine_function_item_textView , R.id.mine_function_item_imageView});

        // 设置适配器
        viewHolder.listView.setAdapter(adapter);

        // 设置监听器
        viewHolder.listView.setOnItemClickListener(this);
    }

    /**
     * 生成功能项标签资源的数据源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> getFunctionTitle() {
        // 加载功能项
        List<Map<String, Object>> dataList = new ArrayList<>();

        String[] functionTitle = getActivity().getResources().getStringArray(R.array
                .mine_function_title);
        // 资源类型数组
        TypedArray images = getResources().obtainTypedArray(R.array.mine_function_image);

        for (int i = 0; i < functionTitle.length; i++) {
            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加标签资源
            // 添加标题
            function.put(FUNCTION_TITLE, functionTitle[i]);
            // 添加功能标签图标资源
            function.put(FUNCTION_IMAGE, images.getResourceId(i, R.mipmap.ic_launcher));

            // 将标签加入数据源
            dataList.add(function);
        }

        images.recycle();

        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // 身份认证
                identity();
                break;
            case 1:
                // 我的货车
                vehicle();
            default:
                break;
        }
    }

    /**
     * 车牌
     */
    private void vehicle() {
        Intent intent = new Intent(getActivity(), VehicleManagementActivity.class);
        startActivity(intent);
    }

    /**
     * 身份认证
     */
    private void identity() {
        Intent intent = new Intent(getActivity(), IdentityActivity.class);
        startActivity(intent);
    }

    /**
     * 退出登录
     */
    private void logout() {

        // 清空保存记录
        GlobalApplication.getApplicationConfig().setPassword(null);
        GlobalApplication.getApplicationConfig().Save();

        // 跳转到登录界面
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mine, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_mine_logout:
                // 退出登录
                logout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 数据加载结果的广播接收者
     *
     * @author 超悟空
     * @version 1.0 2016/3/24
     * @since 1.0
     */
    private class LoadingReceiver extends BroadcastReceiver {

        /**
         * 得到本接收者监听的动作集合
         *
         * @return 填充完毕的意图集合
         */
        public final IntentFilter getRegisterIntentFilter() {
            // 新建动作集合
            IntentFilter filter = new IntentFilter();

            // 数据加载
            filter.addAction(StaticValue.BroadcastAction.USER_INFO_CHANGE_TAG);
            return filter;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // 得到动作字符串
            String actionString = intent.getAction();
            Log.i(LOG_TAG + "LoadingReceiver.onReceive", "action is " + actionString);

            switch (actionString) {
                case StaticValue.BroadcastAction.USER_INFO_CHANGE_TAG:
                    initUserLayout();
                    break;
            }
        }
    }
}
