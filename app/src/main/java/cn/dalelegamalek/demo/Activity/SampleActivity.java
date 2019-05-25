package cn.dalelegamalek.demo.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.dalelegamalek.demo.xcodescanner.ScanActivity;
import cn.simonlee.demo.xcodescanner.R;
import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;

public class SampleActivity extends AppCompatActivity {
    CircleImageView img;
    Button add,update,delete,scan;
    TextView nam,phon,point,date;
    String name,age,address,phone,image,mail;
    private SharedPreferences sharedpref;
    int id,points,category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        initializer();
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        sharedpref= getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        id=sharedpref.getInt("id",0);
        name=sharedpref.getString("name","");
        address=sharedpref.getString("address","");
        phone=sharedpref.getString("phone","");
        image=sharedpref.getString("image","");
        points=sharedpref.getInt("points",0);
        age=sharedpref.getString("age","");
        category=sharedpref.getInt("category",0);
        mail=sharedpref.getString("email","");
        Glide.with(this).load(image).error(R.drawable.circlelogo).into(img);
        nam.setText(name);
        phon.setText(phone);
        point.setText(points+"");
        date.setText(age);
           add.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent i=new Intent(SampleActivity.this, Add_order.class);
                    i.putExtra("category",category);
                    i.putExtra("id",id);
                    startActivity(i);
               }
           });

           delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i=new Intent(SampleActivity.this, Delete_order.class);
                   i.putExtra("category",category);
                   i.putExtra("id",id);
                   startActivity(i);
               }
           });
           scan.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(SampleActivity.this, ScanActivity.class));
               }
           });
    }
    public void initializer(){
        img=findViewById(R.id.img);
        add=findViewById(R.id.add_order);
        delete=findViewById(R.id.delete_order);

        scan=findViewById(R.id.scan);
        nam=findViewById(R.id.name);
        date=findViewById(R.id.age);
        point=findViewById(R.id.point);
        phon=findViewById(R.id.order);
    }
}
