package gxyclub.ui.activity.welcomeActivity;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gxyclub.R;
import cn.star.lockpattern.util.LockPatternUtil;
import cn.star.lockpattern.widget.LockPatternIndicator;
import cn.star.lockpattern.widget.LockPatternView;
import base.BaseActivity;

import gxyclub.bean.LoginBean;
import gxyclub.config.MyApp;
import gxyclub.ui.activity.loginActivity.LoginActivity;
import utils.SPUtils;
import utils.Util;


/**
 * 手势登录
 */

public class GestureLoginActivity extends BaseActivity {
    @BindView(R.id.lockPatternView)
    LockPatternView lockPatternView;
    @BindView(R.id.forgetGestureBtn)
    Button forgetGestureBtn;
    @BindView(R.id.loginlockPatterIndicator)
    LockPatternIndicator indicator;
    @BindView(R.id.messageTv)
    TextView messageTv;

    private String password;
    private List<LockPatternView.Cell> cellList = null;

    @Override
    public int getResLayout() {
        return R.layout.activity_gesture_login;
    }

    @Override
    protected void initView() {
        lockPatternView.setOnPatternListener(patternListener);
    }

    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            if (cellList != null) {
                cellList.clear();
                indicator.setDefaultIndicator();
            }
            lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if (pattern != null) {
                if (pattern.size() < 4) {
                    updateStatus(Status.LESSERROR);
                } else {
                    byte[] bytes = LockPatternUtil.patternToHash(pattern);
                    password = Util.getPasswordFromBytes(bytes);
                    cellList = pattern;
                    indicator.setIndicator(cellList);
                    loginGestureSuccess();
                }
            }
        }
    };


    /**
     * 请求登录接口
     */
    private void loginGestureSuccess() {
        String userName = (String) SPUtils.get(this, "userName", "");
        try {
            userName = aesCrypt.decrypt(userName);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (userName != null && !userName.equals("")) {
            HashMap<String, Object> param = new HashMap<>();
            param.put("userName", userName);
            //密码加密
            String pwd = Util.setBase64(password);
            param.put("graphPwd", pwd);
            //encrypt	number	否	登录密码是否base64加密：0-否,1-是（后续版本必传）
            param.put("encrypt", 1);
            getNetClient().login(param);
        }
    }


    /**
     * 接口回调
     */
    //成功
    @Override
    public void onSuccessful(String requestWhat, Object data, baseBean.ResponsePagesEntity page) {
        super.onSuccessful(requestWhat, data, page);
        updateStatus(Status.CORRECT);

        //登录数据 保存
        LoginBean loginBean = JSON.parseObject(data.toString(), LoginBean.class);
        MyApp.getInstance().setLoginBean(loginBean);
        MyApp.getInstance().setLogin(true);
    }

    //失败
    @Override
    public void onFailure(String requestWhat, Object data) {
        super.onFailure(requestWhat, data);
        updateStatus(Status.ERROR);
    }

    /**
     * 手势触摸
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getAction()) {
            case KeyEvent.KEYCODE_BACK:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.BUTTON_BACK:
                break;
        }

        return false;
    }


    /**
     * 更新状态
     */

    //状态
    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_default, R.color.grey_a5a5a5),
        //密码输入错误
        ERROR(R.string.gesture_error, R.color.red_f4333c),
        //至少链接4个点
        LESSERROR(R.string.create_gesture_less_error, R.color.red_f4333c),

        //密码输入正确
        CORRECT(R.string.gesture_correct, R.color.grey_a5a5a5);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }

    private void updateStatus(Status status) {
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                messageTv.setText("绘制解锁图案");
                messageTv.setTextColor(getResources().getColor(R.color.color_66));
                break;
            case LESSERROR:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                messageTv.setText("至少连接4个点，请重新输入");
                messageTv.setTextColor(getResources().getColor(R.color.red));
                break;
            case CORRECT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);

                break;
        }
    }

    /**
     * 点击监听
     * 忘记手势密码（去账号登录界面）
     */
    @OnClick(R.id.forgetGestureBtn)
    void forgetGesturePassword() {
        startToActivity(this, LoginActivity.class, true);
    }


}
