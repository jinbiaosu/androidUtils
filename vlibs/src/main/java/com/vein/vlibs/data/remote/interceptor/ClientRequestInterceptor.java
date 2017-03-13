package com.vein.vlibs.data.remote.interceptor;

import com.vein.vlibs.utils.JLog;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DiscuzRequestInterceptor Created by 董旭 16/5/18 下午8:04 Copyright (c) 2014
 * 小云社群. All rights reserved
 */
public class ClientRequestInterceptor implements Interceptor {

//	private Context context;
//	private SharedPreferencesDB shareDB;

	public ClientRequestInterceptor() {
//		context = DemoApplication.getContext();
//		shareDB = SharedPreferencesDB.getInstance(context);
	}

	@Override
	public Response intercept(Chain chain) throws IOException {

//		String currentTime = System.currentTimeMillis() + "";
//		String packageName = context.getPackageName();
//		String appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();

		Request oldRequest = chain.request();

		// 添加新的参数
		HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder().scheme(oldRequest.url().scheme()).host(oldRequest.url().host());

		FormBody.Builder formEncodingBuilder = new FormBody.Builder();
		for (int i = 0; i < oldRequest.url().querySize(); i++) {
			formEncodingBuilder.add(oldRequest.url().queryParameterName(i), oldRequest.url().queryParameterValue(i));
		}
//		formEncodingBuilder.add(IMEI, DZPhoneUtil.getIMEI(context));
//		formEncodingBuilder.add(IMSI, DZPhoneUtil.getIMSI(context));
//		formEncodingBuilder.add(APPHASH, DZStringUtil.stringToMD5(currentTime.substring(0, 5) + AUTHKEY).substring(8, 16));
//		formEncodingBuilder.add(APP_NAME, appName);
//		formEncodingBuilder.add(PACKAGE_NAME, packageName);
//		formEncodingBuilder.add(SDK_TYPE, SDK_TYPE_VALUE);
//		formEncodingBuilder.add(FORUM_TYPE, 7 + "");
//		formEncodingBuilder.add(SDK_VERSION, SDK_VERSION_VALUE);
//		formEncodingBuilder.add(PLAT_TYPE, PLAT_TYPE_VALUE);
//		formEncodingBuilder.add(EGN_VERSION, APP_BUILD_VERSION);

		formEncodingBuilder.build();

		// 新的请求
		Request newRequest = oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body()).url(authorizedUrlBuilder.build())
				.post(formEncodingBuilder.build()).build();
		JLog.i("OkHttp", "==vein==" + newRequest.url());
		return chain.proceed(newRequest);
	}
}
