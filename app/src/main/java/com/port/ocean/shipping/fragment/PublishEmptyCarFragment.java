package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/7/2.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.util.MemoryValue;
import com.port.ocean.shipping.work.PublishEmptyCar;

import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.model.operate.EmptyParameterObserver;
import org.mobile.library.model.work.WorkBack;

/**
 * 发布空车片段
 *
 * @author 超悟空
 * @version 1.0 2015/7/2
 * @since 1.0
 */
public class PublishEmptyCarFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PublishEmptyCarFragment.";

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

        /**
         * 回程复选框
         */
        public CheckBox backCheckBox = null;

        /**
         * 优惠复选框
         */
        public CheckBox favorableCheckBox = null;
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
        View rootView = inflater.inflate(R.layout.fragment_publish_empty_car, container, false);

        // 初始化布局
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        // 初始化控件集
        initViewHolder(rootView);
        // 初始化按钮
        initButton(rootView);
        // 绑定选择输入框点击事件
        initSelectEdit();
    }

    /**
     * 初始化控件集引用
     *
     * @param rootView 根布局
     */
    private void initViewHolder(View rootView) {
        // 查询按钮
        viewHolder.button = (Button) rootView.findViewById(R.id.fragment_publish_empty_car_button);
        // 始发地输入框
        viewHolder.startEditText = (EditText) rootView.findViewById(R.id
                .fragment_publish_empty_car_start_editText);
        // 目的地输入框
        viewHolder.endEditText = (EditText) rootView.findViewById(R.id
                .fragment_publish_empty_car_end_editText);
        // 回程复选框
        viewHolder.backCheckBox = (CheckBox) rootView.findViewById(R.id
                .fragment_publish_empty_car_back_checkBox);
        // 优惠复选框
        viewHolder.favorableCheckBox = (CheckBox) rootView.findViewById(R.id
                .fragment_publish_empty_car_favorable_checkBox);
    }

    /**
     * 初始化发布空车按钮
     */
    private void initButton(final View rootView) {

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.button.setEnabled(false);
                // 执行发布
                onPublishCar(rootView);
            }
        });
    }

    /**
     * 发布空车
     */
    private void onPublishCar(final View rootView) {

        // 关闭软键盘
        InputMethodController.CloseInputMethod(getActivity());

        if (!MemoryValue.getMemoryValue().isLogin()) {
            Toast.makeText(getActivity(), R.string.no_login, Toast.LENGTH_SHORT).show();
            viewHolder.button.setEnabled(true);
            return;
        }

        // 提取文本
        String[] start = viewHolder.startEditText.getText().toString().trim().split("-");
        String[] end = viewHolder.endEditText.getText().toString().trim().split("-");

        Log.i(LOG_TAG + "onPublishCar", "start is " + viewHolder.startEditText.getText().toString
                ());
        Log.i(LOG_TAG + "onPublishCar", "end is " + viewHolder.endEditText.getText().toString());

        if (start.length == 0 || end.length == 0) {
            Toast.makeText(getActivity(), R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            viewHolder.button.setEnabled(true);
            return;
        }

        // 提取复选框值
        String back = viewHolder.backCheckBox.isChecked() ? "1" : "0";
        String favorable = viewHolder.favorableCheckBox.isChecked() ? "1" : "0";

        // 发布空车任务
        PublishEmptyCar publishEmptyCar = new PublishEmptyCar();

        publishEmptyCar.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {

                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();

                if (state) {
                    getActivity().finish();
                } else {
                    viewHolder.button.setEnabled(true);
                }
            }
        });

        publishEmptyCar.beginExecute(MemoryValue.getMemoryValue().getUserID(), start[0], start
                .length > 1 ? start[1] : "", end[0], end.length > 1 ? end[1] : "", back, favorable);
    }

}
