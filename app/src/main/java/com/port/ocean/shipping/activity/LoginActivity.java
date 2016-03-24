package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/23.
 */


import android.content.Intent;
import android.view.View;

import com.port.ocean.shipping.function.LoadUserInfo;

import org.mobile.library.model.activity.BaseLoginActivity;

/**
 * 用户登录Activity
 *
 * @author 超悟空
 * @version 2.0 2016/3/22
 * @since 1.0
 */
public class LoginActivity extends BaseLoginActivity {

    @Override
    protected void onLoginSuccess(String message) {
        goMain();
    }

    /**
     * 跳转到主界面
     */
    private void goMain() {
        // 加载用户数据
        LoadUserInfo.onLoadUserInfo();
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    @Override
    public void onRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
