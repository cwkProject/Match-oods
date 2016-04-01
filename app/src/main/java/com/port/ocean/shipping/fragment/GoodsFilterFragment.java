package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2016/3/11.
 */

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.Goods;
import com.port.ocean.shipping.function.CargoTypeSelectList;
import com.port.ocean.shipping.function.VehicleTypeSelectList;

import org.mobile.library.common.function.CitySelectList;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.DataChangeObserver;

/**
 * 找货筛选器
 *
 * @author 超悟空
 * @version 1.0 2016/3/11
 * @since 1.0
 */
public class GoodsFilterFragment extends Fragment {

    /**
     * 控件管理器
     */
    private class ViewHolder {

        /**
         * 起始城市选择列表
         */
        public CitySelectList startCitySelectList = null;

        /**
         * 终点城市选择列表
         */
        public CitySelectList endCitySelectList = null;

        /**
         * 货物类别选择列表
         */
        public CargoTypeSelectList cargoTypeSelectList = null;

        /**
         * 车型选择列表
         */
        public VehicleTypeSelectList vehicleTypeSelectList = null;

        /**
         * 用于显示选择列表的窗口
         */
        public PopupWindow popupWindow = null;

        /**
         * 弹出窗口的内容布局
         */
        public CardView cardView = null;

        /**
         * 起点文本框
         */
        public TextView startTextView = null;

        /**
         * 终点文本框
         */
        public TextView endTextView = null;

        /**
         * 货物文本框
         */
        public TextView goodsTextView = null;

        /**
         * 车型文本框
         */
        public TextView vehicleTextView = null;

        /**
         * 保存部分当前的筛选数据
         */
        public Goods goods = null;

        /**
         * 保存上一步操作的筛选数据
         */
        public String oldData = null;
    }

    /**
     * 条件改动监听器
     */
    private DataChangeObserver<Goods> dataChangeListener = null;

    /**
     * 控件工具
     */
    private ViewHolder viewHolder = new ViewHolder();

    /**
     * 设置条件改动监听器
     *
     * @param dataChangeListener 数据监听器
     */
    public void setDataChangeListener(DataChangeObserver<Goods> dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goods_find_filter, container, false);

        // 初始化布局引用
        initView(rootView);

        // 初始化弹出框
        initPopupWindow();

        // 初始化事件绑定
        initEvent();

        return rootView;
    }

    /**
     * 初始化布局引用
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        viewHolder.startTextView = (TextView) rootView.findViewById(R.id
                .fragment_goods_find_filter_start_textView);
        viewHolder.endTextView = (TextView) rootView.findViewById(R.id
                .fragment_goods_find_filter_end_textView);
        viewHolder.goodsTextView = (TextView) rootView.findViewById(R.id
                .fragment_goods_find_filter_goods_textView);
        viewHolder.vehicleTextView = (TextView) rootView.findViewById(R.id
                .fragment_goods_find_filter_vehicle_textView);

        viewHolder.goods = new Goods();

        viewHolder.startCitySelectList = new CitySelectList(getActivity());
        viewHolder.endCitySelectList = new CitySelectList(getActivity());
        viewHolder.cargoTypeSelectList = new CargoTypeSelectList(getActivity());
        viewHolder.vehicleTypeSelectList = new VehicleTypeSelectList(getActivity());

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
     * 初始化事件绑定
     */
    private void initEvent() {

        viewHolder.startCitySelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                if (s != null) {
                    String[] strings = s.split("-");
                    viewHolder.startTextView.setText(strings[strings.length - 1]);

                    viewHolder.goods.setStartProvince(strings.length > 0 ? strings[0] : null);
                    viewHolder.goods.setStartCity(strings.length > 1 ? strings[1] : null);
                    viewHolder.goods.setStartDistrict(strings.length > 2 ? strings[2] : null);
                }

                viewHolder.popupWindow.dismiss();

                executeFilter();
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
                    String[] strings = s.split("-");
                    viewHolder.endTextView.setText(strings[strings.length - 1]);

                    viewHolder.goods.setEndProvince(strings.length > 0 ? strings[0] : null);
                    viewHolder.goods.setEndCity(strings.length > 1 ? strings[1] : null);
                    viewHolder.goods.setEndDistrict(strings.length > 2 ? strings[2] : null);
                }

                viewHolder.popupWindow.dismiss();

                executeFilter();
            }

            @Override
            public void onCancel(View view) {

            }
        });

        viewHolder.cargoTypeSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.goodsTextView.setText(s);
                viewHolder.popupWindow.dismiss();

                viewHolder.goods.setGoodsName(s);

                executeFilter();
            }

            @Override
            public void onCancel(View view) {

            }
        });

        viewHolder.vehicleTypeSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.vehicleTextView.setText(s);
                viewHolder.popupWindow.dismiss();

                viewHolder.goods.setVehicleType(s);

                executeFilter();
            }

            @Override
            public void onCancel(View view) {

            }
        });

        viewHolder.startTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.startCitySelectList.loadSelect());
            }
        });

        viewHolder.endTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.endCitySelectList.loadSelect());
            }
        });

        viewHolder.goodsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.cargoTypeSelectList.loadSelect());
            }
        });

        viewHolder.vehicleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.vehicleTypeSelectList.loadSelect());
            }
        });
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
     * 数据改变执行筛选
     */
    private void executeFilter() {
        StringBuilder builder = new StringBuilder();

        builder.append(viewHolder.goods.getStartProvince());
        builder.append(viewHolder.goods.getStartCity());
        builder.append(viewHolder.goods.getStartDistrict());
        builder.append(viewHolder.goods.getEndProvince());
        builder.append(viewHolder.goods.getEndCity());
        builder.append(viewHolder.goods.getEndDistrict());
        builder.append(viewHolder.goods.getGoodsName());
        builder.append(viewHolder.goods.getVehicleType());

        String newData = builder.toString();

        if (viewHolder.oldData == null || !viewHolder.oldData.equals(newData)) {
            viewHolder.oldData = newData;
            if (dataChangeListener != null) {
                dataChangeListener.notifyDataChange(viewHolder.goods);
            }
        }
    }
}
