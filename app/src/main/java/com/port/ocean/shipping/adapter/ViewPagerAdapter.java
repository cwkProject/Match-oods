package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.port.ocean.shipping.R;
import com.port.ocean.shipping.fragment.ExploreFragment;
import com.port.ocean.shipping.fragment.FindGoodsFragment;
import com.port.ocean.shipping.fragment.MessageFragment;
import com.port.ocean.shipping.fragment.MineFragment;

import org.mobile.library.global.GlobalApplication;

/**
 * 主界面ViewPager适配器
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FindGoodsFragment();
            case 1:
                return new ExploreFragment();
            case 2:
                return new MessageFragment();
            case 3:
                return new MineFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return GlobalApplication.getGlobal().getString(R.string.view_pager_title_main);
            case 1:
                return GlobalApplication.getGlobal().getString(R.string.view_pager_title_message);
            case 2:
                return GlobalApplication.getGlobal().getString(R.string.view_pager_title_explore);
            case 3:
                return GlobalApplication.getGlobal().getString(R.string.view_pager_title_mine);
        }
        return null;
    }
}
