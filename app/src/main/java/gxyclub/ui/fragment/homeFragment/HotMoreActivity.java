package gxyclub.ui.fragment.homeFragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.gxyclub.R;
import gxyclub.adapter.HomeHotMoreAdapter;
import gxyclub.bean.HomeHotMoreBean;

/**
 * Created by Administrator on 2018/10/23.
 */

public class HotMoreActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.ll_layout)
    AutoLinearLayout llLayout;
    @BindView(R.id.bga_layout)
    BGARefreshLayout bgaLayout;
    Unbinder unbinder;

    @Override
    public int getResLayout() {
        return R.layout.frafment_hot_more;

    }

    private List<HomeHotMoreBean> hotBeanList = new ArrayList<>();
    private HomeHotMoreAdapter homeHotAdapter;
    private BGANormalRefreshViewHolder viewHolder;

    @Override
    protected void initView() {
        super.initView();
        rl_base_title.setVisibility(View.GONE);

        //假数据
        HomeHotMoreBean cookBookBean1 = new HomeHotMoreBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg", "测试1");
        HomeHotMoreBean cookBookBean2 = new HomeHotMoreBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg", "测试2");
        HomeHotMoreBean cookBookBean3 = new HomeHotMoreBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg", "测试3");

        hotBeanList.add(cookBookBean1);
        hotBeanList.add(cookBookBean2);
        hotBeanList.add(cookBookBean3);

        hotBeanList.add(cookBookBean1);
        hotBeanList.add(cookBookBean2);
        hotBeanList.add(cookBookBean3);
        hotBeanList.add(cookBookBean1);
        hotBeanList.add(cookBookBean2);
        hotBeanList.add(cookBookBean3);


        //刷新加载
        viewHolder = new BGANormalRefreshViewHolder(this, true);
        bgaLayout.setDelegate(this);
        bgaLayout.setRefreshViewHolder(viewHolder);

        homeHotAdapter = new HomeHotMoreAdapter(hotBeanList, this);
        homeHotAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(homeHotAdapter);

        homeHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startToActivity(new Intent(HotMoreActivity.this, HotDetailsActivity.class), false);
            }
        });
    }

    @OnClick({R.id.rb_back, R.id.tv_state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_back:
                finishToActivity();
                break;
            case R.id.tv_state:
                break;
        }
    }


    //下拉刷新
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        bgaLayout.endRefreshing();

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();

    }

    private Handler handler = new Handler();

    //上拉加载
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 500);
        return true;
    }
}
