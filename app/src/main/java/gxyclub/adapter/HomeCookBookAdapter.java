package gxyclub.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HomeCookBookBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import utils.GlideRoundTransform;

/**
 * Created by Administrator on 2018/9/27.
 */

public class HomeCookBookAdapter extends BaseQuickAdapter<HomeCookBookBean, BaseViewHolder> {
    private List<HomeCookBookBean> mList;
    private Activity activity;

    public void setList(List<HomeCookBookBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HomeCookBookAdapter(List<HomeCookBookBean> mList, Activity activity) {
        super(R.layout.item_home_cookbook, mList);
        this.mList = mList;
        this.activity = activity;
    }


    public ImageView iv_image;

    @Override
    protected void convert(BaseViewHolder helper, HomeCookBookBean item) {
        iv_image = (ImageView) helper.itemView.findViewById(R.id.iv_image);
        //图片半圆角 RoundedCornersTransformation
        Glide.with(activity).load(item.getImage())
                .error(R.mipmap.banner)

                .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(iv_image);
        helper.setText(R.id.tv_title, item.getTitle());

        CardView ll_layout = (CardView) helper.itemView.findViewById(R.id.cv_layout);
        if (helper.getPosition() == 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(40, 0, 30, 0);
            ll_layout.setLayoutParams(layoutParams);
        }

        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);

        Glide.with(activity)
                .load(item.getImage())
                .transform(new GlideRoundTransform(activity))
                .error(R.mipmap.nothing).into(iv_icon);

    }


}
