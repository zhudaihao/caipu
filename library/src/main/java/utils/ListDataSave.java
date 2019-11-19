package utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存集合到本地
 */

public class ListDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ListDataSave(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);//创建sp对象
        editor = preferences.edit();
    }

    /**
     * 保存List
     *
     * @param tag
     * @param dataList
     */
    public <T> void setDataList(String tag, List<T> dataList) {
        if (null == dataList || dataList.size() <= 0)
            return;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dataList); //将List转换成Json
        editor.putString(tag, jsonStr); //存入json串
        editor.commit();  //提交
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag) {
        List<T> dataList = new ArrayList<T>();

//
//        String strJson = preferences.getString(tag, "");
//        if (null == strJson) {
//            return dataList;
//        }
//        Gson gson = new Gson();
//        dataList = gson.fromJson(strJson, new TypeToken<List<T>>() {
//        }.getType());


//        //防空判断
//        if (tag != "") {
//            Gson gson = new Gson();
//            dataList = gson.fromJson(new TypeToken<List<T>>() {
//            }.getType(), T); //将json字符串转换成List集合
//        }




        //                SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List", Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
//                String peopleListJson = sp.getString("KEY_NewUserModel_LIST_DATA", "");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
//                if (peopleListJson != "")  //防空判断
//                {
//                    Gson gson = new Gson();
//                    saveList = gson.fromJson(peopleListJson, new TypeToken<List<PublishBean>>() {
//                    }.getType()); //将json字符串转换成List集合
//                }


        //获取保存的数据
        String peopleListJson = preferences.getString(tag, "");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
        //防空判断
        if (peopleListJson != "") {
            Gson gson = new Gson();
            dataList = gson.fromJson(peopleListJson, new TypeToken<List<T>>() {
            }.getType()); //将json字符串转换成List集合
        }

        return dataList;

    }


}
