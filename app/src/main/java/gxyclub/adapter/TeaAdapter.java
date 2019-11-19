package gxyclub.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.BreakFastBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 下午茶
 */

public class TeaAdapter extends BaseQuickAdapter<BreakFastBean, BaseViewHolder> {
    private List<BreakFastBean> mList;
    private Activity activity;

    public void setList(List<BreakFastBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public TeaAdapter(List<BreakFastBean> mList, Activity activity) {
        super(R.layout.item_breakfast, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, BreakFastBean item) {
        View view = helper.itemView;
        LinearLayout cv_layout_r = (LinearLayout) view.findViewById(R.id.ll_layout);

        if (helper.getPosition() == 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 30, 0, 0);
            cv_layout_r.setLayoutParams(layoutParams);
        }

        ImageView iv_image_l = (ImageView) view.findViewById(R.id.iv_image_l);
        ImageView iv_image_r = (ImageView) view.findViewById(R.id.iv_image_r);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(iv_image_l);

        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(iv_image_r);


    }


}
