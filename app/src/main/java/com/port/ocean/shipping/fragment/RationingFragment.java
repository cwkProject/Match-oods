package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.activity.GoodsSupplyFindResultListActivity;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.model.operate.EmptyParameterObserver;

/**
 * 配货页面
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class RationingFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "RationingFragment.";

    /**
     * 子控件集合
     *
     * @author 超悟空
     * @version 1.0 2015/7/17
     * @since 1.0
     */
    private class ViewHolder {
        /**
         * 查找按钮
         */
        public Button button = null;

        /**
         * 始发地输入框
         */
        public EditText startEditText = null;

        /**
         * 目的地输入框
         */
        public EditText endEditText = null;
    }

    /**
     * 当前操作的文本框，1为始发地，2为目的地
     */
    private int currentEdit = 0;

    /**
     * 输入框点击事件回调
     */
    private EmptyParameterObserver editClickObserver = null;

    /**
     * 子控件集合对象
     */
    private ViewHolder viewHolder = new ViewHolder();

    /**
     * 设置输入框点击事件监听
     *
     * @param editClickObserver 监听器
     */
    public void setEditClickObserver(EmptyParameterObserver editClickObserver) {
        this.editClickObserver = editClickObserver;
        // 绑定选择输入框点击事件
        initSelectEdit();
    }

    /**
     * 设置输入框显示值
     *
     * @param province 省
     * @param city     市
     * @param district 区
     */
    public void setEditValue(String province, String city, String district) {
        String value = province;
        if (city != null) {
            value += "-" + city;
        }

        if (district != null) {
            value += "-" + district;
        }

        Log.i(LOG_TAG + "setEditValue", "value is " + value);
        Log.i(LOG_TAG + "setEditValue", "current edit is " + currentEdit);

        switch (currentEdit) {
            case 1:
                // 始发地
                viewHolder.startEditText.setText(value);
                break;
            case 2:
                // 目的地
                viewHolder.endEditText.setText(value);
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rationing, container, false);

        // 初始化布局
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化布局
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        // 初始化控件集
        initViewHolder(rootView);

        // 初始化查找按钮
        initButton();
    }

    /**
     * 初始化控件集引用
     *
     * @param rootView 根布局
     */
    private void initViewHolder(View rootView) {
        // 查询按钮
        viewHolder.button = (Button) rootView.findViewById(R.id.fragment_rationing_button);
        // 始发地输入框
        viewHolder.startEditText = (EditText) rootView.findViewById(R.id
                .fragment_rationing_start_editText);
        // 目的地输入框
        viewHolder.endEditText = (EditText) rootView.findViewById(R.id
                .fragment_rationing_end_editText);
    }

    /**
     * 初始化选择输入框点击事件
     */
    private void initSelectEdit() {
        // 设置点击事件
        viewHolder.startEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentEdit = 1;
                Log.i(LOG_TAG + "initSelectEdit", "start edit is clicked");
                editClickObserver.invoke();
            }
        });

        viewHolder.endEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentEdit = 2;
                Log.i(LOG_TAG + "initSelectEdit", "end edit is clicked");
                editClickObserver.invoke();
            }
        });
    }

    /**
     * 初始化查找按钮
     */
    private void initButton() {
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFind();
            }
        });
    }

    /**
     * 执行查找
     */
    private void onFind() {

        // 关闭软键盘
        InputMethodController.CloseInputMethod(getActivity());

        String[] start = viewHolder.startEditText.getText().toString().trim().split("-");
        String[] end = viewHolder.endEditText.getText().toString().trim().split("-");

        Log.i(LOG_TAG + "onFind", "start is " + viewHolder.startEditText.getText().toString());
        Log.i(LOG_TAG + "onFind", "end is " + viewHolder.endEditText.getText().toString());

        if (start.length == 0 || end.length == 0 || start[0].length() == 0 || end[0].length() ==
                0) {
            Toast.makeText(getActivity(), R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getActivity(), GoodsSupplyFindResultListActivity.class);

        intent.putExtra(StaticValue.IntentTag.START_PROVINCE_TAG, start[0]);
        intent.putExtra(StaticValue.IntentTag.START_CITY_TAG, start.length > 1 ? start[1] : "");
        intent.putExtra(StaticValue.IntentTag.START_DISTRICT_TAG, start.length > 2 ? start[2] : "");
        intent.putExtra(StaticValue.IntentTag.END_PROVINCE_TAG, end[0]);
        intent.putExtra(StaticValue.IntentTag.END_CITY_TAG, end.length > 1 ? end[1] : "");
        intent.putExtra(StaticValue.IntentTag.END_DISTRICT_TAG, end.length > 2 ? end[2] : "");
        startActivity(intent);
    }
}
