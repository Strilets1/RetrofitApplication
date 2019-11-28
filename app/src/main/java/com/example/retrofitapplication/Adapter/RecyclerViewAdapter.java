package com.example.retrofitapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitapplication.Message;
import com.example.retrofitapplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Message> messageList = new ArrayList<>();
    Context mContext;

    public RecyclerViewAdapter(List<Message> messageList, Context context) {
        this.mContext = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvText.setText(messageList.get(position).getText());
        holder.tvId.setText("id: " + String.valueOf(messageList.get(position).getId()));
        //holder.tvTime.setText(String.valueOf(messageList.get(position).getTime()));
        //*****************************************************
        Date date = new Date(messageList.get(position).getTime());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(date);
        holder.tvTime.setText("Time: " + dateFormatted);

        //Load image
        Glide.with(mContext)
                .asBitmap()
                .load(messageList.get(position).getImage())
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvId, tvText, tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.imageView);
            tvText = itemView.findViewById(R.id.text_message);
            tvId = itemView.findViewById(R.id.id_message);
            tvTime = itemView.findViewById(R.id.time_message);
        }
    }
}
