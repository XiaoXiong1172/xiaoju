package com.example.xiaoju.ui.zh;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiaoju.R;
import com.example.xiaoju.db.AppDataCache;
import com.example.xiaoju.model.DataBean;
import com.example.xiaoju.ui.WebViewActivity;
import com.example.xiaoju.utils.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class BaseAdapter extends PagedListAdapter<DataBean, BaseAdapter.MyViewHolder> {
    private static Context context;
    protected BaseAdapter(Context context) {
        super(c);
        this.context  = context;
    }
    public static final DiffUtil.ItemCallback c = new DiffUtil.ItemCallback<DataBean>() {

        @Override
        public boolean areItemsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      DataBean dataBean =  getItem(position);
      holder.bind(dataBean);
    }

   static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title,ctime,desc;
        private ImageView imageView;
        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
             title =   itemView.findViewById(R.id.title);
           ctime =  itemView.findViewById(R.id.ctime);
           desc = itemView.findViewById(R.id.desc);
                imageView = itemView.findViewById(R.id.image);
        }

        private void bind(DataBean dataBean) {
                if (null ==dataBean){
                    title.setText("1");
                    ctime.setText("1");
                    desc.setText("1");
                }else {
                    Log.d("baseadapter", "bind: "+dataBean.toString());
                    title.setText(dataBean.getTitle());
                    ctime.setText(translate(dataBean.getCtime()));
                    desc.setText(dataBean.getDescription());
                    Glide.with(context).load(dataBean.getPicUrl()).into(imageView);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, WebViewActivity.class);
                            intent.putExtra("url",dataBean.getUrl());
                            intent.putExtra("title",dataBean.getDescription());
                            context.startActivity(intent);
                        }
                    });
                    itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            AppDataCache.getInstance().deleteDataBean(dataBean);
                            return true;
                        }
                    });
                }
        }
       private String translate(String ctext) {
            Date date = null;
           DateFormat format  =  new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
           try {
              date =   format.parse(ctext);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           return DateUtil.getTimeFormatText(date);
       }
    }
}
