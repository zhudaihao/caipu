package gxyclub.ui.fragment.everyFragment;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import base.BaseRefreshFragment;
import gxyclub.adapter.DinnerFastAdapter;
import gxyclub.bean.BreakFastBean;
import gxyclub.ui.activity.cookBookActivity.CookBookDetailsActivity;


/**
 * 晚餐
 */

public class DinnerFastFragment extends BaseRefreshFragment {

    private DinnerFastAdapter dinnerFastAdapter;
    private List<BreakFastBean> mList = new ArrayList<>();

    //设置标题和刷新设置，适配器
    protected void setBaseRefreshAdapter() {
        //假数据
        BreakFastBean cookBookBean1 = new BreakFastBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349582&di=52c28ecce77e3209b90444d64f2f3e2f&imgtype=0&src=http%3A%2F%2Fuploads.jy135.com%2Fallimg%2F201706%2F18-1F6031GF0.jpg", "测试1");
        BreakFastBean cookBookBean2 = new BreakFastBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349774&di=85f45dbf0b6a095b320e3a986cf4ac48&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150210%2Fsucaizijuan_3945508.jpg", "测试2");
        BreakFastBean cookBookBean3 = new BreakFastBean("https://timgsa.baidu.com/timg?beanList&quality=80&size=b9999_10000&sec=1538052349775&di=12235135a54b53f54846d35d2a423a1c&imgtype=0&src=http%3A%2F%2Fwww.360changshi.com%2Fcp%2Fd%2Ffile%2Fbigpic%2F2016%2F06%2F14%2Fsgyja1o5ofp.jpg", "测试3");

        mList.add(cookBookBean1);
        mList.add(cookBookBean2);
        mList.add(cookBookBean3);

        dinnerFastAdapter = new DinnerFastAdapter(mList, activity);
        //false动画重复
        dinnerFastAdapter.isFirstOnly(false);
        //设置那种动画
        dinnerFastAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        recyclerView.setAdapter(dinnerFastAdapter);

        dinnerFastAdapter.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        startToActivity(new Intent(activity, CookBookDetailsActivity.class), false);
    }

    //设置网络请求
    protected void setBaseLoadData() {
    }

    //设置网络请求成功后的逻辑处理
    protected void setBaseSuccessfulDate(String requestWhat, Object data) {
    }
}
