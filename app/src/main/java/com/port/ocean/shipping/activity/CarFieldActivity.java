package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/29.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.util.StaticValue;

import org.mobile.library.common.webview.MobileWebViewFactory;

/**
 * 网上车源Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/29
 * @since 1.0
 */
public class CarFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_field);

        // 初始化布局
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        // 设置标题
        setTitle(R.string.title_car_field);
        // 初始化网页
        initWebView();
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
     * 初始化网页
     */
    private void initWebView() {
        WebView webView = (WebView) findViewById(R.id.activity_car_field_webView);

        MobileWebViewFactory.assemblingWebView(this, webView);

        webView.loadUrl(StaticValue.Url.CAR_FIELD);
    }
}

