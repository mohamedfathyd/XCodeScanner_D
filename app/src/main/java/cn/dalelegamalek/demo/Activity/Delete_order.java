package cn.dalelegamalek.demo.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import cn.dalelegamalek.demo.Adapter.RecyclerAdapter_deletecategory;
import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.contact_order;
import cn.simonlee.demo.xcodescanner.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delete_order extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_deletecategory recyclerAdapter_secondry;
    private List<contact_order> contactList;
    private apiinterface_home apiinterface;
    int id = 0;
    String name;
    int sec_id;
    Typeface myTypeface;
    Intent intent;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        textView = (TextView) findViewById(R.id.toolbar_title);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getIntExtra("id", 0);


        textView.setText(name);


        this.setTitle("");
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

        recyclerView = (RecyclerView) findViewById(R.id.review);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchInfo();
    }

    public void fetchInfo() {
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_order>> call = apiinterface.getcontacts_order(id);
        call.enqueue(new Callback<List<contact_order>>() {
            @Override
            public void onResponse(Call<List<contact_order>> call, Response<List<contact_order>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                recyclerAdapter_secondry = new RecyclerAdapter_deletecategory(Delete_order.this, contactList);
                recyclerView.setAdapter(recyclerAdapter_secondry);


            }

            @Override
            public void onFailure(Call<List<contact_order>> call, Throwable t) {

            }
        });
    }
}