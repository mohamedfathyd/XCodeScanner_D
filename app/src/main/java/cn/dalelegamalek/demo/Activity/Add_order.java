package cn.dalelegamalek.demo.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.simonlee.demo.xcodescanner.R;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_order extends AppCompatActivity {

    TextInputLayout textInputLayoutname,textInputLayoutaddress,textInputLayoutphone,textInputLayoutcountry,textInputLayoutage,
            textInputLayoutpassword,textInputLayoutconfirmpassword,textInputLayoutr;
    EditText textInputEditTextname,textInputEditTextaddress,textInputEditTextphone,textInputEditTextcountry,textInputEditTextprice,
            textInputEditTextpoints,textInputEditTextconfirmpassword,textInputEditTextr,textInputEditTextdetails;
    AppCompatButton regesiter;
    TextView openlogin;

    Call<ResponseBody> call = null;
    private apiinterface_home apiinterface;
    String code,mVerificationId;
    ProgressDialog progressDialog;
    ImageView profile;
    ProgressDialog progressDialog1;
    //   login_ login_;
    String codee =null;

    Bitmap bitmap;
    private  static final int IMAGE = 100;
    String [] egypt={"القاهرة", "الجيزة","المنبا","السويس","الأقصر","الاسكندرية","بورسعيد","دمياط","أسوان","القليوبية","بنى سويف","الاسماعيلية","" +
            "سوهاج","أسيوط","البحر الأحمر","البحيرة","الدقهلية","الغربية","الفيوم","قنا","كفر الشيخ","مطروح","المنوفيه","الوادى الجديد","" +
            "6 من أكنوبر","شمال سيناء","جنوب سيناء","حلوان","الشارقية"};
    String [] saudia={"الرياض","مكة المكرمة","المدينة المنورة","القصيم","الشرقية","عسير","تبوك","حائل","الحدود الشمالية","جازان","نجران","" +
            "الباحة","الجوف"};
    String [] emarat={"دبي","أبوظبي","الشارقة","العين","رأس الخيمة","عجمان","الفجيرة","أم القيوين","خورفكان","دبا الحصن"};
    String [] bahren={"العاصمة","المحرق","الشمالية","الجنوبية"};
    String [] aman={"الداخلية","الظاهرة","شمال الباطنة","جنوب الباطنة","البريمى","الوسطى","شمال الشرقية","جنوب الشرقية","ظفار","مسقط","مسندم"};
    String []qatar={"الريان","الدوحة","الخور","الوكرة","الشمال","أم صلال"};
    String []kuwait={"العاصمة","الجهراء","الفراونية","حولى","مبارك الكبير"};
    Spinner spn,spncontry;
    Intent intent;
    int id;
    int category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        inisialize();
        intent=getIntent();
        id=intent.getIntExtra("id",0);
        category= intent.getIntExtra("category",0);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
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
        spn=findViewById(R.id.spinsss);
        spncontry=findViewById(R.id.spin);
        spncontry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spncontry.getSelectedItem().toString().equals("مصر")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, egypt);
                    spn.setAdapter(adpt_area);
                } else if (spncontry.getSelectedItem().toString().equals("السعودية")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, saudia);
                    spn.setAdapter(adpt_area);
                } else if (spncontry.getSelectedItem().toString().equals("الأمارات")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, emarat);
                    spn.setAdapter(adpt_area);
                } else if (spncontry.getSelectedItem().toString().equals("الكويت")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, kuwait);
                    spn.setAdapter(adpt_area);
                } else if (spncontry.getSelectedItem().toString().equals("عمان")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, aman);
                    spn.setAdapter(adpt_area);
                } else if (spncontry.getSelectedItem().toString().equals("قطر")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, qatar);
                    spn.setAdapter(adpt_area);
                } else if (spncontry.getSelectedItem().toString().equals("البحرين")) {
                    ArrayAdapter<String> adpt_area = new ArrayAdapter<String>(Add_order.this,
                            android.R.layout.simple_spinner_dropdown_item, bahren);
                    spn.setAdapter(adpt_area);
                }
            }
            @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
            }
        });
profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        selectImage();
    }
});
}
    public void fetchInfo() {
        progressDialog = ProgressDialog.show(Add_order.this, "جاري أضافة الاعلان", "Please wait...", false, false);
        progressDialog.show();
        String image = convertToString();


        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontactsadd(textInputEditTextname.getText().toString(),
               category, id
                ,textInputEditTextdetails.getText().toString(),spncontry.getSelectedItem().toString(),spn.getSelectedItem().toString(),
                textInputEditTextphone.getText().toString(), Double.valueOf(textInputEditTextprice.getText().toString()), Integer.parseInt(textInputEditTextpoints.getText().toString()),textInputEditTextaddress.getText().toString(),
                image);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Add_order.this);
                dlgAlert.setMessage("تم أضافة الاعلان بنجاح ");
                dlgAlert.setTitle("دللي جمالك");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Add_order.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inisialize() {

        textInputEditTextname =  findViewById(R.id.input_name);
        textInputEditTextphone =  findViewById(R.id.input_mobile);
        textInputEditTextaddress =  findViewById(R.id.input_address);
        textInputEditTextdetails= findViewById(R.id.input_details);
        textInputEditTextpoints =  findViewById(R.id.input_points);
        textInputEditTextprice =  findViewById(R.id.input_price);
        regesiter = (AppCompatButton) findViewById(R.id.btn_signup);
        profile=findViewById(R.id.image);


    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }
    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


