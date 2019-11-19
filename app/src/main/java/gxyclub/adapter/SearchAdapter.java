package gxyclub.adapter;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.SearchBean;

/**
 * 首页
 */

public class SearchAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {
    private List<SearchBean> mList;
    private Activity activity;

    public void setList(List<SearchBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public SearchAdapter(List<SearchBean> mList, Activity activity) {
        super(R.layout.item_search, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, SearchBean item) {
        helper.setText(R.id.tv_title, item.getContent());
        View itemView = helper.itemView;


    }


}
