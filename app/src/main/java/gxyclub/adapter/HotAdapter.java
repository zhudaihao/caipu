package gxyclub.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HotBean;
import utils.GlideRoundTransform;

/**
 * 热门
 */

public class HotAdapter extends BaseQuickAdapter<HotBean, BaseViewHolder> {
    private List<HotBean> mList;
    private Activity activity;

    public void setList(List<HotBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HotAdapter(List<HotBean> mList, Activity activity) {
        super(R.layout.item_hot, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HotBean item) {
        View view = helper.itemView;

        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .transform(new GlideRoundTransform(activity))
                .into(iv_icon);



    }


}
