package gxyclub.ui.fragment.homeFragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import base.BaseRefreshActivity;
import gxyclub.adapter.MoreSystemAdapter;
import gxyclub.bean.MoreSystemBean;

/**
 * 公告（系统）
 */

public class MoreSystemActivity extends BaseRefreshActivity implements BaseQuickAdapter.OnItemClickListener {
    private MoreSystemAdapter moreSystemAdapter;
    private List<MoreSystemBean> mList = new ArrayList<>();

    //设置适配器
    protected void setBaseRefreshAdapter() {
        MoreSystemBean cookBookBean1 = new MoreSystemBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg");
        MoreSystemBean cookBookBean2 = new MoreSystemBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg");
        MoreSystemBean cookBookBean3 = new MoreSystemBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg");


        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        moreSystemAdapter = new MoreSystemAdapter(mList, this);
        //false动画重复
        moreSystemAdapter.isFirstOnly(false);
        //设置那种动画
        moreSystemAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(moreSystemAdapter);

        moreSystemAdapter.setOnItemClickListener(this);


    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("系统消息");
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
//                moreSystemAdapter.setList(mList);
//                if (articleLists.size() == 0) {
//                    showToast("没有更多数据");
//                }
//            } else {
//                //下拉刷新
//                mList.clear();
//                mList.addAll(articleLists);
//                //下拉刷新调用适配器的setNewData方法
//                moreSystemAdapter.setNewData(mList);
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
