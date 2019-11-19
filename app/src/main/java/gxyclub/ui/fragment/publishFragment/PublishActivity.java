package gxyclub.ui.fragment.publishFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szysky.customize.siv.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.Unbinder;
import cn.gxyclub.R;
import cn.zdh.picturelibrary.LQRPhotoSelectUtils;
import cn.zdh.picturelibrary.PictureUtils;
import gxyclub.adapter.PublishAdapter;
import gxyclub.adapter.PublishStepAdapter;
import gxyclub.bean.PublishBean;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


/**
 * 发布
 */

public class PublishActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private View header;
    private View footer;


    @Override
    public int getResLayout() {
        return R.layout.activity_publish;
    }


    private PublishAdapter publishAdapter;
    private List<PublishBean> list = new ArrayList<>();
    private List<PublishBean> saveList = new ArrayList<>();

    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback, stepCallBack;
    private ItemTouchHelper mItemTouchHelper, mItemStep;

    private PictureUtils pictureUtils;

    private PublishBean cookBookBean1, cookBookBean2, cookBookBean3;


    @Override
    protected void initView() {
        super.initView();

        tv_base_title.setText("上传菜谱");
        rb_base_left.setText("取消");
        rb_base_left.setCompoundDrawables(null, null, null, null);
        rb_base_right.setText("保存");

        //获取图片
        pictureUtils = new PictureUtils(this);

        rb_base_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTopActivity();
            }
        });


        //假数据
        cookBookBean1 = new PublishBean(null, "测试1");
        cookBookBean2 = new PublishBean(null, "测试2");
        cookBookBean3 = new PublishBean(null, "测试3");

        list.add(cookBookBean1);
        list.add(cookBookBean2);
        list.add(cookBookBean3);


        rb_base_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //假数据
                list.clear();
                cookBookBean1 = new PublishBean(null, "星期一");
                cookBookBean2 = new PublishBean(null, "星期二");
                cookBookBean3 = new PublishBean(null, "星期三");

                list.add(cookBookBean1);
                list.add(cookBookBean2);
                list.add(cookBookBean3);

                //保存数据
                setSave();

            }

        });


        publishAdapter = new PublishAdapter(list);
        //设置删除拖拽
        setSwipeDrag();

        //头布局
        View header = View.inflate(this, R.layout.header_publish, null);
        setHeader(header);

        //尾布局
        View footer = View.inflate(this, R.layout.footer_publish, null);
        setFooter(footer);

        publishAdapter.addHeaderView(header);
        publishAdapter.addFooterView(footer);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(publishAdapter);

        publishAdapter.setClearPublishListener(new PublishAdapter.ClearPublishListener() {
            @Override
            public void setPublishClear(int position) {
                LogUtil._e("agt", "------------>" + list.size());
                if (list.size() != 1) {
                    list.remove(position - 1);
                    publishAdapter.setList(list);
                } else {
                    showToast("不能删除,至少有一个");
                }
            }
        });

        //获取本地保存数据
        //获取保存的数据
        getSave();
        if (saveList.size() != 0) {
            list.clear();
            list.addAll(saveList);
            publishAdapter.setList(list);
        }

    }

    //保存数据到本地
    private void setSave() {
        SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List", Activity.MODE_PRIVATE);//创建sp对象
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list); //将List转换成Json
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString("KEY_NewUserModel_LIST_DATA", jsonStr); //存入json串
        editor.commit();  //提交
    }

    //获取本地保存数据
    private void getSave() {
        SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List", Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
        String peopleListJson = sp.getString("KEY_NewUserModel_LIST_DATA", "");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
        if (peopleListJson != "")  //防空判断
        {
            Gson gson = new Gson();
            saveList = gson.fromJson(peopleListJson, new TypeToken<List<PublishBean>>() {
            }.getType()); //将json字符串转换成List集合
        }
    }

    //头布局

    private ImageView iv_image;
    private EditText et_name, et_story;

    public void setHeader(View header) {
        iv_image = (ImageView) header.findViewById(R.id.iv_image);
        et_name = (EditText) header.findViewById(R.id.et_name);
        et_story = (EditText) header.findViewById(R.id.et_story);


        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictureUtils.setOnResult(new PictureUtils.OnResult() {
                    @Override
                    public void getResult(File outputFile, Uri outputUri) {
                        //图片回调结果
                        Glide.with(PublishActivity.this)
                                .load(outputFile)
                                .into(iv_image);
                    }
                });

            }
        });
    }


    /**
     * 添加封面图片
     */
    //相机
    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        pictureUtils.mLqrPhotoSelectUtils.takePhoto();
    }


    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {
        pictureUtils.showDialog();
    }


    //相册
    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        // 4、当拍照或从图库选取图片成功后回调
        pictureUtils.mLqrPhotoSelectUtils.selectPhoto();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {
        pictureUtils.showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        pictureUtils.mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }


    //尾部布局
    private TextView tv_add_line, tv_adjust_ingredient, tv_add_step, tv_adjust_step, tv_class;
    private RecyclerView rv_step;
    private Button bt_out;

    private boolean isOne, isStepOne;

    private PublishStepAdapter publishStepAdapter;

    public void setFooter(final View footer) {
        tv_add_line = (TextView) footer.findViewById(R.id.tv_add_line);
        tv_adjust_ingredient = (TextView) footer.findViewById(R.id.tv_adjust_ingredient);
        tv_add_step = (TextView) footer.findViewById(R.id.tv_add_step);
        tv_adjust_step = (TextView) footer.findViewById(R.id.tv_adjust_step);
        tv_class = (TextView) footer.findViewById(R.id.tv_class);

        rv_step = (RecyclerView) footer.findViewById(R.id.rv_step);
        bt_out = (Button) footer.findViewById(R.id.bt_out);


        //设置适配器
        setAdapter();

        //点击监听
        setListener();


    }

    //设置适配器
    private List<PublishBean> stepList = new ArrayList<>();

    private PublishBean cookBookBean4, cookBookBean5, cookBookBean6;

    private void setAdapter() {
        //假数据
        cookBookBean4 = new PublishBean(null, "测试1");
        cookBookBean5 = new PublishBean(null, "测试2");
        cookBookBean6 = new PublishBean(null, "测试3");

        stepList.add(cookBookBean4);
        stepList.add(cookBookBean5);
        stepList.add(cookBookBean6);


        publishStepAdapter = new PublishStepAdapter(stepList, this);
        //设置删除拖拽
        setStepSwipeDrag();


        rv_step.setLayoutManager(new LinearLayoutManager(this));
        rv_step.setAdapter(publishStepAdapter);

        //删除Item监听
        publishStepAdapter.setClearItemListener(new PublishStepAdapter.ClearItemListener() {
            @Override
            public void setClear(int position) {
                LogUtil._e("agt", "------------" + stepList.size());
                if (stepList.size() != 1) {
                    stepList.remove(position);
                    publishStepAdapter.setList(stepList);
                } else {
                    showToast("不能删除,至少有一个");
                }

            }
        });


        //清除回调监听
        publishStepAdapter.setClearListener(new PublishStepAdapter.ClearListener() {
            @Override
            public void setClear(int position) {
                PublishBean publishBean = stepList.get(position);

                publishBean.setTitle("1");
                publishBean.setImage(null);
                publishStepAdapter.notifyDataSetChanged();

            }
        });

        //添加回调
        publishStepAdapter.setAddListener(new PublishStepAdapter.AddListener() {
            @Override
            public void setAdd(final int position) {

                pictureUtils.setOnResult(new PictureUtils.OnResult() {
                    @Override
                    public void getResult(File outputFile, Uri outputUri) {
                        //图片回调结果
                        stepList.get(position).setImage(outputFile);
                        publishStepAdapter.setList(stepList);
                    }
                });
            }
        });


    }

    //点击监听
    private void setListener() {
        //增加一行
        tv_add_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(cookBookBean3);
                publishAdapter.setList(list);
            }
        });


        //调整用料
        tv_adjust_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOne) {
                    for (PublishBean publishBean : list) {
                        publishBean.setTitle("1");
                    }
                    publishAdapter.setList(list);


                } else {
                    for (PublishBean publishBean : list) {
                        publishBean.setTitle("");
                    }
                    publishAdapter.setList(list);

                }
                isOne = !isOne;

            }
        });


        /**
         * -------------
         */

        //增加一步
        tv_add_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepList.add(cookBookBean4);
                publishStepAdapter.setList(stepList);

            }
        });

        //调整步骤
        tv_adjust_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStepOne) {
                    for (PublishBean publishBean : stepList) {
                        publishBean.setTitle("1");
                    }
                    publishStepAdapter.setList(stepList);


                } else {
                    for (PublishBean publishBean : stepList) {
                        publishBean.setTitle("");
                    }
                    publishStepAdapter.setList(stepList);

                }

                isStepOne = !isStepOne;

            }
        });
    }

    /**
     * 用料
     * 设置拖拽
     */
    private void setSwipeDrag() {
        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {


            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {


            }
        };

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(publishAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);

        //拖拽
        publishAdapter.enableDragItem(mItemTouchHelper);
        publishAdapter.setOnItemDragListener(listener);
    }


    /**
     * 步骤
     * 设置拖拽
     */
    private void setStepSwipeDrag() {
        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {


            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {


            }
        };


        stepCallBack = new ItemDragAndSwipeCallback(publishStepAdapter);
        mItemStep = new ItemTouchHelper(stepCallBack);

        mItemStep.attachToRecyclerView(rv_step);

        stepCallBack.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);

        //拖拽
        publishStepAdapter.enableDragItem(mItemStep);
        publishStepAdapter.setOnItemDragListener(listener);


    }


    @Override
    public void onBackPressed() {
       finishTopActivity();
    }
}
