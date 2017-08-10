package com.softgarden.basedatabindinglibrary.network;

import com.softgarden.basedatabindinglibrary.app.Constants;
import com.softgarden.basedatabindinglibrary.network.api.HomeService;
import com.softgarden.baselibrary.utils.L;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class RetrofitManager {
    public volatile static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null)
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    retrofit = getRetrofit();
                }
            }
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(Constants.HOST_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)

                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true)
                .addInterceptor(new ParameterInterceptor());// 拦截器

        return builder.build();
    }

    public static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
        //打印retrofit日志
        L.w("RetrofitLog", message + "");
    }).setLevel(HttpLoggingInterceptor.Level.BODY);


    public static <T> T getAPIService(Class<T> service) {
        return getInstance().create(service);
    }


    public static HomeService getHomeService() {
        return getInstance().create(HomeService.class);
    }


}
