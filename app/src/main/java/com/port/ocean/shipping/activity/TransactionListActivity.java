package com.port.ocean.shipping.activity;
/**
 * Created by 超悟空 on 2015/6/30.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.data.Transaction;
import com.port.ocean.shipping.util.StaticValue;
import com.port.ocean.shipping.work.PullTransaction;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易列表Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/30
 * @since 1.0
 */
public class TransactionListActivity extends AppCompatActivity {

    /**
     * 起始地标签
     */
    private static final String START_TAG = "start_tag";

    /**
     * 目的地标签
     */
    private static final String END_TAG = "end_tag";

    /**
     * 相关属性标签
     */
    private static final String ATTRIBUTE_TAG = "attribute_tag";

    /**
     * 创建时间标签
     */
    private static final String TIME_TAG = "time_tag";

    /**
     * 信息费标签
     */
    private static final String FEE_TAG = "fee_tag";

    /**
     * 交易状态标签
     */
    private static final String STATE_TAG = "state_tag";

    /**
     * 列表使用的数据适配器
     */
    private SimpleAdapter adapter = null;

    /**
     * 交易信息列表
     */
    private List<Transaction> transactionList = null;

    /**
     * 数据适配器的元数据
     */
    private List<Map<String, Object>> adapterDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

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
        setTitle(R.string.title_my_transaction);

        // 初始化列表
        initListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 初始化数据
        initData();
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
     * 初始化列表数据
     */
    private void initListView() {
        // 片段中的列表布局
        ListView listView = (ListView) findViewById(R.id.activity_transaction_list_listView);

        // 列表使用的数据适配器
        adapter = new SimpleAdapter(this, adapterDataList, R.layout.transaction_list_item, new
                String[]{START_TAG , END_TAG , ATTRIBUTE_TAG , TIME_TAG , FEE_TAG , STATE_TAG},
                new int[]{R.id.transaction_list_item_route_start_textView , R.id
                        .transaction_list_item_route_end_textView , R.id
                                  .transaction_list_item_attribute_textView , R.id
                                  .transaction_list_item_time_textView , R.id
                                  .transaction_list_item_fee_textView , R.id
                                  .transaction_list_item_state_textView});

        // 设置适配器
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goContent(position);
            }
        });
    }

    /**
     * 跳转到详情页面
     *
     * @param position 点击位置
     */
    private void goContent(int position) {

        if (transactionList != null && transactionList.size() > position) {

            Intent intent = new Intent(this, TransactionContentActivity.class);
            intent.putExtra(StaticValue.IntentTag.TRANSACTION_CONTENT_TAG, transactionList.get
                    (position));
            startActivity(intent);
        }
    }

    /**
     * 标签资源
     *
     * @param data 数据源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> getAdapterDataList(List<Transaction> data) {
        // 加载功能项
        List<Map<String, Object>> dataList = new ArrayList<>();

        for (Transaction transaction : data) {
            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加标签资源
            function.put(START_TAG, transaction.getStart());
            function.put(END_TAG, transaction.getEnd());
            function.put(ATTRIBUTE_TAG, transaction.getGoods());
            function.put(TIME_TAG, transaction.getCreateTime());
            function.put(FEE_TAG, transaction.getAmount());
            function.put(STATE_TAG, transaction.getDealState().equals("1") ? getString(R.string
                    .transaction_success) : getString(R.string.transaction_await));

            // 将标签加入数据源
            dataList.add(function);
        }
        return dataList;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (!GlobalApplication.getLoginStatus().isLogin()) {
            Toast.makeText(this, R.string.no_login, Toast.LENGTH_SHORT).show();
            return;
        }

        PullTransaction pullTransaction = new PullTransaction();

        pullTransaction.setWorkEndListener(new WorkBack<List<Transaction>>() {
            @Override
            public void doEndWork(boolean state, List<Transaction> data) {
                if (state) {
                    fillData(data);
                }
            }
        });

        pullTransaction.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }

    /**
     * 填充数据
     *
     * @param data 数据源
     */
    private void fillData(List<Transaction> data) {
        Collections.sort(data, new Transaction());

        transactionList = data;

        adapterDataList.clear();
        adapterDataList.addAll(getAdapterDataList(transactionList));
        adapter.notifyDataSetChanged();
    }

}
