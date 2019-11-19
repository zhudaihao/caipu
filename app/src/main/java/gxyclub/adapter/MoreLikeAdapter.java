package gxyclub.adapter;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.MoreLikeBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import utils.GlideRoundTransform;


/**
 * 公告（点赞）适配器
 */

public class MoreLikeAdapter extends BaseQuickAdapter<MoreLikeBean, BaseViewHolder> {
    private List<MoreLikeBean> mList;
    private Activity activity;

    public void setList(List<MoreLikeBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<MoreLikeBean> getList() {
        return mList;
    }

    public MoreLikeAdapter(List<MoreLikeBean> mList, Activity activity) {
        super(R.layout.item_more_collect, mList);
        this.mList = mList;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreLikeBean item) {
        View itemView = helper.itemView;
        ImageView iv_image_l = (ImageView) itemView.findViewById(R.id.iv_image_l);
        ImageView iv_image_r = (ImageView) itemView.findViewById(R.id.iv_image_r);
        Glide.with(activity).load(item.getImage()).transform(new GlideRoundTransform(activity)).into(iv_image_l);
        Glide.with(activity).load(item.getImage()).bitmapTransform(new RoundedCornersTransformation(activity, 8, 0,RoundedCornersTransformation.CornerType.ALL)).into(iv_image_r);

    }
}