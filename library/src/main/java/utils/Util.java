package utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import encoder.BASE64Encoder;


/**
 * 工具类
 */
public class Util {

    //倒计时的时间
    public static final int TIMER_TASK = 180;

    public static String getSignature(String baseString, String secret) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        StringBuffer sb = new StringBuffer();
        sb.append(baseString);
        sb.append(secret);

        // 使用MD5对待签名串求签
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(sb.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }

        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }


    public static String mapToString(HashMap<String, Object> map) {
        StringBuffer baseString = new StringBuffer();
        List<Map.Entry<String, Object>> infoIds = new ArrayList<>(map.entrySet());

        Comparator<? super Map.Entry<String, Object>> text = new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> stringObjectEntry, Map.Entry<String, Object> t1) {
                int date = (stringObjectEntry.getKey()).toString().compareTo(t1.getKey().toString());
                return date;
            }
        };
        Collections.sort(infoIds, text);
        for (int i = 0; i < infoIds.size(); i++) {
            baseString.append(infoIds.get(i).toString());
        }

        return baseString.toString();
    }


    /**
     * Base64 加密
     */
    public static String setBase64(String password) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] pass = new byte[0];
        try {
            pass = password.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Encoder.encode(pass);
    }

    /**
     * 非空判断
     */
    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取多少天前的日期
     */
    @SuppressLint("SimpleDateFormat")
    public static String paseDate(long time) {
        if (time == 0) {
            return "";
        }
        Date date;
        date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取多少天前的日期
     */
    @SuppressLint("SimpleDateFormat")
    public static String paseDate(long time, boolean isSeconds) {
        SimpleDateFormat format = null;
        Date date;
        if (isSeconds) {
            date = new Date(time);
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            date = new Date(time);
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        return format.format(date);
    }


    //当前版本（显示给用户看的版本号）
    public static String getVersionName(Context c) {
        String version = "";
        // 获取packagemanager的实例
        PackageManager packageManager = c.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(c.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
        }
        return version;
    }

    //获取版本号
    public static int getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (Exception e) {
            //e.printStackTrace();
            return 1;
        }
    }

    //获取手机型号
    public static String getIMEI(Context context) {
        return android.os.Build.MODEL;
    }


    //byte转string
    public static String getPasswordFromBytes(byte[] data) {
        StringBuffer str = new StringBuffer();
        if (data != null && data.length != 0) {
            for (int i = 0; i < data.length; i++) {
                str.append(data[i]);
                if (i < data.length - 1)
                    str.append(",");
            }
        }
        return str.toString();
    }

    /**
     * 获取图片路径
     */
    public static String getSDPath() {
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            return Environment.getExternalStorageDirectory().toString() + "/images";
        } else
            return "/data/data/cn.gxy/images";
    }


    /**
     * editText监听处理
     */
    public static void setEditText(final EditText editText, final ImageView ivClear) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    ivClear.setVisibility(View.INVISIBLE);
                }
            }
        });


        //监听焦点
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && editText.getText().toString().trim().length() != 0) {
                    //获取焦点
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    //失去焦点
                    ivClear.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * 时间
     */

    public static String getTimeNew(Date date) {
        //可根据需要自行截取数据显示
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年");
        return format.format(date);
    }

}
