package cn.dalelegamalek.demo.Activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import cn.dalelegamalek.demo.FragmentImage;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.content_category;
import cn.simonlee.demo.xcodescanner.R;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegestrationActivty extends AppCompatActivity {

    TextInputLayout textInputLayoutname,textInputLayoutaddress,textInputLayoutphone,textInputLayoutcountry,textInputLayoutage,
            textInputLayoutpassword,textInputLayoutconfirmpassword,textInputLayoutr;
    EditText textInputEditTextname,textInputEditTextaddress,textInputEditTextphone,textInputEditTextcountry,textInputEditTextage,
            textInputEditTextpassword,textInputEditTextconfirmpassword,textInputEditTextr,textInputEditTextmail;
    AppCompatButton regesiter;
    TextView categoryid;
    TextView openlogin;
    AppCompatSpinner spin;
    Call<ResponseBody> call = null;
    private apiinterface_home apiinterface;
    String code,mVerificationId;
    ProgressDialog progressDialog;
    int ca;
    ImageView profile;
    ProgressDialog progressDialog1;
    //   login_ login_;
    String codee =null;
Getcategry getcategry;
    private List<content_category> contactList;
    Bitmap bitmap;
    int []id;
    private  static final int IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration_activty);
        spin=findViewById(R.id.spin);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);





        inisialize();
        GfetchInfo();

        openlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(Registration.this,LoginActivity.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                DialogFragment dialogFragment = new FragmentImage();
                dialogFragment.show(ft, "dialog");
            }
        });
        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regesiter.setClickable(false);
                if (textInputEditTextaddress.getText().toString().equals("") || textInputEditTextaddress.getText().toString() == null) {

                    textInputLayoutaddress.setError("أدخل البريد الألكترونى");

                } else if (textInputEditTextname.getText().toString().equals("") || textInputEditTextname.getText().toString() == null) {

                    textInputLayoutname.setError("أدخل اسم المستخدم");

                } else if (textInputEditTextphone.getText().toString().equals("") || textInputEditTextphone.getText().toString() == null) {

                    textInputLayoutphone.setError("أدخل رقم الموبيل");

                } else if (textInputEditTextpassword.getText().toString().equals("") || textInputEditTextpassword.getText().toString() == null) {

                    textInputLayoutpassword.setError("أدخل كلمة المرور");

                } else if (textInputEditTextconfirmpassword.getText().toString().equals("") || textInputEditTextconfirmpassword.getText().toString() == null) {

                    textInputLayoutconfirmpassword.setError("أدخل  تأكيد كلمة المرور");

                } else if (!textInputEditTextconfirmpassword.getText().toString().equals(textInputEditTextpassword.getText().toString())) {
                    textInputLayoutconfirmpassword.setError("كلمة تأكيد مختلفة");
                } else {
                  ca=spin.getSelectedItemPosition();
                  int x=id[ca];
                  fetchInfo(x);
                }
            }
        });


    }


    public void fetchInfo(int ca) {
        progressDialog = ProgressDialog.show(RegestrationActivty.this, "جاري انشاء الحساب", "Please wait...", false, false);
        progressDialog.show();

          String image=categoryid.getText().toString();

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts(textInputEditTextname.getText().toString(),
                textInputEditTextpassword.getText().toString(), textInputEditTextaddress.getText().toString()
                ,textInputEditTextphone.getText().toString(),textInputEditTextage.getText().toString(),textInputEditTextmail.getText().toString(),
                image,ca);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegestrationActivty.this);
                dlgAlert.setMessage("تم أنشاء حساب جديد بنجاح ... سنتواصل معك قريبا لتمكينك من الدخول ");
                dlgAlert.setTitle("دللي جمالك");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegestrationActivty.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inisialize() {

        textInputEditTextname =  findViewById(R.id.input_name);
        textInputEditTextphone =  findViewById(R.id.input_mobile);
        textInputEditTextaddress =  findViewById(R.id.input_address);
        textInputEditTextmail= findViewById(R.id.input_email);
        textInputEditTextpassword =  findViewById(R.id.input_password);
        textInputEditTextage =  findViewById(R.id.input_date);
        textInputEditTextconfirmpassword =  findViewById(R.id.input_reEnterPassword);
        regesiter = (AppCompatButton) findViewById(R.id.btn_signup);
        profile=findViewById(R.id.image);
        openlogin =  findViewById(R.id.link_login);
        categoryid=findViewById(R.id.categoryid);


    }


    public void GfetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<content_category>> call = apiinterface.getcontacts_allfirst();
        call.enqueue(new Callback<List<content_category>>() {
            @Override
            public void onResponse(Call<List<content_category>> call, Response<List<content_category>> response) {
                contactList = response.body();

                if(!contactList.isEmpty()|| contactList.equals(null)){
                    String []category = new String[contactList.size()];
                    id = new int[contactList.size()];
                    for(int i=0;i<contactList.size();i++){

                        category[i]=contactList.get(i).getName();
                        id[i]=contactList.get(i).getId();

                    }

                   ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(RegestrationActivty.this,
                            android.R.layout.simple_spinner_dropdown_item, category);
                    spin.setAdapter(adpt_area);
                }


            }

            @Override
            public void onFailure(Call<List<content_category>> call, Throwable t) {

            }
        });

    }
}
