package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/7/2.
 */

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.work.PublishEmptyCar;

import org.mobile.library.common.function.CitySelectList;
import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.function.ISelectList;
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
         * 用于显示选择列表的窗口
         */
        public PopupWindow popupWindow = null;

        /**
         * 弹出窗口的内容布局
         */
        public CardView cardView = null;

        /**
         * 起始城市选择列表
         */
        public CitySelectList startCitySelectList = null;

        /**
         * 终点城市选择列表
         */
        public CitySelectList endCitySelectList = null;

        /**
         * 发布按钮
         */
        public AppCompatButton button = null;

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
     * 初始化选择输入框点击事件
     */
    private void initSelectEdit() {

        viewHolder.startCitySelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                if (s != null) {
                    viewHolder.startEditText.setText(s);
                }

                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });

        viewHolder.endCitySelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                if (s != null) {
                    viewHolder.endEditText.setText(s);
                }

                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });

        // 设置点击事件
        viewHolder.startEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.startCitySelectList.loadSelect());
            }
        });

        viewHolder.endEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.endCitySelectList.loadSelect());

            }
        });
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
        // 初始化弹出框
        initPopupWindow();
        // 初始化按钮
        initButton();
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
        viewHolder.button = (AppCompatButton) rootView.findViewById(R.id
                .fragment_publish_empty_car_button);
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

        viewHolder.startCitySelectList = new CitySelectList(getActivity());
        viewHolder.endCitySelectList = new CitySelectList(getActivity());

        // 弹出窗口布局
        viewHolder.cardView = (CardView) LayoutInflater.from(getActivity()).inflate(R.layout
                .layout_popup_window, null);
        viewHolder.popupWindow = new PopupWindow(getActivity());
    }

    /**
     * 初始化弹出框
     */
    private void initPopupWindow() {
        viewHolder.popupWindow.setContentView(viewHolder.cardView);
        viewHolder.popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        viewHolder.popupWindow.setHeight(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_height));
        viewHolder.popupWindow.setFocusable(true);
        viewHolder.popupWindow.setOutsideTouchable(true);
        viewHolder.popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 显示PopupWindow
     *
     * @param anchor 依附的布局
     * @param view   要显示的布局
     */
    private void showPopupWindow(View anchor, View view) {
        if (!viewHolder.popupWindow.isShowing()) {
            viewHolder.cardView.removeAllViews();
            viewHolder.cardView.addView(view);
            viewHolder.popupWindow.showAsDropDown(anchor);
        }
    }

    /**
     * 初始化发布空车按钮
     */
    private void initButton() {

        // 改变着色
        viewHolder.button.setSupportBackgroundTintList(getResources().getColorStateList((android
                .R.color.holo_red_light)));

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.button.setEnabled(false);
                // 执行发布
                onPublishCar();
            }
        });
    }

    /**
     * 发布空车
     */
    private void onPublishCar() {

        // 关闭软键盘
        InputMethodController.CloseInputMethod(getActivity());

        if (!GlobalApplication.getLoginStatus().isLogin()) {
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

        publishEmptyCar.beginExecute(GlobalApplication.getLoginStatus().getUserID(), start[0],
                start.length > 1 ? start[1] : "", end[0], end.length > 1 ? end[1] : "", back,
                favorable);
    }

}
