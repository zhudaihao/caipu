package gxyclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class EveryDayAdapter extends FragmentPagerAdapter {

    protected List<Fragment> list_Fragment;

    public EveryDayAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);

        this.list_Fragment = list;
    }


    //指示器
    private final String[] titles = {"早餐", "午餐","晚餐","下午茶","宵夜"};

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