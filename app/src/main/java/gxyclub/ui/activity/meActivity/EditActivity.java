package gxyclub.ui.activity.meActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import cn.zdh.library_dialog.DialogUtils;
import cn.zdh.picturelibrary.LQRPhotoSelectUtils;
import cn.zdh.picturelibrary.PictureUtils;
import cn.zdh.picturelibrary.PopupShow;
import gxyclub.bean.JsonBean;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import utils.GetJsonDataUtil;
import utils.GlideRoundTransform;
import utils.Util;

/**
 * 编辑个人资料
 */

public class EditActivity extends BaseActivity implements PictureUtils.OnResult {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_intro)
    TextView tvIntro;

    @Override
    public int getResLayout() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_base_title.setText("编辑个人资料");
        pictureUtils = new PictureUtils(this);

        initTimePicker();//设置时间

    }

    private PictureUtils pictureUtils;

    @OnClick({R.id.rl_icon, R.id.rl_name, R.id.rl_sex, R.id.rl_age, R.id.rl_intro, R.id.rl_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击头像
            case R.id.rl_icon:
                pictureUtils.setOnResult(this);
                pictureUtils.popupShow.setOnOneClick(new PopupShow.OnOneClick() {
                    @Override
                    public void setOnOneClick() {
                        PermissionGen.with(EditActivity.this)
                                .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA
                                ).request();

                    }
                });
                pictureUtils.popupShow.setOnTwoClick(new PopupShow.OnTwoClick() {
                    @Override
                    public void setOnTwoClick() {
                        PermissionGen.needPermission(EditActivity.this,
                                LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        );
                    }
                });
                break;
            //昵称
            case R.id.rl_name:

                break;
            //性别
            case R.id.rl_sex:
                DialogUtils instance = DialogUtils.getInstance();
                instance.shownSexDialog(this);
                instance.setOnclickSex(new DialogUtils.OnclickSex() {
                    @Override
                    public void onClick(String content) {
                        tvSex.setText(content);
                    }
                });

                break;
            //年龄
            case R.id.rl_age:
                timePickerView.show();
                break;
            //地区
            case R.id.rl_area:
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                }
                break;
            //简介
            case R.id.rl_intro:

                Intent intent = new Intent(this, IntroActivity.class);
                startToActivity(intent, false);
                break;
        }
    }


    /**
     * 更换头像
     * 权限回调
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


    /**
     * 获取手机图片回调结果
     */
    @Override
    public void getResult(File outputFile, Uri outputUri) {
        Glide.with(this)
                .load(outputFile)
                .transform(new GlideRoundTransform(this))
                .into(ivIcon);

    }


    /**
     * 素龄
     */
    private TimePickerView timePickerView;

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();

        Calendar c = Calendar.getInstance();//
        int year = c.get(Calendar.YEAR);// 获取当前年份
        int month = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int day = c.get(Calendar.DAY_OF_MONTH);// 获取当日期


        startDate.set(1917, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year, month, day);


        timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                String time = Util.getTimeNew(date);
                tvAge.setText(time);


            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, false, false, false, false, false})
                .setLabel("年", "", "", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setTitleText("请选择素龄")
                .setTitleSize(14)
                .setTitleColor(Color.parseColor("#7A7A7A"))
                .setTitleBgColor(Color.parseColor("#FFFFFF"))
                .setSubCalSize(14)
                .setSubmitColor(Color.parseColor("#333333"))
                .setSubmitText("确定")
                //设置分割线的颜色
//                .setDividerColor(Color.parseColor("#00000000"))
                .setContentSize(21)//字大小
                .setLineSpacingMultiplier(2)//间距
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                //.setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();


    }


    /**
     * 地区
     */
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private Thread thread;
    private boolean isLoaded = false;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    ShowPickerView();
                    break;

                case MSG_LOAD_FAILED:

                    break;

            }
        }
    };

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //获取本地JSON文件
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {
        //Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    //地区
    private void ShowPickerView() {
        // 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + " " +
                        options2Items.get(options1).get(options2) + " " +
                        options3Items.get(options1).get(options2).get(options3);

                tvArea.setText(tx);


            }
        })
                .setTitleText("请选择地区")
                .setTitleSize(14)
                .setTitleColor(Color.parseColor("#7A7A7A"))
                .setSubCalSize(14)
                .setSubmitColor(Color.parseColor("#333333"))
                .setSubmitText("确定")
                .setTitleBgColor(Color.parseColor("#FFFFFF"))
               /* .setDividerColor(R.color.xieyiback)
                .setTextColorCenter(R.color.xieyiback) //设置选中项文字颜色*/
                .setContentTextSize(20)
                .setLineSpacingMultiplier(2)//间距
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

}
