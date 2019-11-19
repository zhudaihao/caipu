package gxyclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class CapacityMoreAdapter extends FragmentPagerAdapter {

    protected List<Fragment> list_Fragment;

    public CapacityMoreAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);

        this.list_Fragment = list;
    }


    //指示器
    private final String[] titles = {"智能推荐", "评分最多","做过最多"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public Fragment getItem(int position) {
       return list_Fragment.get(position);
    }



}
