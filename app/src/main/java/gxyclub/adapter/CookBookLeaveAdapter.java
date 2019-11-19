package gxyclub.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.CookBookLeaveBean;

/**
 * Created by Administrator on 2018/10/10.
 */

public class CookBookLeaveAdapter extends BaseQuickAdapter<CookBookLeaveBean, BaseViewHolder> {

    private List<CookBookLeaveBean> list;

    public void setList(List<CookBookLeaveBean> list) {
        this.list = list;
    }

    private Activity activity;

    public CookBookLeaveAdapter(List<CookBookLeaveBean> list, Activity activity) {
        super(R.layout.item_cookbook_leave, list);
        this.list = list;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, CookBookLeaveBean item) {


    }


}
