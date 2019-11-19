package gxyclub.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.CookBookDetailsBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2018/9/27.
 */

public class CookBookDetailsAdapter extends BaseQuickAdapter<CookBookDetailsBean, BaseViewHolder> {
    private List<CookBookDetailsBean> mList;
    private Activity activity;

    public void setList(List<CookBookDetailsBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public CookBookDetailsAdapter(List<CookBookDetailsBean> mList, Activity activity) {
        super(R.layout.item_home_cookbook_details, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, CookBookDetailsBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_image);
        Glide.with(activity)
                .load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 8, 0, RoundedCornersTransformation.CornerType.ALL))
                .error(R.mipmap.banner).into(iv_icon);



    }


}
