package com.example.firing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<Imagemodel> wallpaperlist;

public Adapter(Context context,ArrayList<Imagemodel> wallpaperlist){
    this.context=context;
    this.wallpaperlist=wallpaperlist;
}

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.item_layout,null,false);
    return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {




    holder.textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.textView.setMovementMethod(LinkMovementMethod.getInstance());
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserintent=new Intent(Intent.ACTION_VIEW);
                    browserintent.setData(Uri.parse("https://www.pexels.com/"));
                    browserintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserintent);
                }
            });
        }
    });
        Glide.with(context).load(wallpaperlist.get(position).getSrc().getPortrait()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,setwallpaper.class);
                intent.putExtra("image",wallpaperlist.get(position).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return wallpaperlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.textview);

        }
    }
}
