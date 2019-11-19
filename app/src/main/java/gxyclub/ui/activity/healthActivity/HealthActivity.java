package gxyclub.ui.activity.healthActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import base.BaseRefreshActivity;
import gxyclub.adapter.HealthAdapter;
import gxyclub.bean.HealthBean;

/**
 * 养生专辑 更多
 */

public class HealthActivity extends BaseRefreshActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rl_base_title.setVisibility(View.VISIBLE);
        tv_base_title.setText("菜谱");


    }

    //设置适配器
    private HealthAdapter cookBookAdapter;
    private List<HealthBean> mList = new ArrayList<>();

    protected void setBaseRefreshAdapter() {
        //假数据
        HealthBean HealthBean1 = new HealthBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg", "测试1");
        HealthBean HealthBean2 = new HealthBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg", "测试2");
        HealthBean HealthBean3 = new HealthBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg", "测试3");
        mList.add(HealthBean1);
        mList.add(HealthBean2);
        mList.add(HealthBean3);
        mList.add(HealthBean1);
        mList.add(HealthBean2);
        mList.add(HealthBean3);
        mList.add(HealthBean1);
        mList.add(HealthBean2);
        mList.add(HealthBean3);
        mList.add(HealthBean1);
        mList.add(HealthBean2);
        mList.add(HealthBean3);

        cookBookAdapter = new HealthAdapter(mList, this);
        cookBookAdapter.isFirstOnly(false);
        cookBookAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(cookBookAdapter);

        //点击item监听
        cookBookAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HealthActivity.this, HealthDetailsActivity.class);
                startToActivity(intent, false);

            }
        });

    }

    //网络请求
    protected int pageNum = 1;

    protected void setBaseLoadData() {
    }

    //处理获取后台数据逻辑处理
    protected void setBaseSuccessfulDate(String requestWhat, Object data) {
        /*List<ArticleList> articleLists = JSON.parseArray(((JSONObject) data).get("viewList").toString(), ArticleList.class);
        mList.addAll(articleLists);

        if (mList.size() == 0) {
            llLayout.setVisibility(View.VISIBLE);
        } else {
            llLayout.setVisibility(View.GONE);

            if (isAdd) {
                //上拉加载
                moreAdapter.setList(mList);
                if (articleLists.size() == 0) {
                    showToast("没有更多数据");
                }
            } else {
                //下拉刷新
                mList.clear();
                mList.addAll(articleLists);
                //下拉刷新调用适配器的setNewData方法
                moreAdapter.setNewData(mList);
            }


        }*/
    }


}
