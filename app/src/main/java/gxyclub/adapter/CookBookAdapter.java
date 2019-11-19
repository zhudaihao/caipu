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
import gxyclub.bean.CookBookBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 菜谱精选更多
 */

public class CookBookAdapter extends BaseQuickAdapter<CookBookBean, BaseViewHolder> {

    private List<CookBookBean> list;
    private Activity activity;

    public void setList(List<CookBookBean> list) {
        this.list = list;
    }

    public CookBookAdapter(List<CookBookBean> list, Activity activity) {
        super(R.layout.item_cookbook, list);
        this.list = list;
        this.activity = activity;
    }



    @Override
    protected void convert(BaseViewHolder helper, CookBookBean item) {
        CardView ll_layout = (CardView) helper.itemView.findViewById(R.id.cv_layout);
        if (helper.getPosition() == (list.size() - 1)) {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(40, 30, 40, 40);
            ll_layout.setLayoutParams(lp);
        }

        ImageView iv_image = (ImageView) helper.itemView.findViewById(R.id.iv_image);

        Glide.with(activity)
                .load(item.getImage())
                .error(R.mipmap.banner)
                .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(iv_image);

        helper.setText(R.id.tv_name, item.getTitle());


    }


}
