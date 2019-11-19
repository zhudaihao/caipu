package gxyclub.ui.activity.cookBookActivity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import gxyclub.adapter.ImageAdapter;
import gxyclub.bean.WorkBean;

/**
 * 浏览图片
 */

public class ImageActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView textView;
    @BindView(R.id.ll_layout)
    RelativeLayout ll_layout;

    @Override
    public int getResLayout() {
        return R.layout.activity_image;
    }

    private List<WorkBean> list = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private int tag = 0;
    //视图
    private List<PhotoView> viewList = new ArrayList<>();


    @Override
    protected void initView() {
        super.initView();
        List<WorkBean> imageList = (List<WorkBean>) getIntent().getSerializableExtra("viewList");
        list.clear();
        list.addAll(imageList);

        for (int i = 0; i < list.size(); i++) {
            PhotoView photoView = new PhotoView(this);
            photoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            //开启缩放功能
            photoView.enable();
            //把图片设置到组件
            Glide.with(this).load(list.get(i).getImage()).into(photoView);
            //把组件装进集合
            viewList.add(photoView);

        }

        rl_base_title.setVisibility(View.GONE);
        setData();

    }

    private boolean isOne = true;
    private int tagNum = 0;

    private void setData() {
        imageAdapter = new ImageAdapter(this, list, viewList);
        viewPager.setAdapter(imageAdapter);
        viewPager.setCurrentItem(tag);//显示当前显示的
        if (isOne) {
            textView.setText(1 + "/" + list.size());
        } else {
            textView.setText((tagNum + 1) + "/" + list.size());
            if (list.size() == 0) {
                textView.setText(0 + "/" + 0);
                ll_layout.setVisibility(View.VISIBLE);
            } else {
                ll_layout.setVisibility(View.GONE);
            }
            if (list.size()==1){
                textView.setText(1 + "/" + 1);
            }
        }


        //滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tag = position;
                //设置显示数字
                tagNum = position;
                textView.setText(String.valueOf(tagNum + 1) + "/" + list.size());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.rb_left, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_left:
                setFinish();
                break;
            case R.id.iv_clear:
                setClear();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setFinish();
    }

    protected void setFinish() {
        Intent intent = new Intent();
        intent.putExtra("image", (Serializable) list);

        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(0, R.anim.anim_right);
    }

    //点击删除
    private void setClear() {
        isOne = false;
        if (list.size() > 0) {
            //更新当前位置
            if (tagNum >= list.size() && tagNum >= 1) {
                tagNum--;
            }
            if (tagNum >= list.size()) {
                tagNum = list.size() - 1;
            }

            //防止最后一个报错
            if (list.size() == 1 && tag == 1) {
                tag--;
                textView.setText(0 + "/" + 0);
            }

            list.remove(tag);
            viewList.remove(tag);
            setData();


        }
    }
}
