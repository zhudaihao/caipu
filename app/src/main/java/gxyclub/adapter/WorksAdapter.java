package gxyclub.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.WorkBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 上传
 */

public class WorksAdapter extends BaseItemDraggableAdapter<WorkBean, BaseViewHolder> {
    private List<WorkBean> mList;
    private Activity activity;

    public void setList(List<WorkBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public WorksAdapter(List<WorkBean> mList, Activity activity) {
        super(R.layout.item_works, mList);
        this.mList = mList;
        this.activity = activity;
    }


    public ImageView iv_icon;

    @Override
    protected void convert(final BaseViewHolder helper, final WorkBean item) {
        View itemView = helper.itemView;
        iv_icon = (ImageView) itemView.findViewById(R.id.iv_image);

        if (TextUtils.isEmpty(item.getImage())) {
            //空
            Glide.with(activity)
                    .load(item.getImageId())
                    .error(R.mipmap.add_imge)
                    .into(iv_icon);
        } else {
            Glide.with(activity)
                    .load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                    .error(R.mipmap.banner).into(iv_icon);

        }


    }


}
