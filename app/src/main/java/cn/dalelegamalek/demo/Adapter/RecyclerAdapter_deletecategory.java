package cn.dalelegamalek.demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.dalelegamalek.demo.model.Apiclient_home;
import cn.dalelegamalek.demo.model.apiinterface_home;
import cn.dalelegamalek.demo.model.contact_order;
import cn.simonlee.demo.xcodescanner.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter_deletecategory extends RecyclerView.Adapter<RecyclerAdapter_deletecategory.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<contact_order> contactslist;
apiinterface_home apiinterface;
    public RecyclerAdapter_deletecategory(Context context, List<contact_order> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_delete,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


    holder.Name.setText(contactslist.get(position).getName());

        Glide.with(context).load(contactslist.get(position).getImage()).error(R.drawable.circlelogo).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=contactslist.get(position).getId();
                fetchInfo(id);
            }
        });
    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView image;

        ImageView delete;
    public MyViewHolder(View itemView) {
        super(itemView);
        Name=(TextView)itemView.findViewById(R.id.txt_fish_title);
        image=(ImageView)itemView.findViewById(R.id.imageView3);
        delete=(ImageView)itemView.findViewById(R.id.delete);

    }
}
    public void fetchInfo(int id) {

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.delete_category(id);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(context,"تم المسح", Toast.LENGTH_LONG).show();
                ((Activity)context).finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }
}