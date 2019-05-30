package cn.dalelegamalek.demo.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.contact_home;
import cn.simonlee.demo.xcodescanner.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AfterScan extends AppCompatActivity {
Intent intent;
Button delete,add;
String id;
    Toolbar toolbar;
int idd;
TextView name, phone, point;
    private List<contact_home> contactList;
    private apiinterface_home apiinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_scan);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        point=findViewById(R.id.point);
        delete=findViewById(R.id.delete_order);
        add=findViewById(R.id.add_order);
        intent=getIntent();
        id=intent.getStringExtra("result");
        idd= Integer.parseInt(id);
        this.setTitle("");
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_right_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
        fetchInfo();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(AfterScan.this,Get_points.class);
                 intent.putExtra("point",Integer.parseInt(point.getText().toString()));
                 intent.putExtra("id",idd);
                 startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AfterScan.this,charge_Point.class);
                intent.putExtra("point",Integer.parseInt(point.getText().toString()));
                intent.putExtra("id",idd);
                startActivity(intent);

            }
        });
    }
    public void fetchInfo(){

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_home>> call= apiinterface.getcontacts_getuser(idd);
        call.enqueue(new Callback<List<contact_home>>() {

            @Override
            public void onResponse(Call<List<contact_home>> call, Response<List<contact_home>> response) {
try {
    if (response.isSuccessful()) {

        contactList = response.body();
        name.setText(contactList.get(0).getName());
        phone.setText(contactList.get(0).getPhone());
        point.setText(contactList.get(0).getPoints() + "");
    }
}catch (Exception e){
    Toast.makeText(AfterScan.this,"هناك خطأ حدث اعد المحاولة لاحقا ",Toast.LENGTH_LONG).show();

}
            }

            @Override
            public void onFailure(Call<List<contact_home>> call, Throwable t) {
                Toast.makeText(AfterScan.this,"هناك خطأ حدث اعد المحاولة لاحقا ",Toast.LENGTH_LONG).show();


            }
        });
    }
}
