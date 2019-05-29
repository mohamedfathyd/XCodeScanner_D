package cn.dalelegamalek.demo.Adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.dalelegamalek.demo.model.content_profile;
import cn.simonlee.demo.xcodescanner.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Dialog dialog;
    private Context mContext ;
    ImageView imageView;
    TextView categoryid;
    private List<content_profile> mData ;


    public RecyclerViewAdapter(Context mContext, List<content_profile> mData, Dialog dialog) {
        this.mContext = mContext;
        this.mData = mData;
        this.dialog=dialog;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_item_book,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.img_book_thumbnail.setImageResource(mData.get(position).getImg());
     imageView=((Activity)mContext).findViewById(R.id.image);
        categoryid=((Activity)mContext).findViewById(R.id.categoryid);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           imageView.setImageResource(mData.get(position).getImg());
           if(position==0){categoryid.setText("aaaa");}
                if(position==1){categoryid.setText("bbbb");}
                if(position==2){categoryid.setText("cccc");}
                if(position==3){categoryid.setText("dddd");}
                if(position==4){categoryid.setText("eeee");}
                if(position==5){categoryid.setText("ffff");}
                if(position==6){categoryid.setText("gggg");}
                if(position==7){categoryid.setText("hhhh");}
                if(position==8){categoryid.setText("iiii");}
                if(position==9){categoryid.setText("jjjj");}
                if(position==10){categoryid.setText("kkkk");}
                if(position==11){categoryid.setText("llll");}
                if(position==12){categoryid.setText("mmmm");}
                if(position==13){categoryid.setText("nnnn");}
                if(position==14){categoryid.setText("oooo");}
                if(position==15){categoryid.setText("pppp");}
                if(position==16){categoryid.setText("qqqq");}

                dialog.dismiss();
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);


            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}