package cn.zdh.viewpagertextlibrary;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 字体轮播 工具类型
 */

public class ViewPagerTextUtils {

    private ViewPagerTextUtils() {
    }

    private static final ViewPagerTextUtils viewPagerTextUtils = new ViewPagerTextUtils();

    public static ViewPagerTextUtils getInstance() {

        return viewPagerTextUtils;
    }


    private TextSwitcher textSwitcher;
    private List<String> list;
    private int i = 0;//循环加载textSwitcher
    private int position = 0;//记录textSwitcher选择的position
    private ViewGroup parent;
    private Timer timer;

    public void setText(final Activity activity, TextSwitcher textSwitcher, final List<String> list) {
        this.list = list;
        this.textSwitcher = textSwitcher;
        parent = null;
        //初始化公告
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //这里为了照顾魅族手机，采用加载布局的方式加载，而不直接new一个
                return LayoutInflater.from(activity).inflate(R.layout.hometextsitcher_textview, parent);

            }
        });

        textSwitcher.setInAnimation(activity, R.anim.slide_in_bottom);
        textSwitcher.setOutAnimation(activity, R.anim.slide_out_top);

        if (list != null && list.size() != 0) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = handler.obtainMessage(1);
                    msg.arg1 = i % list.size();
                    handler.sendMessage(msg);

                }
            }, 0, 3000);
        }
    }


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (list.size() > 0) {
                textSwitcher.setText(list.get(i % list.size()));
                i++;
                position = msg.arg1;
                //公告
                textSwitcher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //此处填写公告点击事件
                        Log.e("agt","----------list--"+list.size()+"----------position--"+position);

                        if (onClickListener != null) {
                            onClickListener.onClick(position);
                        }

                    }
                });

            }
            return false;
        }
    });


    public interface OnClickListener {
        void onClick(int position);
    }

    private static OnClickListener onClickListener;

    public static void setOnClickListener(OnClickListener onClickListener) {
        ViewPagerTextUtils.onClickListener = onClickListener;
    }


    //销毁
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }

    }
}
