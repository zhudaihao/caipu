package gxyclub.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;

import java.util.List;

import gxyclub.bean.WorkBean;

/**
 * 图片浏览
 */

public class ImageAdapter extends PagerAdapter {

    protected Context context;

    //创建个集合装载组件
    protected List<PhotoView> viewList;
    protected List<WorkBean> beanList;


    //构造方法
    public ImageAdapter(Context context, List<WorkBean> beanList, List<PhotoView> viewList) {
        this.context = context;
        this.beanList = beanList;
        this.viewList = viewList;

    }


    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //实际布局也可以在这里创建添加（另种操作方式）
        View view = viewList.get(position);
        container.addView(view);

        return view;
    }


    //滑动时删除的
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // container.removeView(viewList.get(position));//需要浏览删除功能就不能这么写
        container.removeView((View) object);
    }


}
