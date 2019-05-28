package cn.dalelegamalek.demo.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient_home {
    //http://jamalah.com/
    private static final String url="http://192.168.1.12:8080/";

    private static Retrofit retrofit =null;
    public static Retrofit getapiClient(){
        if(retrofit== null){
            retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
