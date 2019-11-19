package gxyclub.ui.activity.cookBookActivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.gxyclub.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import gxyclub.adapter.CookBookDetailsAdapter;
import gxyclub.adapter.CookBookIngredientAdapter;
import gxyclub.adapter.CookBookLeaveAdapter;
import gxyclub.adapter.CookBookProductionAdapter;
import gxyclub.bean.CookBookDetailsBean;
import gxyclub.bean.CookBookIngredientBean;
import gxyclub.bean.CookBookLeaveBean;
import gxyclub.bean.CookBookProductionBean;
import gxyclub.view.RatingBar;

/**
 * 菜谱详情
 */
public class CookBookDetailsActivity extends BaseActivity {
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
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_inform)
    ImageView ivInform;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    @BindView(R.id.rl_layout)
    AutoRelativeLayout rlLayout;

    @Override
    public int getResLayout() {
        return R.layout.activity_cookbook_details;
    }


    /**
     * 初始化控件
     */
    private View headView, inflate;
    private LinearLayoutManager linearLayoutManager;
    //尾部控件的适配器 和 数据集合
    private CookBookDetailsAdapter homeAdapter;
    private List<CookBookDetailsBean> mList = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        rl_base_title.setVisibility(View.GONE);

        //假数据
        CookBookDetailsBean cookBookBean1 = new CookBookDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        CookBookDetailsBean cookBookBean2 = new CookBookDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        CookBookDetailsBean cookBookBean3 = new CookBookDetailsBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");

        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        homeAdapter = new CookBookDetailsAdapter(mList, this);
        //设置那种动画
        homeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setHasFixedSize(true);//item高度固定，可以优化界面

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //头部布局
        headView = View.inflate(this, R.layout.header_cookbookdetails, null);
        initHeadView(headView);

        //尾部布局
        inflate = View.inflate(this, R.layout.footer_cookbookdetails, null);
        initFooterView(inflate);

        homeAdapter.addHeaderView(headView);
        homeAdapter.addFooterView(inflate);

        recyclerView.setAdapter(homeAdapter);

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
    private RecyclerView rv_ingredient;
    private CookBookIngredientAdapter cookBookIngredientAdapter;

    private List<CookBookIngredientBean> cookBookIngredientBeanList = new ArrayList<>();


    private TextView tvEye;
    private RatingBar star;

    private void initHeadView(View headView) {
        rv_ingredient = (RecyclerView) headView.findViewById(R.id.rv_ingredient);
        rv_ingredient.setHasFixedSize(true);
        rv_ingredient.setLayoutManager(new LinearLayoutManager(this));

        tvEye = (TextView) headView.findViewById(R.id.tv_eye);
        star = (RatingBar) headView.findViewById(R.id.star);

        CookBookIngredientBean bean = new CookBookIngredientBean("", "辣椒");
        CookBookIngredientBean bean2 = new CookBookIngredientBean("", "辣椒");
        CookBookIngredientBean bean3 = new CookBookIngredientBean("", "辣椒");
        cookBookIngredientBeanList.add(bean);
        cookBookIngredientBeanList.add(bean2);
        cookBookIngredientBeanList.add(bean3);

        cookBookIngredientAdapter = new CookBookIngredientAdapter(cookBookIngredientBeanList);
        rv_ingredient.setAdapter(cookBookIngredientAdapter);


    }

    /**
     * 尾部布局
     */
    private RecyclerView rv_production, rv_leave;
    private CookBookProductionAdapter cookBookProductionAdapter;
    private List<CookBookProductionBean> cookBookProductionList = new ArrayList<>();

    private CookBookLeaveAdapter cookBookLeaveAdapter;
    private List<CookBookLeaveBean> cookBookLeaveBeanList = new ArrayList<>();

    private TextView tv_production_num, tv_leave_num;

    private void initFooterView(View headView) {
        //作品
        rv_production = (RecyclerView) headView.findViewById(R.id.rv_production);

        tv_production_num = (TextView) headView.findViewById(R.id.tv_production_num);
        tv_leave_num = (TextView) headView.findViewById(R.id.tv_leave_num);

        rv_production.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CookBookDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_production.setLayoutManager(linearLayoutManager);


        //假数据
        CookBookProductionBean cookBookBean1 = new CookBookProductionBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        CookBookProductionBean cookBookBean2 = new CookBookProductionBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        CookBookProductionBean cookBookBean3 = new CookBookProductionBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");
        cookBookProductionList.add(cookBookBean1);
        cookBookProductionList.add(cookBookBean2);
        cookBookProductionList.add(cookBookBean3);
        cookBookProductionAdapter = new CookBookProductionAdapter(cookBookProductionList, this);

        rv_production.setAdapter(cookBookProductionAdapter);

        //留言
        rv_leave = (RecyclerView) headView.findViewById(R.id.rv_leave);
        rv_leave.setHasFixedSize(true);

        rv_leave.setLayoutManager(new LinearLayoutManager(this));
        //假数据
        CookBookLeaveBean cookBookBean4 = new CookBookLeaveBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        CookBookLeaveBean cookBookBean5 = new CookBookLeaveBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        CookBookLeaveBean cookBookBean6 = new CookBookLeaveBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");
        cookBookLeaveBeanList.add(cookBookBean4);
        cookBookLeaveBeanList.add(cookBookBean5);
        cookBookLeaveBeanList.add(cookBookBean6);
        cookBookLeaveAdapter = new CookBookLeaveAdapter(cookBookLeaveBeanList, this);

        rv_leave.setAdapter(cookBookLeaveAdapter);

        //点击监听
        //作品
        tv_production_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        //留言
        tv_leave_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToActivity(new Intent(CookBookDetailsActivity.this, LeaveActivity.class), false);
            }
        });
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

        } else if (y > 0 && y <= 300) {
            //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / 300;
            float alpha = (255 * scale);
            viewLayout.setBackgroundColor(Color.argb((int) alpha, 249, 190, 24));
        } else {    //滑动到banner下面设置普通颜色
            //16进制#F9BE18转换aRGB a为透明度（不断修改透明度） 249,190,24
            viewLayout.setBackgroundColor(Color.argb((int) 255, 249, 190, 24));
        }
    }


    @OnClick({R.id.rb_collect, R.id.rb_works, R.id.rb_share, R.id.iv_back, R.id.iv_inform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finishToActivity();
                break;
            //收藏
            case R.id.rb_collect:
                break;
            //作品上传
            case R.id.rb_works:
                Intent intent = new Intent(CookBookDetailsActivity.this, WorksActivity.class);
                startTopActivity(intent);
                break;
            //分享
            case R.id.rb_share:
                share("https://www.baidu.com/");
                break;
            //举报
            case R.id.iv_inform:

                break;

        }
    }


    /**
     * 分享 网址
     */
    private void share(final String uri) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        /**
         * 真正分享出去的内容实际上是由下面的这些参数决定的，根据平台不同分别配置
         */
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                paramsToShare.setText("分享内容");
                paramsToShare.setTitle("分享标题");

                if (Wechat.NAME.equals(platform.getName()) || SinaWeibo.NAME.equals(platform.getName())) {
                    //微信  //新浪
                    paramsToShare.setUrl(uri);
                }

                if (QQ.NAME.equals(platform.getName())) {
                    //QQ
                    paramsToShare.setTitleUrl(uri);

                }
            }
        });


        //回调监听
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //成功
                showToast("分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                //失败
                showToast("分享失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                //取消
                showToast("取消分享");
            }
        });

        // 启动分享GUI
        oks.show(this);
    }
}
