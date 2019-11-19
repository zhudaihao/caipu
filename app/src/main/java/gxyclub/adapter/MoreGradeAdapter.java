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
import gxyclub.bean.MoreGradeBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 评分最多
 */

public class MoreGradeAdapter extends BaseQuickAdapter<MoreGradeBean, BaseViewHolder> {
    private List<MoreGradeBean> mList;
    private Activity activity;

    public void setList(List<MoreGradeBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public MoreGradeAdapter(List<MoreGradeBean> mList, Activity activity) {
        super(R.layout.item_home_capacity, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, MoreGradeBean item) {
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
