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

public class Get_points extends AppCompatActivity {
    Intent intent;
    int id,point;
    private SharedPreferences sharedpref;
    Call<ResponseBody> call = null;
    private apiinterface_home apiinterface;
    private SharedPreferences.Editor edt;
    int mypoint ,id_send;
    AppCompatButton btn_login;
    EditText points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_points);
        intent=getIntent();
        sharedpref= getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
          id_send=sharedpref.getInt("id",0);
        mypoint=sharedpref.getInt("points",0);
        edt = sharedpref.edit();
        id=intent.getIntExtra("id",0);
        point=intent.getIntExtra("point",0);
        btn_login=findViewById(R.id.btn_login);
        points=findViewById(R.id.points);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(points.getText().toString())>point){
                    Toast.makeText(Get_points.this,"عفوا نقاط المستخدم غير كافيه للسحب" , Toast.LENGTH_LONG).show();
                }
                else{
         fetchInfo();
                }
            }
        });
    }
    public void fetchInfo() {

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_send(id,id_send, Integer.parseInt(points.getText().toString()));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Get_points.this);
                dlgAlert.setMessage("تم سحب النقاط بنجاح");
                dlgAlert.setTitle("دللي جمالك");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                int po= Integer.parseInt(points.getText().toString());
                edt.putInt("points",(mypoint+po));
                edt.apply();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Get_points.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
