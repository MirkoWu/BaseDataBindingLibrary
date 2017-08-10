package com.softgarden.basedatabindinglibrary.network;

import android.support.annotation.NonNull;

import com.softgarden.basedatabindinglibrary.BuildConfig;
import com.softgarden.basedatabindinglibrary.app.Constants;
import com.softgarden.baselibrary.utils.L;
import com.softgarden.baselibrary.utils.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2016/10/10.
 * 参数格式为Json格式的拦截器
 */

public class ParameterInterceptor implements Interceptor {
    private static final String TAG = ParameterInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        RequestBody requestBody = makeRequestBody(oldRequest);
        Request newRequest = oldRequest.newBuilder().post(requestBody).build();
        Response response = chain.proceed(newRequest);
        //打印返回数据
        if (BuildConfig.DEBUG) {
            ResponseBody resultBody = response.body();
            String result = resultBody.string();
            try {
                L.json(result);
            } catch (Exception e) {
                L.e(result);
            }

         /*   * 因为调用ResponseBody.string()后即关闭，后续无法获取内容 */
            response = response.newBuilder()
                    .body(ResponseBody.create(resultBody.contentType(), result))
                    .build();
        }
        return response;
    }

    @NonNull
    private RequestBody makeRequestBody(Request oldRequest) {
        HttpUrl oldUrl = oldRequest.url();
        FormBody.Builder newBodyBuilder = new FormBody.Builder();
        JSONObject data = new JSONObject();
        try {

            /** 链接上的参数 */
//        for (int i = oldUrl.querySize() - 1; i >= 0; i--) {
//            String name = oldUrl.queryParameterName(i);
//            String value = oldUrl.queryParameterValue(i);
//            data.put(name, value);
//        }

//            String userID = SP.getUserID();
//            if(!TextUtils.isEmpty(userID)){
//                data.put("token", SP.getToken());
//                data.put("user_id", userID);
//            }


            /** Body上的参数 */
            //这里要判断下  不然参数为空时会classCastException
            if (oldRequest.body() instanceof FormBody) {
                FormBody body = (FormBody) oldRequest.body();
                if (body != null)
                    for (int i = body.size() - 1; i >= 0; i--) {
                        String name = body.name(i);
                        String value = body.value(i);
                        data.put(name, value);
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /** * 添加Sign参数 */
        newBodyBuilder.add("data", data.toString());
        newBodyBuilder.add("apisign", MD5Util.ToMD5(Constants.MD5_KEY, data.toString()));
        L.d("请求地址RequestUrl=====", oldUrl.url().toString());
        L.d("请求参数Params=========", data.toString());//打印请求log

        return newBodyBuilder.build();
    }


}
