package gxyclub.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HotContentBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * 热门食材 详情
 */

public class HotContentAdapter extends BaseQuickAdapter<HotContentBean, BaseViewHolder> {


    private List<HotContentBean> list;
    private Activity activity;

    public HotContentAdapter(List<HotContentBean> list, Activity activity) {
        super(R.layout.item_hot_content, list);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotContentBean item) {
        View itemView = helper.itemView;
        LinearLayout relativeLayout = (LinearLayout) itemView.findViewById(R.id.ll_image);
        ImageView iv_l = (ImageView) itemView.findViewById(R.id.iv_l);
        ImageView iv_r = (ImageView) itemView.findViewById(R.id.iv_r);

        if (TextUtils.isEmpty(item.getImage())) {
            relativeLayout.setVisibility(View.GONE);
        } else {
            relativeLayout.setVisibility(View.VISIBLE);
            Glide.with(activity)
                    .load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(iv_l);

            Glide.with(activity)
                    .load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(iv_r);
        }

    }


}
