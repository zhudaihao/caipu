package gxyclub.ui.activity.cookBookActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.longsh.longshlibrary.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.Unbinder;
import cn.gxyclub.R;
import gxyclub.adapter.LeaveAdapter;
import gxyclub.ui.fragment.leaveFragment.HotFragment;
import gxyclub.ui.fragment.leaveFragment.TopFragment;

/**
 * 留言
 */

public class LeaveActivity extends BaseActivity {
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.view_line)
    View view_line;
    Unbinder unbinder;

    @Override
    public int getResLayout() {
        return R.layout.activity_everyday;
    }

    private List<Fragment> mList = new ArrayList<>();
    //热门
    private HotFragment breakFastFragment = new HotFragment();
    //头条
    private TopFragment lunchFragment = new TopFragment();

    protected LeaveAdapter everyDayAdapter;

    public void initView() {
        view_line.setVisibility(View.VISIBLE);
        tv_base_title.setText("留言");
        rb_base_right.setText("写留言");
        mList.add(breakFastFragment);
        mList.add(lunchFragment);

        //注意fragment嵌套fragment用getChildFragmentManager()管理器
        everyDayAdapter = new LeaveAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(everyDayAdapter);

        //设置指示器(注意需要放在setAdapter后面)
        tabs.setViewPager(viewPager);
        //设置参数
        setTabsValue();

        //点击写留言
        rb_base_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToActivity(new Intent(LeaveActivity.this, EditLeaveActivity.class), false);
            }
        });

    }

    //设置指示器
    private void setTabsValue() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置Tab底部选中的指示器Indicator的高度
        tabs.setIndicatorHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.0f, dm));
        // 设置Tab底部选中的指示器 Indicator的颜色
        tabs.setIndicatorColorResource(R.color.colorPrimaryDark);
        //设置指示器Indicatorin是否跟文本一样宽，默认false
        tabs.setIndicatorinFollowerTv(true);
        //设置小红点提示，item从0开始计算，true为显示，false为隐藏，默认为全部隐藏
        //    tabs.setMsgToast(2, true);
        //设置红点滑动到当前页面自动消失,默认为true
        tabs.setMsgToastPager(true);
        //设置Tab标题文字的颜色
        tabs.setTextColorResource(R.color.color_7A);
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, dm));
        // 设置选中的Tab文字的颜色
        tabs.setSelectedTextColorResource(R.color.colorPrimaryDark);
        //设置Tab底部分割线的高度
        tabs.setUnderlineHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, dm));
        //设置Tab底部分割线的颜色
        tabs.setUnderlineColorResource(R.color.colorPrimaryDark);
        // 设置点击某个Tab时的背景色,设置为0时取消背景色tabs.setTabBackground(0);
        // tabs.setTabBackground(R.drawable.bg_tab);
        tabs.setTabBackground(0);
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
    }


}
