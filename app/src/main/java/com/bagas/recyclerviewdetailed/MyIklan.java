package com.bagas.recyclerviewdetailed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyIklan extends RecyclerView.Adapter<MyIklan.MyIklanHolder> {
    private List<Integer> imageList; // Menyimpan gambar
    private Context context;

    public MyIklan(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public MyIklanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_iklan, parent, false);
        return new MyIklanHolder(view);
    }

    @Override
    public void onBindViewHolder(MyIklanHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position)); // Menampilkan gambar
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyIklanHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyIklanHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iklanView);
        }
    }
}

