package cn.zdh.library_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用dialog
 */

public class DialogUtils {
    private DialogUtils() {
    }

    private static final DialogUtils designDialog = new DialogUtils();

    public static final DialogUtils getInstance() {
        return designDialog;
    }


    /**
     * --------------------design风格对话框-----------------------------------------------------
     */


    /**
     * 加载进度
     */
    private Dialog dialog;

    public void shownDialogProgress(Context context) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dailog_progress_layout);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });
        dialog.show();
        startAnimator(rl_layout);

    }

    /**
     * 普通提示对话框
     */
    public void shownDialog(Context context, String title, String content) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_hint_layout);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });

        RelativeLayout rl_layout2 = (RelativeLayout) dialog.findViewById(R.id.rl_tag2);
        rl_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //获取布局控件
        RadioButton tv_confirm = (RadioButton) dialog.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickConfirm != null) {
                    onclickConfirm.onClick("");
                }
                finishAnimator(rl_layout);
            }
        });


        //标题
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_title.setText(title);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_content.setText(content);

        RadioButton tv_cancel = (RadioButton) dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAnimator(rl_layout);
            }
        });
        dialog.show();
        startAnimator(rl_layout);

    }

    //回调
    public interface OnclickConfirm {
        void onClick(String content);
    }

    public static OnclickConfirm onclickConfirm;

    public void setOnclickConfirm(OnclickConfirm onclickConfirm) {
        DialogUtils.onclickConfirm = onclickConfirm;
    }

    /**
     * 输入对话框
     */
    public void shownDialogEdit(final Context context, String title, String hitText) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_edit_layout);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });

        RelativeLayout rl_layout2 = (RelativeLayout) dialog.findViewById(R.id.rl_tag2);
        rl_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //设置适配软键盘
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //获取布局控件
        final EditText et_content = (EditText) dialog.findViewById(R.id.et_content);
        //标题
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        et_content.setHint(hitText);
        tv_title.setText(title);

        RadioButton tv_confirm = (RadioButton) dialog.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickConfirmEdit != null) {
                    onclickConfirmEdit.onClick(et_content.getText().toString());
                }
                finishAnimator(rl_layout);

            }
        });


        RadioButton tv_cancel = (RadioButton) dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAnimator(rl_layout);
            }
        });
        dialog.show();
        startAnimator(rl_layout);
    }


    //回调
    public interface OnclickConfirmEdit {
        void onClick(String content);
    }

    public static OnclickConfirmEdit onclickConfirmEdit;

    public void setOnclickConfirmEdit(OnclickConfirmEdit onclickConfirmEdit) {
        DialogUtils.onclickConfirmEdit = onclickConfirmEdit;
    }

    /**
     * 列表对话框
     */
    public void shownDialogList(final Context context, final List<String> list, String title) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_list_layout);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });

        LinearLayout rl_layout2 = (LinearLayout) dialog.findViewById(R.id.rl_tag2);
        rl_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //标题
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_title.setText(title);

        //设置适配器
        ListView listView = (ListView) dialog.findViewById(R.id.lv_content);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.item_text_layout, R.id.tv_text, list);
        listView.setAdapter(arrayAdapter);

        //点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onclickConfirmList != null) {
                    onclickConfirmList.onClick(list.get(i));
                }
                finishAnimator(rl_layout);
            }
        });

        dialog.show();
        startAnimator(rl_layout);

    }

    //回调
    public interface OnclickConfirmList {
        void onClick(String content);
    }

    public static OnclickConfirmList onclickConfirmList;

    public void setOnclickConfirmList(OnclickConfirmList onclickConfirmList) {
        DialogUtils.onclickConfirmList = onclickConfirmList;
    }

    /**
     * 列表对话框 帶按钮
     */
    public ListView listView;

    public void shownDialogListButton(final Context context, final List<String> list, String title, String commit) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_list_button_layout);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        RelativeLayout rl_layout2 = (RelativeLayout) dialog.findViewById(R.id.rl_tag2);
        rl_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //获取布局控件
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_title.setText(title);

        RadioButton tv_commit = (RadioButton) dialog.findViewById(R.id.tv_commit);
        tv_commit.setText(commit);

        listView = (ListView) dialog.findViewById(R.id.lv_content);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.item_text_layout, R.id.tv_text, list);
        listView.setAdapter(arrayAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                finishAnimator(rl_layout);
//            }
//        });


        //点击删除按钮
        ImageView iv_delete = (ImageView) dialog.findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickConfirmListButton != null) {
                    onclickConfirmListButton.onClick("");
                }
                finishAnimator(rl_layout);
            }
        });

        dialog.show();
        startAnimator(rl_layout);

    }

    //回调
    public interface OnclickConfirmListButton {
        void onClick(String content);
    }

    public static OnclickConfirmListButton onclickConfirmListButton;

    public void setOnclickConfirmListButton(OnclickConfirmListButton onclickConfirmListButton) {
        DialogUtils.onclickConfirmListButton = onclickConfirmListButton;
    }


    /**
     * 列表对话框 帶图片
     */
    private int totalPage; //总的页数
    private int mPageSize = 3; //每页显示的最大的数量
    private ImageView[] ivPoints;//小圆点图片的集合

    //列表对话框
    public void shownDialogListImage(final Context context, final List<PriceEntity> list, String title) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_image_button_layout);

        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_layout);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tv_yes = (TextView) dialog.findViewById(R.id.tv_yes);
        ViewPager viewPager = (ViewPager) dialog.findViewById(R.id.viewpager);
        LinearLayout ll_line = (LinearLayout) dialog.findViewById(R.id.ll_line);//圆点指示器


        tv_title.setText(title);
        totalPage = (int) Math.ceil(list.size() * 1.0 / mPageSize);//大于等于最小整数
        List<View> viewPagerList = new ArrayList<>();//GridView作为一个View对象添加到ViewPager集合中

        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(context, R.layout.grid_view_item, null);
            GridViewAdapter adapter = new GridViewAdapter(context, list, i, 3);
            gridView.setAdapter(adapter);
            //添加item点击监听
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));

        if (list.size() > 3) {
            //添加小圆点
            ivPoints = new ImageView[totalPage];
            for (int i = 0; i < totalPage; i++) {
                //循坏加入点点图片组
                ivPoints[i] = new ImageView(context);
                if (i == 0) {
                    ivPoints[i].setImageResource(R.drawable.shap_selcetion);
                } else {
                    ivPoints[i].setImageResource(R.drawable.shap_un_selection);
                }
                ivPoints[i].setPadding(8, 8, 8, 8);
                ll_line.addView(ivPoints[i]);
            }

        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //  currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.shap_selcetion);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.shap_un_selection);
                    }
                }
            }
        });

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickConfirmListImage != null) {
                    onclickConfirmListImage.onClick("");
                }
                finishAnimator(rl_layout);
            }
        });

        startAnimator(rl_layout);
        dialog.show();


    }

    //回调
    public interface OnclickConfirmListImage {
        void onClick(String content);
    }

    public static OnclickConfirmListImage onclickConfirmListImage;

    public void setOnclickConfirmListImage(OnclickConfirmListImage onclickConfirmListImage) {
        DialogUtils.onclickConfirmListImage = onclickConfirmListImage;
    }


    /**
     * 勾选对话框
     */

    public CheckBox checkBox;
    public RadioButton tv_confirm_check;

    public void shownDialogCheck(final Context context, String title, String content) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_check_layout);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });

        RelativeLayout rl_layout2 = (RelativeLayout) dialog.findViewById(R.id.rl_tag2);
        rl_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //获取布局控件
        checkBox = (CheckBox) dialog.findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_title.setText(title);
        tv_content.setText(content);
        tv_confirm_check = (RadioButton) dialog.findViewById(R.id.tv_confirm);
        tv_confirm_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickConfirmCheck != null) {
                    onclickConfirmCheck.onClick("");
                }
                finishAnimator(rl_layout);

            }
        });


        RadioButton tv_cancel = (RadioButton) dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAnimator(rl_layout);
            }
        });

        startAnimator(rl_layout);
        dialog.show();

    }


    //回调
    public interface OnclickConfirmCheck {
        void onClick(String content);
    }

    public static OnclickConfirmCheck onclickConfirmCheck;

    public void setOnclickConfirmCheck(OnclickConfirmCheck onclickConfirmCheck) {
        DialogUtils.onclickConfirmCheck = onclickConfirmCheck;
    }


    /**
     * 关闭对话框
     */
    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

    }


    /**
     * -----------------------------------进度加载-------------------------------------------
     */


    /**
     * 多彩加载圆圈
     */
    public void shownDialogLoad(Context context, String text) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_load);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });


        //获取布局控件
        TextView tv_text = (TextView) dialog.findViewById(R.id.tv_text);
        tv_text.setText(text);
        startAnimator(rl_layout);
        dialog.show();

    }


    /**
     * 菊花 加载
     */
    public void shownDialogMum(Context context, String text) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_load_ios);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });


        //获取布局控件
        TextView tv_text = (TextView) dialog.findViewById(R.id.tv_text);
        tv_text.setText(text);
        startAnimator(rl_layout);
        dialog.show();
    }


    /**
     * loge 加载
     */

    public void shownLoadLoge(Context context, String text) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_loading);

        //设置点击外部关闭对话框
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_tag);
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAnimator(rl_layout);
            }
        });


        //获取布局控件
        TextView tv_text = (TextView) dialog.findViewById(R.id.tv_text);
        tv_text.setText(text);
        startAnimator(rl_layout);
        dialog.show();
    }

    /**
     * ---------------------------------------底部对话框----------------------------------------------------
     */

    /**
     * 底部对话框
     */
    private PopupWindow popWindow;

    public void showPopWindow(final Activity activity, final List<String> list, String title) {
        View parent = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);

        View view = LayoutInflater.from(activity).inflate(R.layout.pop_button_menu, null);
        popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置动画
        popWindow.setAnimationStyle(R.style.AnimBottom);
        //设置是否获取焦点
        popWindow.setFocusable(true);
        //设置点击外部是否关闭对话框
        popWindow.setOutsideTouchable(false);
        //设置背景颜色
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);

        //获取控件

        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        ListView list_view = (ListView) view.findViewById(R.id.list_view);
        RelativeLayout rl_tag = (RelativeLayout) view.findViewById(R.id.rl_tag);

        PopButtonAdapter popButtonAdapter = new PopButtonAdapter(activity, list);
        list_view.setAdapter(popButtonAdapter);

        rl_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

        //点击item
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onclickPopWind != null) {
                    onclickPopWind.onClick(list.get(i));
                }
                popWindow.dismiss();
            }
        });

        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //回调
    public interface OnclickPopWind {
        void onClick(String content);
    }

    public static OnclickPopWind onclickPopWind;

    public void setOnclickPopWind(OnclickPopWind onclickPopWind) {
        DialogUtils.onclickPopWind = onclickPopWind;
    }

    /**
     * 底部对话框 带图标的
     */
    public void showPopWindowIcon(final Activity activity, final List<String> listText, List<Integer> listIcon) {
        View parent = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        View view = LayoutInflater.from(activity).inflate(R.layout.pop_button_icon, null);

        popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置动画
        popWindow.setAnimationStyle(R.style.AnimBottom);
        //设置是否获取焦点
        popWindow.setFocusable(true);
        //设置点击外部是否关闭对话框
        popWindow.setOutsideTouchable(false);
        //设置背景颜色
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);

        //获取控件
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        RadioButton tv_cancel = (RadioButton) view.findViewById(R.id.tv_cancel);
        RelativeLayout rl_tag = (RelativeLayout) view.findViewById(R.id.rl_tag);

        //设置适配器
        GridAdapter gridAdapter = new GridAdapter(listText, listIcon, activity);
        gridView.setAdapter(gridAdapter);

        //点击监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onclickPopWindIcon != null) {
                    onclickPopWindIcon.onClick(listText.get(i));
                }
                dismissPopWindow();
            }
        });
        rl_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissPopWindow();
            }
        });

        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //回调
    public interface OnclickPopWindIcon {
        void onClick(String content);
    }

    public static OnclickPopWindIcon onclickPopWindIcon;

    public void setOnclickPopWindIcon(OnclickPopWindIcon onclickPopWindIcon) {
        DialogUtils.onclickPopWindIcon = onclickPopWindIcon;
    }


    /**
     * 关闭对话框
     */
    public void dismissPopWindow() {
        if (popWindow != null) {
            popWindow.dismiss();
            popWindow = null;
        }
    }


    /**
     * -------------------------------仿ISO对话框-----------------------------------------------------
     */


    public EditText editText;

    /**
     * 带编辑框的dialog
     */
    public void shownIosDialogEditText(final Context context, String title, String message) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_ios_edit_layout);


        //适应键盘
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //获取控件
        editText = (EditText) dialog.findViewById(R.id.tv_edit);
        RadioButton tv_yes = (RadioButton) dialog.findViewById(R.id.tv_yes);
        RadioButton tv_no = (RadioButton) dialog.findViewById(R.id.tv_no);
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_layout);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_title.setText(title);
        TextView tv_message = (TextView) dialog.findViewById(R.id.tv_message);
        tv_message.setText(message);

        //设置只能输入数字和小数点
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //监听按钮
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickISOEdit != null) {
                    onclickISOEdit.onClick(editText.getText().toString());
                }
                //关闭软键盘
                hintKbOne(editText, context);
                finishAnimator(rl_layout);

            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭软键盘
                hintKbOne(editText, context);
                finishAnimator(rl_layout);
            }
        });
        startAnimator(rl_layout);
        dialog.show();
    }

    //隐藏软键盘
    public void hintKbOne(EditText etFind, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(etFind.getWindowToken(), 0);
    }


    //回调
    public interface OnclickISOEdit {
        void onClick(String content);
    }

    public static OnclickISOEdit onclickISOEdit;

    public void setOnclickISOEdit(OnclickISOEdit onclickISOEdit) {
        DialogUtils.onclickISOEdit = onclickISOEdit;
    }


    /**
     * 常用选择dialog
     */
    public TextView tv_yes;

    public void shownIosDialog(Context context, String title, String content) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_ios_hint_layout);

        //获取控件
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_layout);
        tv_yes = (TextView) dialog.findViewById(R.id.tv_submit);
        TextView tv_no = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tv_phone = (TextView) dialog.findViewById(R.id.tv_phone);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_phone.setText(content);

        //监听按钮
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickISO != null) {
                    onclickISO.onClick("");
                }
                finishAnimator(rl_layout);
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAnimator(rl_layout);
            }
        });

        startAnimator(rl_layout);
        dialog.show();

    }

    //回调
    public interface OnclickISO {
        void onClick(String content);
    }

    public static OnclickISO onclickISO;

    public void setOnclickISO(OnclickISO onclickISO) {
        DialogUtils.onclickISO = onclickISO;
    }


    /**
     * 选择性别 对话框
     */
    public TextView tv_yes_sex;
    public RadioButton rb_man, rb_girl;
    public String sex = "男";

    public void shownSexDialog(Context context) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_sex_layout);

        //获取控件
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_layout);
        tv_yes_sex = (TextView) dialog.findViewById(R.id.tv_submit);
        rb_man = (RadioButton) dialog.findViewById(R.id.rb_man);
        rb_girl = (RadioButton) dialog.findViewById(R.id.rb_girl);

        //监听按钮
        tv_yes_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickSex != null) {
                    if (rb_man.isChecked()) {
                        sex = "男";
                    } else {
                        sex = "女";
                    }
                    onclickSex.onClick(sex);
                }
                finishAnimator(rl_layout);
            }
        });


        startAnimator(rl_layout);
        dialog.show();

    }

    //回调
    public interface OnclickSex {
        void onClick(String content);
    }

    public static OnclickSex onclickSex;

    public static void setOnclickSex(OnclickSex onclickSex) {
        DialogUtils.onclickSex = onclickSex;
    }


    /**
     * 绑定号码 对话框
     */
    public TextView tv_yes_Binding;

    public void shownBindingDialog(Context context) {
        dialog = new Dialog(context, R.style.CursorDialogNotFloatTheme);
        dialog.setContentView(R.layout.dialog_binding_layout);

        //获取控件
        final RelativeLayout rl_layout = (RelativeLayout) dialog.findViewById(R.id.rl_layout);
        tv_yes_Binding = (TextView) dialog.findViewById(R.id.tv_submit);

        ImageView imageView = (ImageView) dialog.findViewById(R.id.iv_delete);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAnimator(rl_layout);
            }
        });

        //监听按钮
        tv_yes_Binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickBinding != null) {
                    onclickBinding.onClickBinding();
                }
                finishAnimator(rl_layout);
            }
        });


        startAnimator(rl_layout);
        dialog.show();

    }

    //回调
    public interface OnclickBinding {
        void onClickBinding();
    }

    public static OnclickBinding onclickBinding;

    public static void setOnclickBinding(OnclickBinding onclickBinding) {
        DialogUtils.onclickBinding = onclickBinding;
    }

    /**
     * 动画
     */
    private void startAnimator(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(400);
        animationSet.addAnimation(aa);
        view.startAnimation(animationSet);

    }

    public void finishAnimator(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation aa = new AlphaAnimation(1, 0);
        aa.setDuration(400);
        animationSet.addAnimation(aa);
        view.startAnimation(animationSet);
        //监听动画 关闭对话框
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismissDialog();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}
