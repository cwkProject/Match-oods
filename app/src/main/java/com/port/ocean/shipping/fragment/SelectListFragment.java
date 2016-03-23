package com.port.ocean.shipping.fragment;
/**
 * Created by 超悟空 on 2015/7/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.port.ocean.shipping.R;

/**
 * 选择列表片段
 *
 * @author 超悟空
 * @version 1.0 2015/7/15
 * @since 1.0
 */
public class SelectListFragment extends Fragment {

    /**
     * 选择列表布局
     */
    private ListView listView = null;

    /**
     * 获取选择片段保存的列表对象，
     * 用于配置数据源和响应事件
     *
     * @return 列表对象
     */
    public ListView getListView() {
        return listView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // 根布局
        View rootView = inflater.inflate(R.layout.fragment_select_list, container, false);

        // 列表布局
        listView = (ListView) rootView.findViewById(R.id.fragment_select_list_listView);

        return rootView;
    }

}
