package gxyclub.adapter;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.MeCookBookBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 个人中心 菜谱
 */

public class MeCookBookAdapter extends BaseQuickAdapter<MeCookBookBean, BaseViewHolder> {
    private List<MeCookBookBean> mList;
    private Activity activity;

    public void setList(List<MeCookBookBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public MeCookBookAdapter(List<MeCookBookBean> mList, Activity activity) {
        super(R.layout.item_me_cookbook, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, MeCookBookBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
              .error(R.mipmap.banner).into(iv_icon);

        RelativeLayout relativeLayout = (RelativeLayout) helper.itemView.findViewById(R.id.ll_layout);

        if (helper.getPosition() == 0) {
            relativeLayout.setPadding(40, 30, 40, 34);
        }


    }


}
