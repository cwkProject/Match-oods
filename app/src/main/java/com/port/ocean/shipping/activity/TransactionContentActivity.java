package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/7/1.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.Transaction;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.work.Payment;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;

/**
 * 交易详情Activity
 *
 * @author 超悟空
 * @version 1.0 2015/7/1
 * @since 1.0
 */
public class TransactionContentActivity extends AppCompatActivity {

    /**
     * 交易信息内容对象
     */
    private Transaction transaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_content);

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
        setTitle(R.string.title_transaction_content);
        // 提取数据
        loadData();
        // 显示数据
        fillData();
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
     * 提取相关数据
     */
    private void loadData() {
        Intent intent = getIntent();
        transaction = (Transaction) intent.getSerializableExtra(StaticValue.IntentTag
                .TRANSACTION_CONTENT_TAG);
    }

    /**
     * 填充数据
     */
    private void fillData() {
        if (transaction == null) {
            return;
        }

        TextView startTextView = (TextView) findViewById(R.id
                .activity_transaction_content_start_textView);
        TextView endTextView = (TextView) findViewById(R.id
                .activity_transaction_content_end_textView);
        TextView feeTextView = (TextView) findViewById(R.id
                .activity_transaction_content_fee_textView);
        TextView debitTextView = (TextView) findViewById(R.id
                .activity_transaction_content_debit_textView);
        TextView goodsTextView = (TextView) findViewById(R.id
                .activity_transaction_content_goods_textView);
        TextView timeTextView = (TextView) findViewById(R.id
                .activity_transaction_content_time_textView);
        TextView transactionIdTextView = (TextView) findViewById(R.id
                .activity_transaction_content_ID_textView);
        TextView stateTextView = (TextView) findViewById(R.id
                .activity_transaction_content_state_textView);
        LinearLayout stateLayout = (LinearLayout) findViewById(R.id
                .activity_transaction_content_state_layout);

        startTextView.setText(transaction.getStart());
        endTextView.setText(transaction.getEnd());
        feeTextView.setText(transaction.getAmount());
        debitTextView.setText(transaction.getDebitName());
        goodsTextView.setText(transaction.getGoods());
        timeTextView.setText(transaction.getCreateTime());
        transactionIdTextView.setText(transaction.getOrderNum());
        stateTextView.setText("1".equals(transaction.getDealState()) ? R.string
                .transaction_success : R.string.transaction_await);


        if ("0".equals(transaction.getDealState())) {
            stateLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPaymentDialog();
                }
            });
        }

        if ("1".equals(transaction.getDealState())) {
            stateTextView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.right_icon, 0, 0, 0);
        }
    }

    /**
     * 执行支付
     */
    private void doPayment() {
        Payment payment = new Payment();
        payment.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {

                Toast.makeText(TransactionContentActivity.this, data, Toast.LENGTH_SHORT).show();

                if (state) {
                    onBackPressed();
                }
            }
        });

        payment.beginExecute(GlobalApplication.getLoginStatus().getUserID(), transaction
                .getGoodsId());
    }

    /**
     * 显示支付确认对话框
     */
    private void showPaymentDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(R.string.payment_alert_title);

        dialog.setMessage(getString(R.string.payment_alert_message) + transaction.getAmount());

        dialog.setPositiveButton(R.string.payment_alert_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doPayment();
            }
        });

        dialog.setNegativeButton(R.string.payment_alert_cancel, null);

        dialog.show();
    }
}
