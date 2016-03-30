package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/24.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.IdentityInfoData;
import com.port.ocean.shipping.function.LoadUserInfo;
import com.port.ocean.shipping.function.VehicleLengthSelectList;
import com.port.ocean.shipping.function.VehiclePlateSelectList;
import com.port.ocean.shipping.function.VehicleTypeSelectList;
import com.port.ocean.shipping.work.IdentityAuthenticate;
import com.port.ocean.shipping.work.PullIdentityInfo;

import org.mobile.library.common.dialog.SimpleDialog;
import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.work.WorkBack;

/**
 * 身份认证
 *
 * @author 超悟空
 * @version 1.0 2015/6/24
 * @since 1.0
 */
public class IdentityActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "IdentityActivity.";

    /**
     * 子控件集合
     *
     * @author 超悟空
     * @version 1.0 2015/7/16
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
         * 车长选择工具
         */
        public VehicleLengthSelectList vehicleLengthSelectList = null;

        /**
         * 车牌选择工具
         */
        public VehiclePlateSelectList vehiclePlateSelectList = null;

        /**
         * 车型选择工具
         */
        public VehicleTypeSelectList vehicleTypeSelectList = null;

        /**
         * 认证按钮
         */
        public Button button = null;

        /**
         * 姓名输入框
         */
        public EditText nameEditText = null;

        /**
         * 身份证输入框
         */
        public EditText idCardEditText = null;

        /**
         * 车牌选择按钮
         */
        public Button vehiclePlateNumberButton = null;

        /**
         * 车牌号输入框
         */
        public EditText vehiclePlateNumberEditText = null;

        /**
         * 车型输入框
         */
        public EditText vehicleTypeEditText = null;

        /**
         * 车长输入框
         */
        public EditText vehicleLengthEditText = null;

        /**
         * 载重输入框
         */
        public EditText vehicleLoadEditText = null;

        /**
         * 身份证拍照
         */
        public TextView idCardCameraTextView = null;

        /**
         * 驾驶证拍照
         */
        public TextView drivingLicenseCameraTextView = null;

        /**
         * 行驶证拍照
         */
        public TextView drivingPermitCameraTextView = null;
    }

    /**
     * 控件集
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_authentication);

        // 初始化布局
        initView();
        // 初始化数据
        initData();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        // 设置标题
        setTitle(R.string.title_identity_authentication);
        // 初始化控件集
        initViewHolder();
        // 初始化弹出框
        initPopupWindow();
        // 初始化认证按钮
        initButton();
        // 初始化拍照按钮
        initCamera();
        // 初始化选择输入框点击事件
        initSelectEdit();
    }

    /**
     * 初始化控件集引用
     */
    private void initViewHolder() {
        // 弹出窗口布局
        viewHolder.cardView = (CardView) LayoutInflater.from(this).inflate(R.layout
                .layout_popup_window, null);
        viewHolder.popupWindow = new PopupWindow(this);

        // 车长选择工具
        viewHolder.vehicleLengthSelectList = new VehicleLengthSelectList(this);
        // 车牌选择工具
        viewHolder.vehiclePlateSelectList = new VehiclePlateSelectList(this);
        // 车型选择工具
        viewHolder.vehicleTypeSelectList = new VehicleTypeSelectList(this);

        // 姓名输入框
        viewHolder.nameEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_name_edit);
        // 身份证输入框
        viewHolder.idCardEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_id_card_edit);
        // 车牌选择按钮
        viewHolder.vehiclePlateNumberButton = (Button) findViewById(R.id
                .activity_identity_authentication_vehicle_number_button);
        // 车牌号输入框
        viewHolder.vehiclePlateNumberEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_vehicle_number_edit);
        // 车型输入框
        viewHolder.vehicleTypeEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_models_edit);
        // 车长输入框
        viewHolder.vehicleLengthEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_car_length_edit);
        // 载重输入框
        viewHolder.vehicleLoadEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_load_edit);
        // 身份证拍照
        viewHolder.idCardCameraTextView = (TextView) findViewById(R.id
                .activity_identity_authentication_id_card_camera);
        // 驾驶证拍照
        viewHolder.drivingLicenseCameraTextView = (TextView) findViewById(R.id
                .activity_identity_authentication_driving_license_camera);
        // 行驶证拍照
        viewHolder.drivingPermitCameraTextView = (TextView) findViewById(R.id
                .activity_identity_authentication_driving_permit_camera);
        // 认证按钮
        viewHolder.button = (Button) findViewById(R.id.activity_identity_authentication_button);
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
     * 初始化选择输入框点击事件
     */
    private void initSelectEdit() {
        // 初始化车型选择
        initVehicleTypeSelect();
        // 初始化车长选择
        initVehicleLengthSelect();
        // 初始化车牌选择
        initVehiclePlateSelect();
    }

    /**
     * 初始化车型选择
     */
    private void initVehicleTypeSelect() {

        // 设置点击事件
        viewHolder.vehicleTypeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.vehicleTypeSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.vehicleTypeSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.vehicleTypeEditText.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化车长选择
     */
    private void initVehicleLengthSelect() {

        // 设置点击事件
        viewHolder.vehicleLengthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.vehicleLengthSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.vehicleLengthSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.vehicleLengthEditText.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化车牌选择
     */
    private void initVehiclePlateSelect() {

        // 设置点击事件
        viewHolder.vehiclePlateNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.vehiclePlateSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.vehiclePlateSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.vehiclePlateNumberButton.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        PullIdentityInfo pullIdentityInfo = new PullIdentityInfo();

        pullIdentityInfo.setWorkEndListener(new WorkBack<IdentityInfoData>() {
            @Override
            public void doEndWork(boolean state, IdentityInfoData data) {
                if (state) {
                    // 填充数据
                    onFillData(data);
                }
            }
        });

        pullIdentityInfo.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }

    /**
     * 填充数据
     *
     * @param data 数据源
     */
    private void onFillData(IdentityInfoData data) {
        // 填充数据
        viewHolder.nameEditText.setText(data.getUserName());
        viewHolder.idCardEditText.setText(data.getIdentityCard());

        if (data.getVehicleNumber() != null) {
            String vehiclePlate = data.getVehicleNumber().substring(0, 1);
            String vehiclePlateNumber = data.getVehicleNumber().substring(1);
            viewHolder.vehiclePlateNumberButton.setText(vehiclePlate);
            viewHolder.vehiclePlateNumberEditText.setText(vehiclePlateNumber);
        }
        viewHolder.vehicleTypeEditText.setText(data.getVehicleType());
        viewHolder.vehicleLengthEditText.setText(data.getVehicleLength());
        viewHolder.vehicleLoadEditText.setText(data.getTons());
    }

    /**
     * 初始化标题栏
     */
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
     * 初始化认证按钮
     */
    private void initButton() {

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭点击响应
                viewHolder.button.setEnabled(false);
                onAuthentication();
            }
        });

    }

    /**
     * 执行认证按钮点击事件
     */
    private void onAuthentication() {

        // 关闭软键盘
        InputMethodController.CloseInputMethod(this);

        // 提取文本信息
        String name = viewHolder.nameEditText.getText().toString().trim();
        String idCard = viewHolder.idCardEditText.getText().toString().trim();
        String vehiclePlate = viewHolder.vehiclePlateNumberButton.getText().toString().trim();
        String vehiclePlateNumber = viewHolder.vehiclePlateNumberEditText.getText().toString()
                .trim();
        String vehicleType = viewHolder.vehicleTypeEditText.getText().toString().trim();
        String vehicleLength = viewHolder.vehicleLengthEditText.getText().toString().trim();
        String vehicleLoad = viewHolder.vehicleLoadEditText.getText().toString().trim();

        if (!GlobalApplication.getLoginStatus().isLogin()) {
            Toast.makeText(this, R.string.no_login, Toast.LENGTH_SHORT).show();
            // 打开点击响应
            viewHolder.button.setEnabled(true);
            return;
        }

        if (name.length() == 0 || idCard.length() == 0) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            // 打开点击响应
            viewHolder.button.setEnabled(true);
            return;
        }

        // 新建认证对象
        IdentityAuthenticate identityAuthenticate = new IdentityAuthenticate();

        identityAuthenticate.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {
                if (state) {
                    Toast.makeText(IdentityActivity.this, data, Toast.LENGTH_SHORT).show();
                    // 加载用户数据
                    LoadUserInfo.onLoadUserInfo();
                    finish();
                } else {
                    SimpleDialog.showDialog(IdentityActivity.this, data);
                    // 打开点击响应
                    viewHolder.button.setEnabled(true);
                }
            }
        });

        identityAuthenticate.beginExecute(GlobalApplication.getLoginStatus().getUserID(), name,
                idCard, vehiclePlate + vehiclePlateNumber, vehicleType, vehicleLength, vehicleLoad);
    }

    /**
     * 初始化拍照按钮
     */
    private void initCamera() {

        viewHolder.idCardCameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        viewHolder.drivingLicenseCameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        viewHolder.drivingPermitCameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

}
