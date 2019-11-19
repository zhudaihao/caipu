package gxyclub.adapter;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.PublishBean;


/**
 * BaseItemDraggableAdapter
 */

public class PublishAdapter extends BaseItemDraggableAdapter<PublishBean, BaseViewHolder> {
    private List<PublishBean> mList;

    public void setList(List<PublishBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<PublishBean> getmList() {
        return mList;
    }

    public PublishAdapter(List<PublishBean> mList) {
        super(R.layout.item_publish, mList);
        this.mList = mList;
    }

    public ImageView iv_clear,iv_move;

    @Override
    protected void convert(final BaseViewHolder helper, PublishBean item) {
        iv_clear = (ImageView) helper.itemView.findViewById(R.id.iv_clear);
        iv_move = (ImageView) helper.itemView.findViewById(R.id.iv_move);

        if (TextUtils.isEmpty(item.getTitle())) {
            iv_clear.setVisibility(View.VISIBLE);
            iv_move.setVisibility(View.VISIBLE);
        } else {
            iv_clear.setVisibility(View.GONE);
            iv_move.setVisibility(View.GONE);
        }

        helper.setText(R.id.et_food,item.getTitle());

        //删除item
        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clearPublishListener != null) {
                    clearPublishListener.setPublishClear(helper.getAdapterPosition());
                }

            }
        });


    }


    //点击删除回调
    public interface ClearPublishListener {
        void setPublishClear(int position);
    }

    private static ClearPublishListener clearPublishListener;

    public void setClearPublishListener(ClearPublishListener clearPublishListener) {
        PublishAdapter.clearPublishListener = clearPublishListener;
    }


}
