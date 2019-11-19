package gxyclub.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HealthDetailsBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 养生专辑详情
 */

public class HealthDetailsAdapter extends BaseQuickAdapter<HealthDetailsBean, BaseViewHolder> {
    private List<HealthDetailsBean> mList;
    private Activity activity;

    public void setList(List<HealthDetailsBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HealthDetailsAdapter(List<HealthDetailsBean> mList, Activity activity) {
        super(R.layout.item_home_capacity, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HealthDetailsBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(R.mipmap.banner).into(iv_icon);


    }


}
