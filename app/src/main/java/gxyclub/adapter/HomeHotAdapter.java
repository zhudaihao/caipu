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
import gxyclub.bean.HomeHotBean;
import utils.GlideRoundTransform;

/**
 * Created by Administrator on 2018/9/27.
 */

public class HomeHotAdapter extends BaseQuickAdapter<HomeHotBean, BaseViewHolder> {
    private List<HomeHotBean> mList;
    private Activity activity;

    public void setList(List<HomeHotBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HomeHotAdapter(List<HomeHotBean> mList, Activity activity) {
        super(R.layout.item_home_hot, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeHotBean item) {
        ImageView iv_icon = (ImageView) helper.itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .transform(new GlideRoundTransform(activity))
                .error(R.mipmap.nothing).into(iv_icon);

        helper.setText(R.id.tv_name, item.getTitle());

        CardView cv_layout = (CardView) helper.itemView.findViewById(R.id.cv_layout);
        switch (helper.getPosition()) {
            case 0:
                helper.setBackgroundRes(R.id.tv_tab, R.mipmap.hot_one);
                helper.setText(R.id.tv_tab, "1");
                break;
            case 1:
                helper.setBackgroundRes(R.id.tv_tab, R.mipmap.hot_two);
                helper.setText(R.id.tv_tab, "2");
                break;
            case 2:
                helper.setBackgroundRes(R.id.tv_tab, R.mipmap.hot_thread);
                helper.setText(R.id.tv_tab, "3");
                //设置距离
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(40, 30, 40, 80);
                cv_layout.setLayoutParams(layoutParams);
                break;


        }
    }


}
