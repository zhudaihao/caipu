package net;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.rest.JsonObjectRequest;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.StringRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import baseBean.RequestEntity;
import encoder.AESCrypt;
import utils.Util;


/**
 * 访问网络的控制
 */

public class NetClient {
    public HttpResponseListener responseListener;
    public static int OS_TYPE = 2;

    /**
     * 请求队列.
     */
    private RequestQueue requestQueue;
    private StringRequest request;
    private StringRequest stringRequest;
    private JsonObjectRequest jsonObjectRequest;

    private AESCrypt aesCrypt;
    private Context context;
    protected HashMap<String, Object> params = null;
    private HttpListener listener;
    private DownloadQueue downloadQueue;
    private DownloadRequest downloadRequest;

    public NetClient(RequestQueue requestQueue, Context context, HttpListener<String> listener) {
        super();
        try {
            aesCrypt = new AESCrypt();
        } catch (Exception e) {
        }
        params = new HashMap<>();
        this.listener = listener;
        this.context = context;
        this.requestQueue = requestQueue;

    }

    //初始化公共参数
    public static int LANGUAGE = 1;

    private void initPublicParams() {
        params.put("osType", OS_TYPE);
        params.put("version", Util.getAppVersion(context));
        params.put("imei", Util.getIMEI(context));
        params.put("language", LANGUAGE);
    }


    /**
     * _______________________________________网络请求方式___________________________________________
     */
    protected void get(String url, String requestWhat, boolean isShownLoad) {
        //创建get请求对象
        initPublicParams();
        request = (StringRequest) NoHttp.createStringRequest(url, RequestMethod.GET);

        //同意添加请求标识
        params.put("requestWhat", requestWhat);
        //添加请求参数
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();
            if (val instanceof String) {
                request.add(key, (String) val);
            } else if (val instanceof Integer) {
                request.add(key, (int) val);
            }

        }

        try {
            //添加签名
            request.add("signture", Util.getSignature(Util.mapToString(params), "4DFF2D1F474B3215FEA02799774DB76A"));
        } catch (IOException e) {
        }

        responseListener = new HttpResponseListener(context, request, listener, true, isShownLoad);

        // responseListener = new HttpResponseListener< >(context, request, listener, true, isshowloading);
        requestQueue.add(0, request, responseListener);
    }


    protected void get(String url, String requestWhat) {
        //创建get请求对象
        initPublicParams();
        request = (StringRequest) NoHttp.createStringRequest(url, RequestMethod.GET);

        //同意添加请求标识
        params.put("requestWhat", requestWhat);
        // 添加请求参数
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();
            if (val instanceof String) {
                request.add(key, (String) val);
            } else if (val instanceof Integer) {
                request.add(key, (int) val);
            }

        }

        try {
            //添加签名
            request.add("signture", Util.getSignature(Util.mapToString(params), "4DFF2D1F474B3215FEA02799774DB76A"));
        } catch (IOException e) {
        }


        responseListener = new HttpResponseListener<>(context, request, listener, true, true);
        requestQueue.add(0, request, responseListener);
    }


    protected void get(String url) {
        //创建get请求对象
        initPublicParams();
        request = (StringRequest) NoHttp.createStringRequest(url, RequestMethod.GET);

        //同意添加请求标识
//        params.put("requestWhat", requestWhat);
        // 添加请求参数
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();
            if (val instanceof String) {
                request.add(key, (String) val);
            } else if (val instanceof Integer) {
                request.add(key, (int) val);
            }

        }

//        try {
//            //添加签名
//            request.add("signture", Util.getSignature(Util.mapToString(params), "4DFF2D1F474B3215FEA02799774DB76A"));
//        } catch (IOException e) {
//        }


        responseListener = new HttpResponseListener<>(context, request, listener, true, true);
        requestQueue.add(0, request, responseListener);
    }

    protected void post(HashMap<String, Object> param, String requestWhat, String url, boolean isEncryption, boolean isShownLoad) {
        //初始化公共参数
        jsonObjectRequest = (JsonObjectRequest) NoHttp.createJsonObjectRequest(url, RequestMethod.POST);
        RequestEntity body = new RequestEntity();
//        RequestPublic common = new RequestPublic();

//        common.getClient().setImei(Util.getIMEI(context));
//        common.getClient().setOsType(OS_TYPE);
//        common.getClient().setVersion(Util.getAppVersion(context) + "");
//        common.setLanguage(LANGUAGE + "");
//        body.setCommon(common);
//        body.setRequestWhat(requestWhat);
        //签名
//        try {
//            String sigh = Util.getSignature(Util.mapToString(param), "4DFF2D1F474B3215FEA02799774DB76A");
//            param.put("signture", sigh);
//        } catch (IOException e) {
//            //e.printStackTrace();
//        }
        //  对data加密
        String data = "";
        String key = "";

//        if (isEncryption) {
//            try {
//                //随机密钥
//                key = DesUtil.getKeyString();
//                //DES加密data
//                //  LogUtils.e("加密前key>>data" + key + ">>>>" + JSON.toJSONString(param, true));
//                data = DesUtil.encrypt(JSON.toJSONString(param, true), key);
//                body.setData(data);
//                //AES加密DES的密钥
//                body.setSecurity(aesCrypt.encrypt(key));
//            } catch (Exception e) {
//                //e.printStackTrace();
//            }
//        } else {
//            data = JSON.toJSONString(param, true);
//            body.setData(data);
//        }
        data = JSON.toJSONString(param, true);
        body.setData(data);

        jsonObjectRequest.setDefineRequestBodyForJson(JSON.toJSONString(body));
        //加密加密私钥上传服务器
        responseListener = new HttpResponseListener<>(context, jsonObjectRequest, listener, true, isShownLoad);
        requestQueue.add(0, jsonObjectRequest, responseListener);
    }

    //上传
    protected void upLoad(String token, FileBinary file, String requestWhat, String url, boolean isShownLoad) {
        //初始化公共参数
        stringRequest = (StringRequest) NoHttp.createStringRequest(url, RequestMethod.POST);
        stringRequest.add("source", 2 + "");
        stringRequest.add("token", token);
        stringRequest.add("headImg", file);
        stringRequest.add("requestWhat", requestWhat);
        //加密加密私钥上传服务器
        responseListener = new HttpResponseListener<>(context, stringRequest, listener, true, isShownLoad);
        requestQueue.add(0, stringRequest, responseListener);

    }

    //下载
    protected void download(String url, String requestWhat, boolean isRange, boolean isDeleteOld, DownloadListener downloadListener) {
        downloadQueue = NoHttp.newDownloadQueue(1);
        //初始化公共参数
        downloadRequest = NoHttp.createDownloadRequest(url, Util.getSDPath(), "gxyclub.apk", isRange, isDeleteOld);

        //加密加密私钥上传服务器
        //  SSLContext sslContext = SSLContextUtil.getSSLContext();
        //if (sslContext != null) {
        // downloadRequest.setSSLSocketFactory(sslContext.getSocketFactory());
        //   }

        stringRequest.add("requestWhat", requestWhat);
        responseListener = new HttpResponseListener<>(context, stringRequest, listener, true, false);
        downloadQueue.add(0, downloadRequest, downloadListener);
    }


    /**
     *  ----------------------------------------------- 请求部分 --------------------------------------------------
     */


    /**
     * 版本检测
     */
    public void checkUpdate() {
        get(NetRequest.UPDATE, RequestWhat.UPDATE, false);

    }

    /**
     * 手势密码开关修改
     */
    public void isLock(int graphPwdSwith, String userName, boolean isLogin) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("graphPwdSwith", graphPwdSwith);
        param.put("userName", userName);
        post(param, RequestWhat.IS_LOCK, NetRequest.IS_LOCK, true, isLogin);
    }


    /**
     * 初始化数据
     */
    public void home(String userName) {
        params.put("userName", userName);
        get(NetRequest.HOME, RequestWhat.HOME, false);
    }


    /**
     * 网站公告
     */
    public void getNotice(int pages) {
        params.put("pageNum", pages);
        get(NetRequest.NOTICE, RequestWhat.NOTICE);
    }


    /**
     * 个人信息
     */
    public void getUser(int userId) {
        params.put("userId", userId);
        get(NetRequest.USER, RequestWhat.USER);
        // LogUtils.g("-----* 个人信息------" + NetRequest.SINGLEGUSS + params + RequestWhat.SINGGUSS);
    }

    /**
     * 领取任务 奖励
     */
    public void getTask(int userId, int taskType) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("taskType", taskType);

        post(hashMap, RequestWhat.TASK, NetRequest.TASK, true, false);
    }


    /**
     * 登录
     */
    public void login(HashMap param) {
        post(param ,RequestWhat.LOGIN,NetRequest.LOGIN, false, false);
    }

    /**
     * 获取短信
     */
    public void getCode(String phone, int type) {
        params.put("phone", phone);
        params.put("type", type);
        get(NetRequest.CODE, RequestWhat.CODE);


    }

    public void getCode(String key) {
        params.put("key",key);
        get(NetRequest.Code);


    }


    /**
     * 忘记密码
     * phone	string	是	电话
     * captcha	string	是	短信验证码
     * newPwd	string	是	新登录密码
     */
    public void forgetPwd(String phone, String captcha, String newPwd) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("phone", phone);
        hashMap.put("captcha", captcha);
        hashMap.put("newPwd", newPwd);
        post(hashMap, RequestWhat.FORGETPWD, NetRequest.FORGETPWD, true, false);

    }


    /**
     * 注册
     */
    public void register(HashMap param) {
        post(param, RequestWhat.REGISTER, NetRequest.REGISTER, true, false);
    }


    /**
     * 修改登录密码
     */
    public void changePwd(HashMap<String, Object> param) {
        post(param, RequestWhat.CHANGEPWD, NetRequest.CHANGEPWD, true, false);
    }


}
