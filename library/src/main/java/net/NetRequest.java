package net;

public class NetRequest {
    //测试环境
    public static String SERVER = "http://148.70.103.12/LiDium/public/api.php/";

    //线上环境
    //public static String SERVER = "https://cms-app.gxyclub.com";


    public static final String LOGIN = "http://route.showapi.com/950-1";//登录




    public static final String Code = "http://apicloud.mob.com/v1/cook/category/query";//验证码


    public static final String UPDATE = SERVER + "/system/v1/nosecurity/notoken/latestVersion";//版本检测 升级
    public static final String IS_LOCK = SERVER + "/personal/v1/notoken/graphPwdSwith";//手势密码开关修改

    public static final String HOME = SERVER + "/home/v1/nosecurity/notoken";//初始化
    public static final String NOTICE = SERVER + "/about/v1/nosecurity/notoken/articles";//网站公告


    public static final String USER = SERVER + "/user/v1/nosecurity/notoken/getUserById";//用户个人信息
    public static final String TASK = SERVER + "/task/v1/notoken/gainAward";//任务奖励
    public static final String CODE = SERVER + "/system/v1/nosecurity/notoken/smsCaptcha";//获取短信
    public static final String FORGETPWD = SERVER + "/user/v1/notoken/forgetPwd";//忘记密码
    public static final String REGISTER = SERVER + "/user/v1/notoken/register";//注册
    public static final String CHANGEPWD = SERVER + "/user/v1/notoken/updatePwd";//修改登录密码


}