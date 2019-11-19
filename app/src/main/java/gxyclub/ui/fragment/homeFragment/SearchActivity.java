package gxyclub.ui.fragment.homeFragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import gxyclub.adapter.SearchAdapter;
import gxyclub.bean.SearchBean;
import gxyclub.ui.activity.capacityActivity.CapacityMoreActivity;

/**
 * Created by Administrator on 2018/10/23.
 */

public class SearchActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
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
        return R.layout.activity_search;

    }

    private List<SearchBean> list = new ArrayList<>();
    private SearchAdapter searchAdapter, footerAdapter;
    private BGANormalRefreshViewHolder viewHolder;

    private RecyclerView rvFooter;
    @Override
    protected void initView() {
        super.initView();
        rl_base_title.setVisibility(View.GONE);

        //假数据
        SearchBean cookBookBean1 = new SearchBean("麻婆豆腐");
        SearchBean cookBookBean2 = new SearchBean("鱼腥草");
        SearchBean cookBookBean3 = new SearchBean("奶茶");

        SearchBean cookBookBean4 = new SearchBean("毛血旺");
        SearchBean cookBookBean5 = new SearchBean("麻辣香锅");
        SearchBean cookBookBean6 = new SearchBean("甜品");

        SearchBean cookBookBean7 = new SearchBean("红烧鱼");
        SearchBean cookBookBean8 = new SearchBean("梅菜扣肉");
        SearchBean cookBookBean9 = new SearchBean("酸辣土豆丝");

        list.add(cookBookBean1);
        list.add(cookBookBean2);
        list.add(cookBookBean3);

        list.add(cookBookBean4);
        list.add(cookBookBean5);
        list.add(cookBookBean6);
        list.add(cookBookBean7);
        list.add(cookBookBean8);
        list.add(cookBookBean9);


        //刷新加载
        viewHolder = new BGANormalRefreshViewHolder(this, true);
        bgaLayout.setDelegate(this);
        bgaLayout.setRefreshViewHolder(viewHolder);

        //瀑布流
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager footerManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        searchAdapter = new SearchAdapter(list, this);
        searchAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        //底部
        footerAdapter = new SearchAdapter(list, this);
        footerAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        View inflate = View.inflate(this, R.layout.footer_search, null);
        rvFooter = (RecyclerView) inflate.findViewById(R.id.rv_search);

        rvFooter.setLayoutManager(footerManager);
        rvFooter.setAdapter(footerAdapter);


        searchAdapter.addFooterView(inflate);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(searchAdapter);

        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @OnClick({R.id.rb_back, R.id.tv_state,R.id.rb_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_back:
                finishToActivity();
                break;
            case R.id.tv_state:
                break;
            case R.id.rb_search:
                startToActivity(new Intent(SearchActivity.this, CapacityMoreActivity.class), false);
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
