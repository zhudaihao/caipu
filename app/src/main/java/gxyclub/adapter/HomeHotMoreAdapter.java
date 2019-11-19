package gxyclub.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HomeHotMoreBean;
import utils.GlideRoundTransform;

/**
 * Created by Administrator on 2018/9/27.
 */

public class HomeHotMoreAdapter extends BaseQuickAdapter<HomeHotMoreBean, BaseViewHolder> {
    private List<HomeHotMoreBean> mList;
    private Activity activity;

    public void setList(List<HomeHotMoreBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public HomeHotMoreAdapter(List<HomeHotMoreBean> mList, Activity activity) {
        super(R.layout.item_home_hot_more, mList);
        this.mList = mList;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeHotMoreBean item) {
        View itemView = helper.itemView;
        ImageView iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        Glide.with(activity)
                .load(item.getImage())
                .transform(new GlideRoundTransform(activity))
                .error(R.mipmap.nothing).into(iv_icon);

        helper.setText(R.id.tv_name, item.getTitle());
        CardView cv_layout = (CardView) itemView.findViewById(R.id.cv_layout);
        TextView textView = (TextView) itemView.findViewById(R.id.tvNum);


        switch (helper.getPosition()) {
            case 0:
                textView.setVisibility(View.VISIBLE);
                helper.setBackgroundRes(R.id.tvNum, R.mipmap.hot_one);
                helper.setText(R.id.tvNum, "1");

                //设置距离
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(40, 30, 40, 30);
                cv_layout.setLayoutParams(layoutParams);
                break;
            case 1:
                textView.setVisibility(View.VISIBLE);
                helper.setBackgroundRes(R.id.tvNum, R.mipmap.hot_two);
                helper.setText(R.id.tvNum, "2");
                break;
            case 2:
                textView.setVisibility(View.VISIBLE);
                helper.setBackgroundRes(R.id.tvNum, R.mipmap.hot_thread);
                helper.setText(R.id.tvNum, "3");

                break;
            default:
                textView.setVisibility(View.GONE);
                break;
        }
    }
}
