package base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/31.
 * 封装
 * MyBaseViewPager
 */

public abstract class MyBaseViewPager<T> extends PagerAdapter {

    protected LayoutInflater mInflater;
    protected List<T> mDataList;
    protected ArrayList<View> mViewSparseArray;
    protected Context context;




    public void setmViewSparseArray(ArrayList<View> mViewSparseArray) {
        if(this.mViewSparseArray != null){
            mViewSparseArray.clear();
        }
        this.mViewSparseArray = mViewSparseArray;
        notifyDataSetChanged();
    }

    public MyBaseViewPager(Context context, List<T> dataList) {
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
        mViewSparseArray = new ArrayList<View>(dataList.size());
        this.context=context;
    }

    @Override
    public int getCount() {
        if (mDataList == null) return 0;
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewSparseArray.get(position);
        if (view == null) {
            view = getView(position);
            mViewSparseArray.add(position, view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mViewSparseArray.get(position));
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return mDataList != null && mDataList.size()==0 ? POSITION_NONE : super.getItemPosition(object);
    }

    public abstract View getView(int position);

    public T getItem(int position) {
        return mDataList.get(position);
    }

}