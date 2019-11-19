package gxyclub.ui.activity.cookBookActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gxyclub.R;
import gxyclub.adapter.WorksAdapter;
import gxyclub.bean.WorkBean;
import gxyclub.view.RatingBar;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;

/**
 * Created by Administrator on 2018/10/18.
 */

public class WorksActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    private static final int IMAGE = 666;
    @BindView(R.id.et_edit)
    EditText etEdit;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.star)
    RatingBar star;

    private static final int max = 9;


    @Override
    public int getResLayout() {
        return R.layout.activity_works;
    }


    private WorksAdapter worksAdapter;
    private ArrayList<WorkBean> list = new ArrayList<>();
    private WorkBean addBean;

    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    protected void initView() {
        super.initView();
        tv_base_title.setText("上传作品");
        rb_base_right.setText("发布");

        rb_base_left.setText("取消");
        rb_base_left.setBackground(null);
        rb_base_left.setCompoundDrawables(null, null, null, null);
        rb_base_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTopActivity();
            }
        });

        rb_base_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击发布");

            }
        });


        star.setClickable(true);
        star.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {

                showToast("--"+ratingCount);
            }
        });


        setPick();

        worksAdapter = new WorksAdapter(list, this);
        recyclerView.setHasFixedSize(true);

        //设置移动
        final OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                if (pos == (list.size() - 1)) {
                    worksAdapter.setToggleDragOnLongPress(false);
                } else {
                    worksAdapter.setToggleDragOnLongPress(true);
                }
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            }
        };

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(worksAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);


        worksAdapter.enableDragItem(mItemTouchHelper);
        worksAdapter.setOnItemDragListener(listener);

        addBean = new WorkBean(R.mipmap.add_imge);
        list.add(addBean);

        final GridLayoutManager manager = new GridLayoutManager(this, 3);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(worksAdapter);
        worksAdapter.setOnItemClickListener(this);


    }

    private PickConfig.Builder builder;

    private void setPick() {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(60);

        //设置获取图片框架
        builder = new PickConfig.Builder(this)
                .isneedcrop(false)//是否需要剪裁
                .actionBarcolor(Color.parseColor("#FABE19"))//title颜色
                .statusBarcolor(Color.parseColor("#FABE19"))//状态栏颜色
                .isneedcamera(true)//是否需要相机
                .isSqureCrop(true)//是否正方形剪裁
                .setUropOptions(options)//图片压缩比例
                .maxPickSize(max)//多选最多选择几张
                .spanCount(3)//一行显示几张
                .pickMode(PickConfig.MODE_MULTIP_PICK);
    }


    @Override
    public void onBackPressed() {
        finishTopActivity();
    }

    //点击item
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //通过0判断是否点击添加按钮
        if (list.get(position).getImageId() != 0) {

            if (tagList.size() != 0) {
                builder.maxPickSize(max - tagList.size());
            } else {
                builder.maxPickSize(max);
            }

            //权限
            PermissionGen.needPermission(this,
                    PICK_REQUEST_CODE,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}
            );


        } else {

            /**
             * 跳转
             */
            Intent intent = new Intent(WorksActivity.this, ImageActivity.class);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getImageId() != 0) {
                    list.remove(i);
                }
            }
            intent.putExtra("viewList", list);
            startActivityForResult(intent, IMAGE);
            overridePendingTransition(R.anim.anim_left, 0);
        }

    }


    //相册
    @PermissionSuccess(requestCode = PICK_REQUEST_CODE)
    private void selectPhoto() {
        builder.build();
    }


    //拒绝
    @PermissionFail(requestCode = PICK_REQUEST_CODE)
    private void showTip2() {
        showToast("授权后才能正常使用拍照或图片选择功能");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    //获取图片回调
    private ArrayList<WorkBean> tagList = new ArrayList<>();

    /**
     * 回调
     */
    private List<WorkBean> resultList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相机回调
        if (resultCode == RESULT_OK && requestCode == PICK_REQUEST_CODE) {
            //在data中返回 选择的图片列表
            ArrayList<String> paths = data.getStringArrayListExtra("data");
            list.remove(list.size() - 1);
            for (String path : paths) {
                WorkBean workBean = new WorkBean(path);
                list.add(workBean);
                tagList.add(workBean);
            }

            if (tagList != null) {
                if (tagList.size() < max) {
                    list.add(list.size(), addBean);
                } else if (tagList.size() == max) {
                    list.clear();
                    list.addAll(tagList);

                }

                worksAdapter.setList(list);

            }

        }
        //浏览图片后回调
        if (resultCode == RESULT_OK && requestCode == IMAGE) {
            //刷新适配器
            resultList = (List<WorkBean>) data.getSerializableExtra("image");
            list.clear();
            list.addAll(resultList);
            tagList.clear();
            tagList.addAll(resultList);

            if (list.size() < max) {
                list.add(addBean);
            }
            worksAdapter.setList(list);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
