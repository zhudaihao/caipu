package gxyclub.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.gxyclub.R;
import gxyclub.bean.HomeEveryDayBean;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 每日三餐
 */
public class HomeEveryDayAdapter extends PagerAdapter {

    private List<HomeEveryDayBean> list;
    private Activity activity;
    private List<View> viewList = new ArrayList<>();

    public void setList(List<HomeEveryDayBean> list) {
        this.list = list;
    }

    public HomeEveryDayAdapter(List<HomeEveryDayBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        for (int i = 0; i < list.size(); i++) {
            //获取布局控件，设置数据
            View view = View.inflate(activity, R.layout.item_home_everyday, null);
            LinearLayout ll_layout = (LinearLayout) view.findViewById(R.id.ll_layout);

            ImageView iv_image_l = (ImageView) view.findViewById(R.id.iv_image_l);
            ImageView iv_image_r = (ImageView) view.findViewById(R.id.iv_image_r);
            Glide.with(activity)
                    .load(list.get(i).getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                    .into(iv_image_l);

            Glide.with(activity)
                    .load(list.get(i).getImage())
                    .bitmapTransform(new RoundedCornersTransformation(activity, 12, 0, RoundedCornersTransformation.CornerType.TOP))
                    .into(iv_image_r);

            //监听回调
            final int position = i;
            ll_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick != null) {
                        onClick.click(position);
                    }
                }
            });


            /**
             * 注意要把view装进集合
             */
            viewList.add(view);

        }

    }

    public interface OnClick {
        void click(int position);
    }

    private static OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(viewList.get(position));
        return viewList.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));

    }
}
