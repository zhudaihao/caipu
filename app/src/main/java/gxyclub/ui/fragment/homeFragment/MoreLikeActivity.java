package gxyclub.ui.fragment.homeFragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import base.BaseRefreshActivity;
import gxyclub.adapter.MoreLikeAdapter;
import gxyclub.bean.MoreLikeBean;

/**
 * 公告（点赞）
 */

public class MoreLikeActivity extends BaseRefreshActivity implements BaseQuickAdapter.OnItemClickListener {
    private MoreLikeAdapter moreAdapter;
    private List<MoreLikeBean> mList = new ArrayList<>();

    //设置适配器
    protected void setBaseRefreshAdapter() {
        MoreLikeBean cookBookBean1 = new MoreLikeBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        MoreLikeBean cookBookBean2 = new MoreLikeBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        MoreLikeBean cookBookBean3 = new MoreLikeBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");


        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        moreAdapter = new MoreLikeAdapter(mList, this);
        //false动画重复
        moreAdapter.isFirstOnly(false);
        //设置那种动画
        moreAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(moreAdapter);

        moreAdapter.setOnItemClickListener(this);


    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("点赞");
    }

    //网络请求
    protected void setBaseLoadData() {
//        getNetClient().getNotice(pageNum);
    }

    //处理获取后台数据逻辑处理
    protected void setBaseSuccessfulDate(String requestWhat, Object data) {
//        List<ArticleList> articleLists = JSON.parseArray(((JSONObject) data).get("viewList").toString(), ArticleList.class);
//        mList.addAll(articleLists);
//
//        if (mList.size() == 0) {
//            llLayout.setVisibility(View.VISIBLE);
//        } else {
//            llLayout.setVisibility(View.GONE);
//
//            if (isAdd) {
//                //上拉加载
//                moreAdapter.setList(mList);
//                if (articleLists.size() == 0) {
//                    showToast("没有更多数据");
//                }
//            } else {
//                //下拉刷新
//                mList.clear();
//                mList.addAll(articleLists);
//                //下拉刷新调用适配器的setNewData方法
//                moreAdapter.setNewData(mList);
//            }
//
//
//        }

    }


    /**
     * 点击item
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


    }


}
