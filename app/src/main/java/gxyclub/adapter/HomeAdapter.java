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
import gxyclub.bean.HomeBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 首页
 */

public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {
    private List<HomeBean> mList;
    private Activity activity;

    public void setList(List<HomeBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HomeAdapter(List<HomeBean> mList, Activity activity) {
        super(R.layout.item_home_capacity, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(R.mipmap.banner).into(iv_icon);

        CardView cv_layout = (CardView) helper.itemView.findViewById(R.id.cv_layout);
        if (helper.getPosition() == mList.size()) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(40, 0, 40, 60);
            cv_layout.setLayoutParams(layoutParams);
        }


    }


}
