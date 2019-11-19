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
import gxyclub.bean.MoreCapacityBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 智能推荐
 */

public class MoreCapacityAdapter extends BaseQuickAdapter<MoreCapacityBean, BaseViewHolder> {
    private List<MoreCapacityBean> mList;
    private Activity activity;

    public void setList(List<MoreCapacityBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public MoreCapacityAdapter(List<MoreCapacityBean> mList, Activity activity) {
        super(R.layout.item_home_capacity, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, MoreCapacityBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(R.mipmap.banner).into(iv_icon);

        CardView cv_layout = (CardView) helper.itemView.findViewById(R.id.cv_layout);
        if (helper.getPosition() == 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(40, 30, 40, 30);
            cv_layout.setLayoutParams(layoutParams);
        }


    }



}
