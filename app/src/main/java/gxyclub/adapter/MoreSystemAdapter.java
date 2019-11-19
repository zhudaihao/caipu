package gxyclub.adapter;


import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.MoreSystemBean;


/**
 * 公告（系统）适配器
 */

public class MoreSystemAdapter extends BaseQuickAdapter<MoreSystemBean, BaseViewHolder> {
    private List<MoreSystemBean> mList;
    private Activity activity;

    public void setList(List<MoreSystemBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<MoreSystemBean> getList() {
        return mList;
    }

    public MoreSystemAdapter(List<MoreSystemBean> mList, Activity activity) {
        super(R.layout.item_system_more, mList);
        this.mList = mList;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreSystemBean item) {
        View itemView = helper.itemView;

    }
}