package gxyclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 个人
 */

public class MePagerAdapter extends FragmentPagerAdapter {

    protected List<Fragment> list_Fragment;

    public MePagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);

        this.list_Fragment = list;
    }


    //指示器
    private final String[] titles = {"菜谱", "作品", "收藏"};

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
