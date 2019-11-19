package gxyclub.ui.fragment.meFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longsh.longshlibrary.PagerSlidingTabStrip;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import baseBean.ResponsePagesEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.gxyclub.R;
import gxyclub.adapter.MePagerAdapter;
import gxyclub.ui.activity.meActivity.EditActivity;
import gxyclub.ui.activity.setActivity.SetActivity;
import gxyclub.view.MyViewPager;


/**
 * 个人
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.tv_attention_num)
    TextView tvAttentionNum;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    MyViewPager viewPager;



    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }


    private List<Fragment> mList = new ArrayList<>();

    //菜谱
    private CookBookFragment cookBookFragment = new CookBookFragment();
    //作品
    private ProductionFragment productionFragment = new ProductionFragment();
    //收藏
    private CollectFragment collectFragment = new CollectFragment();

    protected MePagerAdapter mePagerAdapter;

    private void initView() {
        //假数据
        mList.add(cookBookFragment);
        mList.add(productionFragment);
        mList.add(collectFragment);

        //注意fragment嵌套fragment用getChildFragmentManager()管理器
        mePagerAdapter = new MePagerAdapter(getChildFragmentManager(), mList);
        viewPager.setAdapter(mePagerAdapter);

        //设置指示器(注意需要放在setAdapter后面)
        tabs.setViewPager(viewPager);
        //设置参数
        setTabsValue();

    }


    /**
     * 设置指示器
     */
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
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));
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

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    //获取用户ID
    public int getUserId() {
        int i = 0;
        String id = "";

        return id == null ? 0 : i;
    }

    /**
     * 请求接口
     */
    private void loadData() {
        int userId = getUserId();
        getNetClient().getUser(userId);
    }


    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);


    }

    @Override
    public void onFailed(int what, Response response) {
        super.onFailed(what, response);

    }

    @Override
    public void onFailure(String requestWhat, Object data) {
        super.onFailure(requestWhat, data);

    }


    @OnClick({R.id.iv_edit, R.id.iv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //编辑
            case R.id.iv_edit:
                startToActivity(activity, EditActivity.class,false);
                break;
            //设置
            case R.id.iv_set:
                startToActivity(activity, SetActivity.class,false);
                break;
        }
    }
}
