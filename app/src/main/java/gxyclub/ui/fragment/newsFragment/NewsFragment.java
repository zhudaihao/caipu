package gxyclub.ui.fragment.newsFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yolanda.nohttp.rest.Response;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.gxyclub.R;
import gxyclub.adapter.NewsAdapter;
import gxyclub.bean.NewsBean;
import gxyclub.ui.activity.MainActivity;
import view.BGANormalRefreshViewHolder;


/**
 * 趣闻
 */

public class NewsFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bga_layout)
    BGARefreshLayout bgaLayout;

    @BindView(R.id.tv_state)
    TextView tvLoad;
    @BindView(R.id.ll_layout)
    AutoLinearLayout llLayout;

    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadData();
    }

    private List<NewsBean> mList = new ArrayList<>();
    private NewsAdapter newAdapter;

    private void initView() {
        tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginRefreshing();
            }
        });

        recyclerView.setHasFixedSize(true);//item高度固定，可以优化界面
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        MainActivity activity = (MainActivity) getActivity();

        //假数据
        NewsBean cookBookBean1 = new NewsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        NewsBean cookBookBean2 = new NewsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        NewsBean cookBookBean3 = new NewsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");

        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);
        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);
        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);


        newAdapter = new NewsAdapter(mList, NewsFragment.this, activity);


        //设置那种动画
        newAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(newAdapter);
        //监听item
        newAdapter.setOnItemClickListener(this);


    }



    //初始化 下拉刷新控件
    protected BGANormalRefreshViewHolder viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        //使用BGA下拉刷新fragment需在onCreateView初始化
        bgaLayout.setDelegate(this);
        viewHolder = new BGANormalRefreshViewHolder(activity, true);//设置为true可以上拉加载
        bgaLayout.setRefreshViewHolder(viewHolder);
        return rootView;
    }

    protected int pageNum = 1;

    private void loadData() {
        getNetClient().home("");

    }

    // 通过代码方式控制进入正在刷新状态。应用场景：某些应用在 activity 的 onStart 等方法中调用，
    // 自动进入正在刷新状态获取最新数据（下拉刷新）
    public void beginRefreshing() {
        bgaLayout.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态(上拉加载)
    public void beginLoadingMore() {
        bgaLayout.beginLoadingMore();
    }

    @Override
    public void onSuccessful(String requestWhat, Object data, baseBean.ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
        closeLoading();

//        MatchBean matchBean = JSON.parseObject(data.toString(), MatchBean.class);
//        List<MatchBean.ListBean> listBeanList = matchBean.getList();
//
//        mList.addAll(listBeanList);
//
//        if (mList.size() == 0) {
//            llLayout.setVisibility(View.VISIBLE);
//        } else {
//            llLayout.setVisibility(View.GONE);
//
//            if (isAdd) {
//                //上拉加载
//                newAdapter.setList(mList);
//                if (listBeanList.size() == 0) {
//                    showToast("没有更多数据");
//                }
//            } else {
//                //下拉刷新
//                mList.clear();
//                mList.addAll(listBeanList);
//                //下拉刷新调用适配器的setNewData方法
//                newAdapter.setNewData(mList);
//            }
//
//
//        }


    }


    public void onFailed(int what, Response<String> response) {
        super.onFailed(what, response);
        //关闭刷新
        closeLoading();
        llLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onFailure(String requestWhat, Object data) {
        super.onFailure(requestWhat, data);
        //关闭刷新
        closeLoading();
        llLayout.setVisibility(View.VISIBLE);

    }

    //关闭刷新
    private void closeLoading() {
        bgaLayout.endRefreshing();
        bgaLayout.endLoadingMore();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public static Handler handler = new Handler();
    //上拉加载标记
    protected boolean isAdd = false;

    //下拉刷新
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //使用handler设置最少加载时间
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //重新请求网络数据
                pageNum = 1;
                isAdd = false;
                loadData();
            }

        }, 500);
    }


    //上拉加载
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        //使用handler设置最少加载时间

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //重新请求网络数据
                pageNum = pageNum + 1;
                isAdd = true;
                loadData();

            }

        }, 500);
        return true;
    }


    /**
     * 点击监听
     * 点击item
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


}
