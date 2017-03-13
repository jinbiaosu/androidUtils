package com.vein.vlibs.data.remote.interceptor;

import com.vein.vlibs.utils.JLog;
import com.vein.vlibs.utils.JResource;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * DiscuzRequestInterceptor
 * Created by 董旭 16/5/18 下午8:04
 * Copyright (c) 2014 小云社群. All rights reserved
 */
public class ClientRequestInterceptor_temp implements Interceptor {

	private Locale mLocale;
	private JResource mResource;
    public ClientRequestInterceptor_temp() {
//    	mLocale = DemoApplication.getContext().getResources().getConfiguration().locale;
//    	mResource = JResource.getInstance(DemoApplication.getContext());
    }

    @Override public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        Buffer sink = new Buffer();
        oldRequest.body().writeTo(sink);
        String content = sink.readUtf8();
        String publicKey = null;
    	//获取publicKey
    	try {
//    		publicKey = getPublicKey(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

//        SQJniUtils sq = new SQJniUtils();
//        String headerString = "XiaoYun "+sq.encrypt(SharedPreferencesDB.getInstance(DiscuzApplication.getContext()).getForumKey(),content,publicKey);
		String headerString="";
        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .header("Authorization",headerString)
                .header("Accept-Language", mLocale.getLanguage() + "-" + mLocale.getCountry())
                .build();
        JLog.i("SQHttp",""+newRequest.url());
        return chain.proceed(newRequest);
    }

//    public String getPublicKey(String content) throws Exception{
//
//    	SharedPreferencesDB db = SharedPreferencesDB.getInstance(DiscuzApplication.getContext());
//
//    	if(TextUtils.isEmpty(db.getString("client_publickey"))){
//    		OkHttpClient client = new OkHttpClient.Builder()
//            		.connectTimeout(3, TimeUnit.SECONDS)
//            		.readTimeout(3, TimeUnit.SECONDS)
//                    .build();
//        	RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; encoding=utf-8"), content);
//            SQJniUtils sq = new SQJniUtils();
//            String headerString = "XiaoYun "+sq.encrypt(SharedPreferencesDB.getInstance(DiscuzApplication.getContext()).getForumKey(),content,null);
//
//        	Request request =new Request.Builder().url(mResource.getString("mc_forum_clientapi_url")+"v1/rsa/publicKey")
//        			.header("Accept-Language", mLocale.getLanguage() + "-" + mLocale.getCountry())
//        			.addHeader("Authorization",headerString)
//        			.post(requestBody)
//        			.build();
//
//    		Response response = client.newCall(request).execute();
//    		String responseString = response.body().string();
//    		DZLogUtil.i("client_publickey", responseString);
//    		JSONObject root = new JSONObject(responseString);
//    		JSONObject result = root.optJSONObject("result");
//    		if(result != null){
//    			String pkey = result.optString("publicKey");
//    			if(!TextUtils.isEmpty(pkey)){
//    				SharedPreferencesDB.getInstance(DiscuzApplication.getContext()).saveString("client_publickey", pkey);
//    			}
//    			return pkey;
//    		}else{
//    			return null;
//    		}
//    	}else{
//    		return db.getString("client_publickey");
//    	}
//    }
}

