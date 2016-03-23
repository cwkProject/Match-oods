package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.activity.IdentityActivity;
import com.port.ocean.shipping.activity.LoginActivity;
import com.port.ocean.shipping.data.IdentityInfoData;
import com.port.ocean.shipping.util.MemoryValue;
import com.port.ocean.shipping.work.PullIdentityInfo;

import org.mobile.library.model.work.WorkBack;

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
     * 列表使用的数据适配器
     */
    private SimpleAdapter adapter = null;

    /**
     * 数据适配器的元数据
     */
    private List<Map<String, Object>> adapterDataList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);


        // 初始化功能列表
        initListView(rootView);
        // 初始化退出登录按钮
        initExitButton(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 初始化个人信息布局
        initUserLayout(getView());
    }

    /**
     * 初始化个人信息布局
     *
     * @param rootView 根布局
     */
    private void initUserLayout(View rootView) {
        final TextView userTextView = (TextView) rootView.findViewById(R.id
                .fragment_mine_user_textView);

        if (MemoryValue.getMemoryValue().getUserName() != null) {
            userTextView.setText(MemoryValue.getMemoryValue().getUserName());
        } else {
            PullIdentityInfo pullIdentityInfo = new PullIdentityInfo();

            pullIdentityInfo.setWorkEndListener(new WorkBack<IdentityInfoData>() {
                @Override
                public void doEndWork(boolean state, IdentityInfoData data) {
                    if (state) {
                        // 填充数据
                        MemoryValue.getMemoryValue().setUserName(data.getUserName());
                        userTextView.setText(MemoryValue.getMemoryValue().getUserName());
                    }
                }
            });

            pullIdentityInfo.beginExecute(MemoryValue.getMemoryValue().getUserID());
        }
    }

    /**
     * 初始化功能表格布局
     *
     * @param rootView 根布局
     */
    private void initListView(View rootView) {

        // 片段中的列表布局
        ListView listView = (ListView) rootView.findViewById(R.id.fragment_mine_list_view);

        // 列表使用的数据适配器
        adapter = new SimpleAdapter(getActivity(), getFunctionTitle(), R.layout
                .mine_function_item, new String[]{FUNCTION_TITLE , FUNCTION_IMAGE}, new int[]{R.id.mine_function_item_textView , R.id.mine_function_item_imageView});

        // 设置适配器
        listView.setAdapter(adapter);

        // 设置监听器
        listView.setOnItemClickListener(this);
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
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // 身份认证
                identity();
                break;
            default:
                break;
        }
    }

    /**
     * 身份认证
     */
    private void identity() {
        Intent intent = new Intent(getActivity(), IdentityActivity.class);
        startActivity(intent);
    }

    /**
     * 初始化退出登录按钮
     *
     * @param rootView 根布局
     */
    private void initExitButton(View rootView) {
        // 退出按钮
        Button button = (Button) rootView.findViewById(R.id.fragment_mine_exit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 清空登录状态
                //MemoryValue.getMemoryValue().Reset();
                // 清空保存记录
                //GlobalApplication.getApplicationConfig().Clear();

                // 跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
