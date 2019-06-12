package cn.dalelegamalek.demo.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.simonlee.demo.xcodescanner.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class charge_Point extends AppCompatActivity {
    Intent intent;
    int id,point;
    private SharedPreferences sharedpref;
    Call<ResponseBody> call = null;
    private apiinterface_home apiinterface;
    private SharedPreferences.Editor edt;
    int mypoint ,id_send;
    AppCompatButton a,b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge__point);
        intent=getIntent();
        sharedpref= getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        id_send=sharedpref.getInt("id",0);
        mypoint=sharedpref.getInt("points",0);
        edt = sharedpref.edit();
        id=intent.getIntExtra("id",0);
        point=intent.getIntExtra("point",0);
        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mypoint<10){
                    Toast.makeText(charge_Point.this,"نقاطك كامقدم خدمة غير كافية لشحن الباقة" , Toast.LENGTH_LONG).show();
                }
                else{
               fetchInfo(10);}
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mypoint<20){
                    Toast.makeText(charge_Point.this,"نقاطك كامقدم خدمة غير كافية لشحن الباقة" , Toast.LENGTH_LONG).show();
                }
                else{
                    fetchInfo(20);}
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mypoint<50){
                    Toast.makeText(charge_Point.this,"نقاطك كامقدم خدمة غير كافية لشحن الباقة" , Toast.LENGTH_LONG).show();
                }
                else{
                    fetchInfo(50);}
            }
        });
    }
    public void fetchInfo(final int pointt) {

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_send_(id,id_send,pointt);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(charge_Point.this);
                dlgAlert.setMessage("تم شحن الباقة بنجاح");
                dlgAlert.setTitle("دللي جمالك");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                edt.putInt("points",(mypoint-pointt));
                edt.apply();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(charge_Point.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
