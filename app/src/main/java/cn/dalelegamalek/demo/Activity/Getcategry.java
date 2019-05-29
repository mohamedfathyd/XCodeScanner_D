package cn.dalelegamalek.demo.Activity;

import android.content.SharedPreferences;

import java.util.List;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.content_category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Getcategry {
    private List<content_category> contactList;
    private apiinterface_home apiinterface;

    public void GfetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<content_category>> call = apiinterface.getcontacts_allfirst();
        call.enqueue(new Callback<List<content_category>>() {
            @Override
            public void onResponse(Call<List<content_category>> call, Response<List<content_category>> response) {
                contactList = response.body();

                if(!contactList.isEmpty()|| contactList.equals(null)){

                }


            }

            @Override
            public void onFailure(Call<List<content_category>> call, Throwable t) {

            }
        });

    }
}
