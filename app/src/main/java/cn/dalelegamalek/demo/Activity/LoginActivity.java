package cn.dalelegamalek.demo.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.contact_home;
import cn.simonlee.demo.xcodescanner.R;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button takhaty;
    AppCompatButton Naccount;
    AppCompatButton login;
    TextView newaccount;
    EditText textInputEditTextphone,textInputEditTextpassword;
    TextInputLayout textInputLayoutphone,textInputLayoutpassword;
    private List<contact_home> contactList;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    String codee;

    //  login__ login__;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        newaccount=findViewById(R.id.link_signup);
        login=findViewById(R.id.btn_login);
        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegestrationActivty.class));
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchInfo();
            }
        });



        textInputEditTextphone=findViewById(R.id.input_email);
        textInputEditTextpassword=findViewById(R.id.input_password);





        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        if(sharedpref.getString("remember","").trim().equals("yes")){

            startActivity(new Intent(LoginActivity.this,SampleActivity.class));
            finish();
        }



    }
    public void fetchInfo(){
        progressDialog = ProgressDialog.show(LoginActivity.this,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_home>> call= apiinterface.getcontacts_login(textInputEditTextphone.getText().toString(),
                textInputEditTextpassword.getText().toString());
        call.enqueue(new Callback<List<contact_home>>() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onResponse(Call<List<contact_home>> call, Response<List<contact_home>> response) {
                if(response.isSuccessful()){

                    contactList = response.body();

                    progressDialog.dismiss();
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
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(LoginActivity.this);
                    dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                    dlgAlert.setTitle("دللي جمالك");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    startActivity(new Intent(LoginActivity.this, SampleActivity.class));

                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<contact_home>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"لم يتم تأكيد الحساب بعد ",Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });
    }
}
