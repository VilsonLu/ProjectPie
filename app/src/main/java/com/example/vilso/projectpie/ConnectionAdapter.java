package com.example.vilso.projectpie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jeh on 09/08/2015.
 */
public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.ConnectionViewHolder>{
    private Context context;
    private ConnectionViewHolder.ClickListener clickListener;
    private LayoutInflater inflater;
    private List<ParseUser> data = Collections.emptyList();

    public ConnectionAdapter(Context context, List<ParseUser> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ConnectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_connection, parent, false);
        ConnectionViewHolder holder = new ConnectionViewHolder(context, clickListener, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConnectionViewHolder holder, int position) {
        ParseUser current = data.get(position);
        //set the attributes to the Views
//        holder.iv_img.setImageResource(current.get());
        holder.tv_title.setText(current.getUsername());
        holder.tv_subtext.setText(current.getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(ConnectionViewHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }

    static class ConnectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private ClickListener clickListener;
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_subtext;

        public ConnectionViewHolder(Context context, ClickListener clickListener, View itemView){
            super(itemView);
            this.context = context;
            this.clickListener = clickListener;
            iv_img = (ImageView)itemView.findViewById(R.id.iv_profilepic);
            tv_title = (TextView)itemView.findViewById(R.id.tv_name);
            tv_subtext = (TextView)itemView.findViewById(R.id.tv_email);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v){
            if(clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }

        public interface ClickListener{
            void itemClicked(View view, int position);
        }

    }
}
