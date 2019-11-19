package gxyclub.ui.fragment.homeFragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import baseBean.ResponsePagesEntity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.gxyclub.R;
import gxyclub.adapter.HotContentAdapter;
import gxyclub.adapter.HotDetailsAdapter;
import gxyclub.bean.HotContentBean;
import gxyclub.bean.HotDetailsBean;
import view.BGANormalRefreshViewHolder;

/**
 * 热门专辑详情
 */
public class HotDetailsActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bga_layout)
    BGARefreshLayout bgaLayout;
    @BindView(R.id.view_layout)
    View viewLayout;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_layout)
    AutoLinearLayout llLayout;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;

    @Override
    public int getResLayout() {
        return R.layout.activity_hot_details;
    }


    /**
     * 初始化控件
     */
    private View headView;
    private LinearLayoutManager linearLayoutManager;
    //尾部控件的适配器 和 数据集合
    private HotDetailsAdapter hotDetailsAdapter;
    private List<HotDetailsBean> mList = new ArrayList<>();

    protected BGANormalRefreshViewHolder viewHolder;

    @Override
    protected void initView() {
        super.initView();
        rl_base_title.setVisibility(View.GONE);
        rl_layout.setBackgroundColor(getResources().getColor(R.color.back));
        tvTitle.setText("热门详情");
        tvTitle.setVisibility(View.GONE);

        //假数据
        HotDetailsBean cookBookBean1 = new HotDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        HotDetailsBean cookBookBean2 = new HotDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        HotDetailsBean cookBookBean3 = new HotDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");

        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        hotDetailsAdapter = new HotDetailsAdapter(mList, this);


        recyclerView.setHasFixedSize(true);//item高度固定，可以优化界面
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //使用BGA下拉刷新fragment需在onCreateView初始化
        bgaLayout.setDelegate(this);
        viewHolder = new BGANormalRefreshViewHolder(this, true);//设置为true可以上拉加载
        bgaLayout.setRefreshViewHolder(viewHolder);

        //设置那种动画
        hotDetailsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setHasFixedSize(true);//item高度固定，可以优化界面

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //头部布局
        headView = View.inflate(this, R.layout.header_hot_details, null);
        initHeadView(headView);


        hotDetailsAdapter.addHeaderView(headView);


        recyclerView.setAdapter(hotDetailsAdapter);

        //监听滑动变化
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                setColor(viewLayout);

            }
        });


    }

    /**
     * 头部布局
     */
    private TextView tv_title, tv_nutritive;
    private RecyclerView recycler_content;
    private HotContentAdapter hotContentAdapter;
    private List<HotContentBean> list = new ArrayList<>();

    private void initHeadView(View headView) {
        tv_title = (TextView) headView.findViewById(R.id.tv_title);
        tv_nutritive = (TextView) headView.findViewById(R.id.tv_nutritive);
        recycler_content = (RecyclerView) headView.findViewById(R.id.recycler_content);
        //假数据
        HotContentBean hotContentBean1 = new HotContentBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1540548523307&di=7224910398dba251e7c98160d355763e&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F5243fbf2b211931376d158d568380cd790238dc1.jpg");
        HotContentBean hotContentBean2 = new HotContentBean("");
        HotContentBean hotContentBean3 = new HotContentBean("");

        list.add(hotContentBean1);
        list.add(hotContentBean2);
        list.add(hotContentBean3);

        hotContentAdapter = new HotContentAdapter(list, this);

        recycler_content.setLayoutManager(new LinearLayoutManager(this));
        recycler_content.setAdapter(hotContentAdapter);


    }


    //改变title背景颜色
    private void setColor(View viewLayout) {
        //获取滑动距离，，通过布局管理器
        //1.获得视图的第一条木的下标
        //2.根据下标获得view条目,,,在获得条目的高度
        //3.下标*条目高度-可见视图距离顶部的高度
        int position = linearLayoutManager.findFirstVisibleItemPosition();
        View view = linearLayoutManager.findViewByPosition(position);
        int itemHeight = view.getHeight();
        int y = (position) * itemHeight - view.getTop();

        if (viewLayout == null) {
            showToast("空对象");
            return;
        }


        if (y < 30) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }

        if (y <= 0) {   //设置标题的背景颜色
            viewLayout.setBackgroundColor(Color.argb((int) 0, 249, 190, 24));
        } else if (y > 0 && y <= 150) {
            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / 150;
            float alpha = (255 * scale);
            viewLayout.setBackgroundColor(Color.argb((int) alpha, 249, 190, 24));
        } else {    //滑动到banner下面设置普通颜色
            //16进制#F9BE18转换aRGB a为透明度（不断修改透明度） 249,190,24
            viewLayout.setBackgroundColor(Color.argb((int) 255, 249, 190, 24));

        }
    }

    @Override
    public void onSuccessful(String requestWhat, Object data, ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);

    }

    //网络请求
    protected int pageNum = 1;
    //上拉加载标记
    protected boolean isAdd = false;

    protected void setBaseLoadData() {
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finishToActivity();
    }

    //下拉刷新
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        bgaLayout.endRefreshing();
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
                setBaseLoadData();

            }

        }, 500);
        return true;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public static Handler handler = new Handler();
}
