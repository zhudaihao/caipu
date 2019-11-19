package cn.zdh.picturelibrary;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

/**
 * 底部弹出 PopupWindow
 */

public class PopupShow {
    private PopupShow() {
    }

    private static final PopupShow popupShow = new PopupShow();

    public static final PopupShow getInstance() {

        return popupShow;
    }

    /**
     * 底部对话框
     */

    public RadioButton rb_two, rb_one, rb_cancel;
    private PopupWindow popWindow;

    public void showPopWindowIcon(final Activity activity) {
        View parent = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        View view = LayoutInflater.from(activity).inflate(R.layout.pop_button_picture, null);

        popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置动画
        popWindow.setAnimationStyle(R.style.AnimBottom);
        //设置是否获取焦点
        popWindow.setFocusable(true);
        //设置点击外部是否关闭对话框
        popWindow.setOutsideTouchable(false);
        //设置背景颜色 透明#00000000  半透明30000000
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);

        //获取控件
        rb_one = (RadioButton) view.findViewById(R.id.rb_one);
        rb_cancel = (RadioButton) view.findViewById(R.id.rb_cancel);
        rb_two = (RadioButton) view.findViewById(R.id.rb_two);
        RelativeLayout rl_tag = (RelativeLayout) view.findViewById(R.id.rl_tag);


        //点击监听
        rl_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });

        rb_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissPopWindow();
            }
        });


        //点击相机
        rb_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOneClick != null) {
                    onOneClick.setOnOneClick();
                }
                popWindow.dismiss();
            }
        });

        //点击相册
        rb_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTwoClick != null) {
                    onTwoClick.setOnTwoClick();
                }
                popWindow.dismiss();
            }
        });


        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    /**
     * 点击相册 和 相机 回调
     */
    //相机
    public interface OnOneClick {
        void setOnOneClick();
    }

    private static OnOneClick onOneClick;

    public static void setOnOneClick(OnOneClick onOneClick) {
        PopupShow.onOneClick = onOneClick;
    }

    //相册
    public interface OnTwoClick {
        void setOnTwoClick();
    }

    private static OnTwoClick onTwoClick;

    public static void setOnTwoClick(OnTwoClick onTwoClick) {
        PopupShow.onTwoClick = onTwoClick;
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
}
