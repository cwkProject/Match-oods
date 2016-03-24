package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.content.Intent;

import com.port.ocean.shipping.function.LoadUserInfo;

import org.mobile.library.model.activity.BaseRegisterActivity;

/**
 * 注册Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class RegisterActivity extends BaseRegisterActivity {

    @Override
    protected void onRegisterSuccess() {
        goMain();
    }

    /**
     * 跳转到主界面
     */
    private void goMain() {
        // 加载用户数据
        LoadUserInfo.onLoadUserInfo();
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
        finish();
    }
}
