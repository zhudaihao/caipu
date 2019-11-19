package gxyclub.ui.activity.healthActivity;

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
import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.gxyclub.R;
import gxyclub.adapter.HealthDetailsAdapter;
import gxyclub.adapter.HeaderHealthAdapter;
import gxyclub.bean.HealthDetailsBean;

/**
 * 养生专辑详情
 */
public class HealthDetailsActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
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
    @BindView(R.id.ll_layout)
    AutoLinearLayout llLayout;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int getResLayout() {
        return R.layout.activity_health_details;
    }


    /**
     * 初始化控件
     */
    private View headView;
    private LinearLayoutManager linearLayoutManager;
    //尾部控件的适配器 和 数据集合
    private HealthDetailsAdapter healthDetailsAdapter;
    private List<HealthDetailsBean> mList = new ArrayList<>();

    private BGANormalRefreshViewHolder bgaNormalRefreshViewHolder;

    @Override
    protected void initView() {
        super.initView();
        rl_base_title.setVisibility(View.GONE);
        rl_layout.setBackgroundColor(getResources().getColor(R.color.back));
        //假数据
        HealthDetailsBean cookBookBean1 = new HealthDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        HealthDetailsBean cookBookBean2 = new HealthDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        HealthDetailsBean cookBookBean3 = new HealthDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");

        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        healthDetailsAdapter = new HealthDetailsAdapter(mList, this);
        //设置那种动画
        healthDetailsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setHasFixedSize(true);//item高度固定，可以优化界面

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //刷新
        bgaNormalRefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        bgaLayout.setRefreshViewHolder(bgaNormalRefreshViewHolder);
        bgaLayout.setDelegate(this);

        //头部布局
        headView = View.inflate(this, R.layout.header_healthdetails, null);
        initHeadView(headView);


        healthDetailsAdapter.addHeaderView(headView);


        recyclerView.setAdapter(healthDetailsAdapter);

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
    private RecyclerView rv_header;
    private HeaderHealthAdapter headerHealthAdapter;

    private void initHeadView(View headView) {
        mList.get(0).setShowOne(true);
        rv_header = (RecyclerView) headView.findViewById(R.id.rv_header);
        headerHealthAdapter = new HeaderHealthAdapter(mList, HealthDetailsActivity.this);
        rv_header.setHasFixedSize(true);
        rv_header.setLayoutManager(new LinearLayoutManager(this));
        rv_header.setAdapter(headerHealthAdapter);


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


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finishToActivity();
    }

    private Handler handler = new Handler();

    //刷新
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bgaLayout.endRefreshing();
            }
        }, 500);
    }

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
