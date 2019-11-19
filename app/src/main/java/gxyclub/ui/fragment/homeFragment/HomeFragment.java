package gxyclub.ui.fragment.homeFragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yolanda.nohttp.rest.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import baseBean.ResponsePagesEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.gxyclub.R;
import cn.zdh.bannerlibrary.BannerLayout;
import cn.zdh.viewpagertextlibrary.ViewPagerTextUtils;
import gxyclub.adapter.HomeAdapter;
import gxyclub.adapter.HomeCookBookAdapter;
import gxyclub.adapter.HomeEveryDayAdapter;
import gxyclub.adapter.HomeHealthAdapter;
import gxyclub.adapter.HomeHotAdapter;
import gxyclub.bean.HomeBean;
import gxyclub.bean.HomeCookBookBean;
import gxyclub.bean.HomeEveryDayBean;
import gxyclub.bean.HomeHotBean;
import gxyclub.ui.activity.cookBookActivity.CookBookActivity;
import gxyclub.ui.activity.cookBookActivity.CookBookDetailsActivity;
import gxyclub.ui.activity.everyDayActivity.EveryDayActivity;
import gxyclub.ui.activity.healthActivity.HealthActivity;
import gxyclub.ui.activity.healthActivity.HealthDetailsActivity;
import utils.ImageUtils;
import view.BGANormalRefreshViewHolder;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bga_layout)
    BGARefreshLayout bgaLayout;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;
    @BindView(R.id.tv_state)
    TextView tvLoad;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;

    Unbinder unbinder;
    private View cookBook;
    private View health;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    //尾部控件的适配器 和 数据集合
    private HomeAdapter homeAdapter;
    private List<HomeBean> mList = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();//初始化控件
        loadData();//请求接口
        setListener();//点击监听
    }

    /**
     * 初始化控件
     */
    private View headView;
    private LinearLayoutManager linearLayoutManager;

    private void initView() {
        //假数据
        HomeBean cookBookBean1 = new HomeBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        HomeBean cookBookBean2 = new HomeBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        HomeBean cookBookBean3 = new HomeBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");

        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        homeAdapter = new HomeAdapter(mList, activity);
        //设置那种动画
        homeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setHasFixedSize(true);//item高度固定，可以优化界面

        linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);

        //头部布局
        headView = View.inflate(activity, R.layout.header_fragment_home, null);
        homeAdapter.addHeaderView(headView);
        initHeadView(headView);

        //点击item
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startToActivity(new Intent(activity, CookBookDetailsActivity.class), false);
            }
        });

        //监听滑动变化
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                setColor(rl_layout);

            }
        });
    }

    //改变title背景颜色
    private void setColor(RelativeLayout rl_layout) {
        //获取滑动距离，，通过布局管理器
        //1.获得视图的第一条木的下标
        //2.根据下标获得view条目,,,在获得条目的高度
        //3.下标*条目高度-可见视图距离顶部的高度
        int position = linearLayoutManager.findFirstVisibleItemPosition();
        View view = linearLayoutManager.findViewByPosition(position);
        int itemHeight = view.getHeight();
        int y = (position) * itemHeight - view.getTop();

        if (rl_layout == null) {
            showToast("空对象");
            return;
        }

        if (y <= 0) {   //设置标题的背景颜色
            rl_layout.setBackgroundColor(Color.argb((int) 0, 249, 190, 24));
        } else if (y > 0 && y <= 300) {
            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / 300;
            float alpha = (255 * scale);
            rl_layout.setBackgroundColor(Color.argb((int) alpha, 249, 190, 24));
        } else {    //滑动到banner下面设置普通颜色
            //16进制#F9BE18转换aRGB a为透明度（不断修改透明度） 249,190,24
            rl_layout.setBackgroundColor(Color.argb((int) 255, 249, 190, 24));
        }
    }

    /**
     * 点击监听
     * 点击按钮刷新
     */
    private void setListener() {
        tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginRefreshing();
            }
        });

    }


    /**
     * 添加头部控件
     */
    private void initHeadView(View headView) {
        //banner图
        setBanner(headView);
        //菜谱精选
        setCookBook(headView);
        //每日三餐
        setEveryDay(headView);
        //养生专辑
        setHealth(headView);
        //热门食材
        setHot(headView);
        //点击更多
        setMoreListener();
    }

    //获取头部控件
    private Banner mBanner;

    private void setBanner(View headView) {
        mBanner = (Banner) headView.findViewById(R.id.mBanner);

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setDelayTime(2000);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);

        //点击轮播图监听
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                if (bannerList != null) {
//                    String linkUrl = bannerList.get(position).getLinkUrl();
//                    if (Util.isEmpty(linkUrl) || linkUrl.equals("http://")) {
//                        return;
//                    } else {
//                        Intent intent = new Intent(activity, WebViewActivity.class);
//                        intent.putExtra("url", linkUrl);
//                        startActivity(intent);
//                        activity.overridePendingTransition(R.anim.anim_left, 0);
//                    }
//                }

            }
        });


    }

    private RecyclerView rv_cookBook, rv_everDay, rv_hot;
    private TextView tv_cookbook_more, tv_everyDay_more, tv_health_more, tv_hot_more, tv_everyDay;
    private List<HomeCookBookBean> cookBookBeanList = new ArrayList<>();
    private HomeCookBookAdapter homeCookBookAdapter;

    /**
     * 菜谱精选
     */
    private void setCookBook(View headView) {
        rv_cookBook = (RecyclerView) headView.findViewById(R.id.rv_handpick);
        tv_cookbook_more = (TextView) headView.findViewById(R.id.tv_cookbook_more);

        //假数据
        HomeCookBookBean cookBookBean1 = new HomeCookBookBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg", "测试1");
        HomeCookBookBean cookBookBean2 = new HomeCookBookBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg", "测试2");
        HomeCookBookBean cookBookBean3 = new HomeCookBookBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg", "测试3");

        cookBookBeanList.add(cookBookBean1);
        cookBookBeanList.add(cookBookBean2);
        cookBookBeanList.add(cookBookBean3);
        cookBookBeanList.add(cookBookBean1);
        cookBookBeanList.add(cookBookBean2);
        cookBookBeanList.add(cookBookBean3);

        homeCookBookAdapter = new HomeCookBookAdapter(cookBookBeanList, activity);
        homeCookBookAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        rv_cookBook.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_cookBook.setLayoutManager(linearLayoutManager);

        rv_cookBook.setAdapter(homeCookBookAdapter);

        //点击item监听
        homeCookBookAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startToActivity(new Intent(activity, CookBookDetailsActivity.class), false);

            }
        });

    }

    private List<HomeEveryDayBean> everyDayBeanList = new ArrayList<>();

    /**
     * 每日三餐
     */
    private ViewPager vp_everDay;

    private void setEveryDay(View headView) {
        vp_everDay = (ViewPager) headView.findViewById(R.id.vp_everDay);
        tv_everyDay_more = (TextView) headView.findViewById(R.id.tv_everyDay_more);
        tv_everyDay = (TextView) headView.findViewById(R.id.tv_everyDay);
        //假数据
        HomeEveryDayBean cookBookBean1 = new HomeEveryDayBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg", "测试1");
        HomeEveryDayBean cookBookBean2 = new HomeEveryDayBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg", "测试2");
        HomeEveryDayBean cookBookBean3 = new HomeEveryDayBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg", "测试3");

        everyDayBeanList.add(cookBookBean1);
        everyDayBeanList.add(cookBookBean2);
        everyDayBeanList.add(cookBookBean3);


        HomeEveryDayAdapter homeEveryDayAdapter = new HomeEveryDayAdapter(everyDayBeanList, activity);
        vp_everDay.setAdapter(homeEveryDayAdapter);
        vp_everDay.setCurrentItem(0);
        vp_everDay.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //修改三餐
                switch (position) {
                    case 0:
                        tv_everyDay.setText("每日三餐.早餐");
                        break;
                    case 1:
                        tv_everyDay.setText("每日三餐.午餐");
                        break;
                    case 2:
                        tv_everyDay.setText("每日三餐.晚餐");
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //点击item
        homeEveryDayAdapter.setOnClick(new HomeEveryDayAdapter.OnClick() {
            @Override
            public void click(int position) {
                showToast(position + "");
                startToActivity(new Intent(activity, CookBookDetailsActivity.class), false);
            }
        });

    }

    /**
     * 养生专辑
     */
    public void setHealth(View headView) {
        tv_health_more = (TextView) headView.findViewById(R.id.tv_health_more);

        BannerLayout bannerLayout = (BannerLayout) headView.findViewById(R.id.bannerLayout);
        bannerLayout.setItemSpace(30);//调整图片间距离
        bannerLayout.setShowIndicator(false);
        bannerLayout.setAutoPlaying(false);

        List<String> healthList = new ArrayList<>();
        healthList.add("http://img0.imgtn.bdimg.com/it/u=1352823040,1166166164&fm=27&gp=0.jpg");
        healthList.add("http://img3.imgtn.bdimg.com/it/u=2293177440,3125900197&fm=27&gp=0.jpg");
        healthList.add("http://img3.imgtn.bdimg.com/it/u=3967183915,4078698000&fm=27&gp=0.jpg");
        healthList.add("http://img0.imgtn.bdimg.com/it/u=3184221534,2238244948&fm=27&gp=0.jpg");
        healthList.add("http://img4.imgtn.bdimg.com/it/u=1794621527,1964098559&fm=27&gp=0.jpg");
        healthList.add("http://img4.imgtn.bdimg.com/it/u=1243617734,335916716&fm=27&gp=0.jpg");
        HomeHealthAdapter healthAdapter = new HomeHealthAdapter(activity, healthList);
        bannerLayout.setAdapter(healthAdapter);

        //点击item
        healthAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(activity, HealthDetailsActivity.class);
                startToActivity(intent, false);
            }
        });

    }

    private List<HomeHotBean> hotBeanList = new ArrayList<>();
    private HomeHotAdapter homeHotAdapter;

    /**
     * 热门食材
     */
    public void setHot(View headView) {
        rv_hot = (RecyclerView) headView.findViewById(R.id.rv_hot);
        tv_hot_more = (TextView) headView.findViewById(R.id.tv_hot_more);

        //假数据
        HomeHotBean cookBookBean1 = new HomeHotBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg", "测试1");
        HomeHotBean cookBookBean2 = new HomeHotBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg", "测试2");
        HomeHotBean cookBookBean3 = new HomeHotBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg", "测试3");

        hotBeanList.add(cookBookBean1);
        hotBeanList.add(cookBookBean2);
        hotBeanList.add(cookBookBean3);

        homeHotAdapter = new HomeHotAdapter(hotBeanList, activity);
        homeHotAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        rv_hot.setHasFixedSize(true);
        rv_hot.setLayoutManager(new LinearLayoutManager(activity));
        rv_hot.setAdapter(homeHotAdapter);

        //点击item
        homeHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startToActivity(new Intent(activity,HotDetailsActivity.class),false);

            }
        });


    }

    /**
     * 点击更多
     */
    private void setMoreListener() {
        //菜谱精选  点击更多
        tv_cookbook_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToActivity(new Intent(activity, CookBookActivity.class), false);
            }
        });

        //每日三餐  点击更多
        tv_everyDay_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EveryDayActivity.class);
                startToActivity(intent, false);
            }
        });

        //养生专辑 点击
        tv_health_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, HealthActivity.class);
                startToActivity(intent, false);
            }
        });

        //热门食材
        tv_hot_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, HotMoreActivity.class);
                startToActivity(intent, false);
            }
        });




    }


    /**
     * 请求网络接口
     */
    private void loadData() {
//        getNetClient().home("");
    }

    /**
     * 请求网络接口回调
     */
    private HomeBean homeBean;

    /**
     * 网络接口成功
     */
    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
        //显示title
        setVisible();
        //关闭刷新
        setEndRefreshing();
//        homeBean = JSON.parseObject(data.toString(), HomeBean.class);
//        //设置数
//        setBannerData(homeBean);//轮播图数据

        // setListData();//底部列表数据

    }

    private void setVisible() {
        rl_layout.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rl_layout, "alpha", 0, 1);
        objectAnimator.setDuration(1000);
        objectAnimator.start();

    }

    //失败
    @Override
    public void onFailed(int what, Response<String> response) {
        super.onFailed(what, response);
        //显示title
        setVisible();
        //关闭刷新
        setEndRefreshing();
        llLayout.setVisibility(View.VISIBLE);
        headView.setVisibility(View.INVISIBLE);

    }

    //错误
    @Override
    public void onFailure(String requestWhat, Object data) {
        super.onFailure(requestWhat, data);
        //显示title
        setVisible();
        //关闭刷新
        setEndRefreshing();
        llLayout.setVisibility(View.VISIBLE);
        headView.setVisibility(View.INVISIBLE);

    }


    //点击监听
    @OnClick({R.id.iv_class, R.id.tv_search, R.id.iv_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_class:

                break;
            //搜索
            case R.id.tv_search:
                Intent intent = new Intent(activity, SearchActivity.class);
                startToActivity(intent, false);
                break;
            //发布
            case R.id.iv_news:
                startToActivity(activity, MoreActivity.class, false);
                break;
        }
    }


    /**
     * 轮播加载图片框架
     */

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            ImageUtils.instance().loadImage(context, path, imageView, R.mipmap.banner, R.mipmap.banner);

        }

    }

    //轮播
    private void setBannerData(HomeBean homeBean) {

//        List<HomeBean> bannerList = homeBean.getBannerList();
//        //轮播加载图片
//        ArrayList<String> mUrl = new ArrayList<>();
//        for (int i = 0; i < bannerList.size(); i++) {
//            mUrl.add(bannerList.get(i).getFileUrl());
//        }
//
//        mBanner.setImages(mUrl);//设置数据
//        //启动
//        mBanner.start();

    }

    @Override
    public void onStop() {
        super.onStop();
        //轮播图停止轮播
        mBanner.stopAutoPlay();

    }


    /**
     * 底部数据
     */
    private void setListData() {
//        List<HomeBean> borrowsBeanList = homeBean();
//        if (isAdd) {
//            //上拉加载
//            homeAdapter.setList(mList);
//            if (borrowsBeanList.size() == 0) {
//                showToast("没有更多数据");
//            }
//        } else {
//            //下拉刷新
//            mList.clear();
//            mList.addAll(borrowsBeanList);
//            //下拉刷新调用适配器的setNewData方法
//            homeAdapter.setNewData(mList);
//
//            if (mList.size() == 0) {
//                llLayout.setVisibility(View.VISIBLE);
//            } else {
//                llLayout.setVisibility(View.GONE);
//            }
//        }
    }


    /**
     * 初始化 下拉刷新控件
     */
    protected BGANormalRefreshViewHolder viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        //使用BGA下拉刷新fragment需在onCreateView初始化
        bgaLayout.setDelegate(this);
        viewHolder = new BGANormalRefreshViewHolder(activity, false);//设置为true可以上拉加载
        bgaLayout.setRefreshViewHolder(viewHolder);

        //startChangeWholeHeaderViewPaddingTop  addUpdateListener
        return rootView;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        ViewPagerTextUtils.getInstance().onDestroy();
        super.onDestroy();
    }

    // 自动进入正在刷新状态获取最新数据（下拉刷新）
    public void beginRefreshing() {
        bgaLayout.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态(上拉加载)
    public void beginLoadingMore() {
        bgaLayout.beginLoadingMore();
    }


    //关闭刷新动画
    private void setEndRefreshing() {
        bgaLayout.endRefreshing();
    }


    public static Handler handler = new Handler();
    //上拉加载标记
    protected boolean isAdd = false;

    //下拉刷新
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //隐藏title
        setInVisible();
        //使用handler设置最少加载时间
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //重新请求网络数据
                isAdd = false;
                loadData();

            }

        }, 500);

    }

    private void setInVisible() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rl_layout, "alpha", 1, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        rl_layout.setVisibility(View.INVISIBLE);
    }

    //上拉加载
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        //使用handler设置最少加载时间
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //重新请求网络数据
//                isAdd = true;
//                loadData();
//
//            }
//
//        }, 500);
//        return true;
        return false;
    }


}
