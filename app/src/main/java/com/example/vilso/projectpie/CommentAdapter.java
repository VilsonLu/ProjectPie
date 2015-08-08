package com.example.vilso.projectpie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import models.Rate;
import models.RateAndComment;

/**
 * Created by Jeh on 08/08/2015.
 */
public class CommentAdapter extends ParallaxRecyclerAdapter<RateAndComment> {
    private Context context;
    private CommentViewHolder.ClickListener clickListener;
    private LayoutInflater inflater;
    private List<RateAndComment> data = Collections.emptyList();

    public CommentAdapter(List<RateAndComment> data){
        super(data);
        this.data = data;
    }

    public void setContext(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void implementMethods(){
        implementRecyclerAdapterMethods(new RecyclerAdapterMethods() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
                CommentViewHolder holder = (CommentViewHolder)holder1;

                RateAndComment current = data.get(position);
                //set the attributes to the Views
//        holder.iv_img.setImageResource(current.get());
                holder.tv_username.setText(current.getUsername());
                holder.tv_comment.setText(current.getComment());
                holder.tv_rating.setRating(current.getRating());
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = inflater.inflate(R.layout.row_comment, viewGroup, false);
                CommentViewHolder holder = new CommentViewHolder(context, clickListener, view);
                return holder;
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });
    }

    public void setClickListener(CommentViewHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private ClickListener clickListener;
        public CircleImageView iv_img;
        public TextView tv_username;
        public TextView tv_comment;
        public RatingBar tv_rating;

        public CommentViewHolder(Context context, ClickListener clickListener, View itemView){
            super(itemView);
            this.context = context;
            this.clickListener = clickListener;
            iv_img = (CircleImageView)itemView.findViewById(R.id.profile_image);
            tv_username = (TextView)itemView.findViewById(R.id.tv_username);
            tv_comment = (TextView)itemView.findViewById(R.id.tv_comment);
            tv_rating = (RatingBar)itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v){
            //ATTENTION - This is not the onClick function, don't add here
            //Look for itemClick() in main activity

            if(clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }

        public interface ClickListener{
            void itemClicked(View view, int position);
        }

    }
}
