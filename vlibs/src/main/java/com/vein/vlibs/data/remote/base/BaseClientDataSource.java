package com.vein.vlibs.data.remote.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vein.vlibs.data.remote.interceptor.ClientRequestInterceptor;
import com.vein.vlibs.utils.JResource;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BaseDiscuzDataSource
 * Created by 董旭 16/5/20 下午11:20
 * Copyright (c) 2014 小云社群. All rights reserved
 */
public abstract class BaseClientDataSource {

    protected String TAG = getClass().getSimpleName();

    protected JResource resource;

    public BaseClientDataSource() {

//        resource = JResource.getInstance(DemoApplication.getContext());

        ClientRequestInterceptor interceptor = new ClientRequestInterceptor();

        //加拦截方法，增加必要参数
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //重写解析
        Gson customGsonInstance = new GsonBuilder().create();

//        Gson customGsonInstance = new GsonBuilder()
//                .registerTypeAdapter(new TypeToken<List<String>>() {
//                        }.getType(),
//                        new ClientJSONSerializer())
//                .create();



        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://phptest.luckall.cn/")
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client);

        Retrofit retrofit = builder.build();

        initDataSource(retrofit);
    }

    protected abstract void initDataSource(Retrofit retrofit);


    public class ClientJSONSerializer implements JsonSerializer<String> {
        public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

}
