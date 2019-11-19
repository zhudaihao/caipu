package gxyclub.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.CookBookProductionBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2018/10/10.
 */

public class CookBookProductionAdapter extends BaseQuickAdapter<CookBookProductionBean, BaseViewHolder> {

    private List<CookBookProductionBean> list;

    public void setList(List<CookBookProductionBean> list) {
        this.list = list;
    }

    private Activity activity;

    public CookBookProductionAdapter(List<CookBookProductionBean> list, Activity activity) {
        super(R.layout.item_cookbook_production, list);
        this.list = list;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, CookBookProductionBean item) {
        View itemView = helper.itemView;
        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_image);
        Glide.with(activity).load(item.getImage())
                .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(imageView);

    }


}
