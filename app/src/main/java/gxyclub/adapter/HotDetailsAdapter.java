package gxyclub.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HotDetailsBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 热门专辑详情
 */

public class HotDetailsAdapter extends BaseQuickAdapter<HotDetailsBean, BaseViewHolder> {
    private List<HotDetailsBean> mList;
    private Activity activity;

    public void setList(List<HotDetailsBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HotDetailsAdapter(List<HotDetailsBean> mList, Activity activity) {
        super(R.layout.item_home_capacity, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HotDetailsBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(R.mipmap.banner).into(iv_icon);


    }


}
