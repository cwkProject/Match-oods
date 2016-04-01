package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/7/1.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.EmptyCarStateData;
import com.port.ocean.shipping.work.RevokedEmptyCar;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;

/**
 * 撤销空车片段
 *
 * @author 超悟空
 * @version 1.0 2015/7/1
 * @since 1.0
 */
public class RevokedEmptyCarFragment extends Fragment {

    /**
     * 空车数据源
     */
    private EmptyCarStateData data = null;

    /**
     * 撤销按钮
     */
    private AppCompatButton button = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_revoked_empty_car, container, false);

        // 初始化布局
        initView(rootView);
        // 初始化数据
        initData(rootView);

        return rootView;
    }

    /**
     * 初始化数据
     *
     * @param rootView 根布局
     */
    private void initData(View rootView) {
        TextView startTextView = (TextView) rootView.findViewById(R.id
                .fragment_revoked_empty_car_start_route_textView);
        TextView endTextView = (TextView) rootView.findViewById(R.id
                .fragment_revoked_empty_car_end_route_textView);
        TextView backTextView = (TextView) rootView.findViewById(R.id
                .fragment_revoked_empty_car_back_textView);
        TextView favorableTextView = (TextView) rootView.findViewById(R.id
                .fragment_revoked_empty_car_rebate_textView);

        if (data != null) {
            String start = null;

            if (data.getStartProvince().trim().length() != 0) {
                start = data.getStartProvince().trim();
            }

            if (data.getStartCity().trim().length() != 0) {
                start += "-" + data.getStartCity().trim();
            }

            String end = null;

            if (data.getEndProvince().trim().length() != 0) {
                end = data.getEndProvince().trim();
            }

            if (data.getEndCity().trim().length() != 0) {
                end += "-" + data.getEndCity().trim();
            }

            startTextView.setText(start);
            endTextView.setText(end);

            if ("1".equals(data.getBack())) {
                backTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap
                        .right_small_icon, 0);
            }

            if ("1".equals(data.getFavorable())) {
                favorableTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap
                        .right_small_icon, 0);
            }
        }
    }

    /**
     * 设置空车数据源
     *
     * @param data 数据源对象
     */
    public RevokedEmptyCarFragment setData(EmptyCarStateData data) {
        this.data = data;
        return this;
    }

    /**
     * 初始化布局
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        // 初始化撤销按钮
        initButton(rootView);
    }

    /**
     * 初始化撤销按钮
     *
     * @param rootView 根布局
     */
    private void initButton(final View rootView) {
        button = (AppCompatButton) rootView.findViewById(R.id.fragment_revoked_empty_car_button);

        // 改变着色
        button.setSupportBackgroundTintList(getResources().getColorStateList((android.R.color
                .holo_red_light)));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                onRevoked();
            }
        });
    }

    /**
     * 撤销发布
     */
    private void onRevoked() {
        RevokedEmptyCar revokedEmptyCar = new RevokedEmptyCar();
        revokedEmptyCar.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {

                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();

                if (state) {
                    getActivity().finish();
                }

                button.setEnabled(true);
            }
        });

        revokedEmptyCar.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }
}
