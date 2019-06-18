package cn.dalelegamalek.demo.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.contact_home;
import cn.simonlee.demo.xcodescanner.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    ImageView i;
    private List<contact_home> contactList;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        i = (ImageView) findViewById(R.id.imageView);
        i.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(sharedpref.getString("remember","").trim().equals("yes")){
                    String phone=sharedpref.getString("phone","").trim();
                    String pass=sharedpref.getString("password","").trim();
                    fetchInfo2(phone,pass);
                    //  startActivity(new Intent(LoginActivity.this,SampleActivity.class));
                  // finish();
                }
                else{
               startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }}

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    public void fetchInfo2(String phone,String pass){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_home>> call= apiinterface.getcontacts_login(phone,
                pass);
        call.enqueue(new Callback<List<contact_home>>() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onResponse(Call<List<contact_home>> call, Response<List<contact_home>> response) {
                try {


                    if(response.isSuccessful()){

                        contactList = response.body();
                        edt.putInt("id",contactList.get(0).getId());
                        edt.putString("name",contactList.get(0).getName());
                        edt.putString("phone",contactList.get(0).getPhone());
                        edt.putString("address",contactList.get(0).getAddress());
                        edt.putString("password",contactList.get(0).getPassword());
                        edt.putInt("points",contactList.get(0).getPoints());
                        edt.putString("email",contactList.get(0).getEmail());
                        edt.putString("image",contactList.get(0).getImage());
                        edt.putInt("category",contactList.get(0).getCategory());
                        edt.putString("age", String.valueOf(contactList.get(0).getDate()));
                        edt.putString("remember","yes");
                        edt.apply();

                        startActivity(new Intent(SplashActivity.this, SampleActivity.class));

                    }
                }catch (Exception e){
                    Toast.makeText(SplashActivity.this,"لم يتم تأكيد الحساب بعد ",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<contact_home>> call, Throwable t) {
                Toast.makeText(SplashActivity.this,"لم يتم تأكيد الحساب بعد ",Toast.LENGTH_LONG).show();


            }
        });
    }
}