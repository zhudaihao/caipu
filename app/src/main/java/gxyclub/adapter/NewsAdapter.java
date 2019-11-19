package gxyclub.adapter;


import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.NewsBean;
import gxyclub.ui.activity.MainActivity;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 *趣闻
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {
    private List<NewsBean> mList;
    private Fragment fragment;
    private MainActivity context;

    public void setList(List<NewsBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<NewsBean> getmList() {
        return mList;
    }

    public NewsAdapter(List<NewsBean> mList, Fragment fragment, MainActivity context) {
        super(R.layout.item_news, mList);
        this.mList = mList;
        this.fragment = fragment;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {

        ImageView imageView= (ImageView) helper.itemView.findViewById(R.id.iv_image);
        Glide.with(fragment).load(item.getImage())
                .error(R.mipmap.banner)
                .bitmapTransform(new RoundedCornersTransformation(context,12,0, RoundedCornersTransformation.CornerType.ALL) )
                .into(imageView);


    }
}
