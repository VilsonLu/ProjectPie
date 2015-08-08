package com.example.vilso.projectpie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import models.IdeaItem;

/**
 * Created by Jeh on 08/08/2015.
 */
public class IdeaItemAdapter  extends RecyclerView.Adapter<IdeaItemAdapter.IdeaItemHolder>{
    private Context context;
    private IdeaItemHolder.ClickListener clickListener;
    private LayoutInflater inflater;
    private List<IdeaItem> data = Collections.emptyList();

    public IdeaItemAdapter(Context context, List<IdeaItem> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public IdeaItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_videos, parent, false);
        IdeaItemHolder holder = new IdeaItemHolder(context, clickListener, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(IdeaItemHolder holder, int position) {
        IdeaItem current = data.get(position);

        //TODO: get youtube thumbnail
//        holder.iv_img.setImageResource(current.getYoutubeLink());
        holder.tv_appname.setText(current.getTitle());
        holder.tv_views.setText("Views: " + current.getLikes());    //TODO: change to views
        holder.tv_likes.setText("Likes " + current.getLikes());
        holder.tv_status.setText(current.getStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(IdeaItemHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }

    static class IdeaItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private ClickListener clickListener;
        public ImageView iv_img;
        public TextView tv_appname;
        public TextView tv_views;
        public TextView tv_likes;
        public TextView tv_status;
        public Button btn_delete;

        public IdeaItemHolder(Context context, ClickListener clickListener, View itemView){
            super(itemView);
            this.context = context;
            this.clickListener = clickListener;
            iv_img = (ImageView)itemView.findViewById(R.id.iv_image);
            tv_appname = (TextView)itemView.findViewById(R.id.tv_appname);
            tv_views = (TextView)itemView.findViewById(R.id.tv_views);
            tv_likes = (TextView)itemView.findViewById(R.id.tv_likes);
            tv_status = (TextView)itemView.findViewById(R.id.tv_status);
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
