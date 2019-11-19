package gxyclub.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HealthDetailsBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 养生专辑详情 头布局
 */

public class HeaderHealthAdapter extends BaseQuickAdapter<HealthDetailsBean, BaseViewHolder> {
    private List<HealthDetailsBean> mList;
    private Activity activity;

    public void setList(List<HealthDetailsBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HeaderHealthAdapter(List<HealthDetailsBean> mList, Activity activity) {
        super(R.layout.item_header_health, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HealthDetailsBean item) {
        ImageView iv_icon_l = (ImageView) helper.itemView.findViewById(R.id.iv_icon_l);
        ImageView iv_icon_r = (ImageView) helper.itemView.findViewById(R.id.iv_icon_r);

        if (item.isShowOne()) {
            Glide.with(activity)
                    .load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .error(R.mipmap.banner).into(iv_icon_l);
            iv_icon_l.setPadding(0, 0, 14, 0);
            iv_icon_r.setVisibility(View.GONE);
        } else {
            Glide.with(activity)
                    .load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .error(R.mipmap.banner).into(iv_icon_l);
            iv_icon_l.setPadding(0, 0, 14, 0);
            iv_icon_r.setPadding(14, 0, 0, 0);

            Glide.with(activity)
                    .load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
                    .error(R.mipmap.banner).into(iv_icon_r);
        }
    }


}
