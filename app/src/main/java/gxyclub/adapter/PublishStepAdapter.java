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
import gxyclub.bean.PublishBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * BaseItemDraggableAdapter
 */

public class PublishStepAdapter extends BaseItemDraggableAdapter<PublishBean, BaseViewHolder> {
    private List<PublishBean> mList;
    private Activity activity;

    public void setList(List<PublishBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<PublishBean> getmList() {
        return mList;
    }

    public PublishStepAdapter(List<PublishBean> mList, Activity activity) {
        super(R.layout.item_step, mList);
        this.mList = mList;
        this.activity = activity;
    }

    public ImageView iv_clear, iv_add, iv_image_clear;
    private ImageView iv_move;

    @Override
    protected void convert(final BaseViewHolder helper, final PublishBean item) {
        iv_clear = (ImageView) helper.itemView.findViewById(R.id.iv_clear);
        iv_image_clear = (ImageView) helper.itemView.findViewById(R.id.iv_image_clear);
        iv_move = (ImageView) helper.itemView.findViewById(R.id.iv_move);
        iv_add = (ImageView) helper.itemView.findViewById(R.id.iv_add);

        if (TextUtils.isEmpty(item.getTitle())) {
            iv_clear.setVisibility(View.VISIBLE);
            iv_move.setVisibility(View.VISIBLE);
        } else {
            iv_clear.setVisibility(View.GONE);
            iv_move.setVisibility(View.GONE);
        }


        if (item.getImage() == null) {
            Glide.with(activity)
                    .load(R.mipmap.step)
                    .into(iv_add);

            iv_image_clear.setVisibility(View.GONE);
        } else {
            iv_image_clear.setVisibility(View.VISIBLE);

            Glide.with(activity).load(item.getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .error(R.mipmap.step)
                    .into(iv_add);

        }

        //点击清除监听回调
        iv_image_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clearListener != null) {
                    clearListener.setClear(helper.getPosition());
                }
            }
        });

        //点击添加回调监听
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addListener != null) {
                    addListener.setAdd(helper.getPosition());
                }
            }
        });

        //删除item
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clearItemListener != null) {
                    clearItemListener.setClear(helper.getPosition());
                }
            }
        });

    }

    //点击删除回调
    public interface ClearItemListener {
        void setClear(int position);
    }

    private static ClearItemListener clearItemListener;

    public void setClearItemListener(ClearItemListener clearItemListener) {
        PublishStepAdapter.clearItemListener = clearItemListener;
    }

    //点击删除回调
    public interface ClearListener {
        void setClear(int position);
    }

    private static ClearListener clearListener;

    public void setClearListener(ClearListener clearListener) {
        PublishStepAdapter.clearListener = clearListener;
    }

    //点击添加回调
    public interface AddListener {
        void setAdd(int position);
    }

    private static AddListener addListener;

    public void setAddListener(AddListener addListener) {
        PublishStepAdapter.addListener = addListener;
    }
}
