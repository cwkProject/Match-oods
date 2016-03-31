package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2016/3/30.
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.bean.Vehicle;
import com.port.ocean.shipping.function.VehiclePlateSelectList;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.work.DeleteVehicle;
import com.port.ocean.shipping.work.UpdateVehicle;

import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.work.IWorkEndListener;

import java.util.Arrays;

/**
 * 编辑车牌号Activity
 *
 * @author 超悟空
 * @version 1.0 2016/3/30
 * @since 1.0
 */
public class EditVehicleActivity extends AppCompatActivity {

    /**
     * 控件管理器
     */
    private class ViewHolder {

        /**
         * 车牌选择列表
         */
        public VehiclePlateSelectList vehiclePlateSelectList = null;

        /**
         * 用于显示选择列表的窗口
         */
        public PopupWindow popupWindow = null;

        /**
         * 弹出窗口的内容布局
         */
        public CardView cardView = null;

        /**
         * 车牌号输入框
         */
        public EditText licensePlateNumberEditText = null;

        /**
         * 车牌选择按钮
         */
        public AppCompatButton licensePlateButton = null;

        /**
         * 保存按钮
         */
        public AppCompatButton saveButton = null;

        /**
         * 删除按钮
         */
        public AppCompatButton deleteButton = null;

        /**
         * 当前在编辑的车辆信息
         */
        public Vehicle vehicle = null;
    }

    /**
     * 控件工具
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_edit);

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
        setTitle(R.string.title_vehicle_detail);
        // 初始化控件集
        initViewHolder();
        // 初始化弹出框
        initPopupWindow();
        // 初始化车牌选择按钮
        initVehiclePlateSelect();
        // 初始化输入框
        initEditText();
        // 初始化保存按钮
        initSaveButton();
        // 初始化删除按钮
        initDeleteButton();
        // 初始化数据
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
        viewHolder.licensePlateNumberEditText = (EditText) findViewById(R.id
                .activity_vehicle_edit_license_plate_number_editText);
        viewHolder.licensePlateButton = (AppCompatButton) findViewById(R.id
                .activity_vehicle_edit_license_plate_button);
        viewHolder.saveButton = (AppCompatButton) findViewById(R.id
                .activity_vehicle_edit_save_button);
        viewHolder.deleteButton = (AppCompatButton) findViewById(R.id
                .activity_vehicle_edit_delete_button);
        viewHolder.vehiclePlateSelectList = new VehiclePlateSelectList(this);
        viewHolder.popupWindow = new PopupWindow(this);
        // 弹出窗口布局
        viewHolder.cardView = (CardView) LayoutInflater.from(this).inflate(R.layout
                .layout_popup_window, null);
    }

    /**
     * 初始化车牌选择
     */
    private void initVehiclePlateSelect() {

        // 改变着色
        TypedArray typedArray = obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        viewHolder.licensePlateButton.setSupportBackgroundTintList(typedArray.getColorStateList(0));
        typedArray.recycle();

        // 设置点击事件
        viewHolder.licensePlateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭软键盘
                InputMethodController.CloseInputMethod(EditVehicleActivity.this);
                showPopupWindow(v, viewHolder.vehiclePlateSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.vehiclePlateSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.licensePlateButton.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化输入框
     */
    private void initEditText() {
        // 字符过滤，强制大写
        InputFilter[] filters = viewHolder.licensePlateNumberEditText.getFilters();
        InputFilter[] inputFilters = Arrays.copyOf(filters, filters.length + 1);
        inputFilters[filters.length] = new InputFilter.AllCaps();
        viewHolder.licensePlateNumberEditText.setFilters(inputFilters);
    }

    /**
     * 初始化保存按钮
     */
    private void initSaveButton() {
        // 改变着色
        TypedArray typedArray = obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        viewHolder.saveButton.setSupportBackgroundTintList(typedArray.getColorStateList(0));
        typedArray.recycle();

        // 设置点击事件
        viewHolder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveVehicle();
            }
        });
    }

    /**
     * 初始化删除按钮
     */
    private void initDeleteButton() {
        // 改变着色
        viewHolder.deleteButton.setSupportBackgroundTintList(getResources().getColorStateList(
                (android.R.color.holo_red_light)));

        // 设置点击事件
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteVehicle();
            }
        });
    }

    /**
     * 删除车辆
     */
    private void onDeleteVehicle() {
        // 关闭软键盘
        InputMethodController.CloseInputMethod(this);

        if (viewHolder.vehicle == null) {
            Toast.makeText(EditVehicleActivity.this, R.string.prompt_data_error, Toast
                    .LENGTH_SHORT).show();
            return;
        }

        viewHolder.deleteButton.setEnabled(false);

        DeleteVehicle deleteVehicle = new DeleteVehicle();

        deleteVehicle.setWorkEndListener(new IWorkEndListener<Void>() {
            @Override
            public void doEndWork(boolean state, String message, Void data) {
                Toast.makeText(EditVehicleActivity.this, message, Toast.LENGTH_SHORT).show();
                viewHolder.deleteButton.setEnabled(true);
                if (state) {
                    // 删除成功
                    setResult(RESULT_FIRST_USER, null);

                    finish();
                }
            }
        });

        deleteVehicle.beginExecute(viewHolder.vehicle.getId());
    }

    /**
     * 保存车辆信息
     */
    private void onSaveVehicle() {
        // 关闭软键盘
        InputMethodController.CloseInputMethod(this);

        String licensePlateNumber = viewHolder.licensePlateNumberEditText.getText().toString();

        if (viewHolder.vehicle == null) {
            Toast.makeText(EditVehicleActivity.this, R.string.prompt_data_error, Toast
                    .LENGTH_SHORT).show();
            return;
        }

        if (licensePlateNumber.length() != 6) {
            Toast.makeText(this, R.string.prompt_license_plate_number_error, Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        viewHolder.saveButton.setEnabled(false);

        UpdateVehicle updateVehicle = new UpdateVehicle();

        updateVehicle.setWorkEndListener(new IWorkEndListener<Vehicle>() {
            @Override
            public void doEndWork(boolean state, String message, Vehicle data) {
                Toast.makeText(EditVehicleActivity.this, message, Toast.LENGTH_SHORT).show();
                viewHolder.saveButton.setEnabled(true);
                if (state) {
                    // 修改成功
                    Intent intent = new Intent();
                    intent.putExtra(StaticValue.IntentTag.VEHICLE_DETAIL_TAG, data);

                    setResult(RESULT_OK, intent);

                    finish();
                }
            }
        });

        updateVehicle.beginExecute(viewHolder.vehicle.getId(), viewHolder.licensePlateButton
                .getText().toString() + licensePlateNumber);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        viewHolder.vehicle = getIntent().getParcelableExtra(StaticValue.IntentTag
                .VEHICLE_DETAIL_TAG);

        if (viewHolder.vehicle != null && viewHolder.vehicle.getLicensePlateNumber() != null) {
            viewHolder.licensePlateButton.setText(viewHolder.vehicle.getLicensePlateNumber()
                    .substring(0, 1));

            viewHolder.licensePlateNumberEditText.setText(viewHolder.vehicle
                    .getLicensePlateNumber().substring(1));
        }
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
}
