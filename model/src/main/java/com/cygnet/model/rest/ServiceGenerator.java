package com.cygnet.model.rest;


import com.cygnet.framework.utils.Constants;
import com.cygnet.framework.utils.network.RequestInterceptor;
import com.cygnet.model.ModelApp;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;



/*
* This class will provide the Retrofit singleton method.
 * And Builder so don't need to create builder Every Java File.
*/

/**
 * Name : ServiceGenerator
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :
 * This class will provide the Retrofit singleton method.
 * And Builder so don't need to create builder Every Java File.
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ModelApp.getAppInstance().getJaksonConverter());


    /**
     * Name : ServiceGenerator createService
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose : This method will create service single time for our interface a
     * and later on we can call all the method.
     * @param serviceClass  : {@link RestApis} class.
     * @param <S>   : return {@link RestApis}
     * @return
     */
    public static <S> S createService(Class<S> serviceClass)
    {

        HashMap<String, String> mHeaders = new HashMap<>();
        mHeaders.put("Accept", "application/json");
        mHeaders.put("Content-Type", "application/json");
        httpClient.addInterceptor(new RequestInterceptor(mHeaders));

        OkHttpClient client = httpClient.build();

        // if you want to set time out.

        /*httpClient.connectTimeout(10, TimeUnit.MINUTES);
        httpClient.writeTimeout(10, TimeUnit.MINUTES);
        httpClient.readTimeout(10, TimeUnit.MINUTES);*/

        /*
         *  If you want to put headers in each requests. then do like this.
         */


        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
