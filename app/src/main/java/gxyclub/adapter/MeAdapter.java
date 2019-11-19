package gxyclub.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.gxyclub.R;
import gxyclub.bean.HomeBean;

/**
 * 个人
 */

public class MeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {
    public MeAdapter() {
        super(R.layout.item_home_capacity);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
    }


}
