package gxyclub.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.gxyclub.R;
import gxyclub.adapter.ViewPagerAdapter;

/**
 * 引导页 分装工具
 */

public class ViewpagerUtils {

    private ViewpagerUtils() {}

    private static ViewpagerUtils viewpagerUtils = new ViewpagerUtils();

    public static ViewpagerUtils getInstance() {

        return viewpagerUtils;
    }

    //定义个变量实现没有被选中状态(注意赋值必须为正数)
    private int index = 0;

    public void setViewpager(Context contexts, int[] viewpagerImage, final int imageMax, ViewPager viewPager, final LinearLayout llProgress, final Button bt_start) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(contexts, viewpagerImage);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        //画圆形进度
        drawableOver(contexts, llProgress, 0, imageMax);
        //监听 设置圆形进度
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //监听viewpager切换时；进行逻辑处理
                //设置没有被选中的点
                llProgress.getChildAt(index % imageMax).setSelected(false);
                //设置选中的点
                llProgress.getChildAt(position % imageMax).setSelected(true);


                //显示体验按钮
                if (position == (imageMax - 1)) {
                    bt_start.setVisibility(View.VISIBLE);
                } else {
                    bt_start.setVisibility(View.GONE);
                }

                //把当前选中的点赋值给没有选中的变量；当下个被选中当前就要隐藏
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //在布局画圆
    private void drawableOver(Context context, LinearLayout linearLayout, int tag, int imageMax) {
        for (int i = 0; i < imageMax; i++) {
            View view = new View(context);
            view.setBackgroundResource(R.drawable.welcome_over_shape);
            //设置哪个默认选择
            if (tag == i) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
            //设置圆大小(注意装圆的是什么布局就用什么布局设置宽高)
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            //设置圆之间的距离
            params.setMargins(10, 10, 10, 10);
            //把大小和间距设置到组件
            view.setLayoutParams(params);
            //把组件放到布局里面
            linearLayout.addView(view);
        }
    }

}
