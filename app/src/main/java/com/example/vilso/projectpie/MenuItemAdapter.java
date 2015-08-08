package com.example.vilso.projectpie;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>{
    private Context context;
    private MenuItemViewHolder.ClickListener clickListener;
    private LayoutInflater inflater;
    private List<MenuItemModel> data = Collections.emptyList();

    public MenuItemAdapter(Context context, List<MenuItemModel> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_menu_item, parent, false);
        MenuItemViewHolder holder = new MenuItemViewHolder(context, clickListener, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        MenuItemModel current = data.get(position);
        //set the attributes to the Views
        if(position == 0){
            setSelected(true, position, holder.iv_img, holder.tv_title, holder.parent_view);
        }
        else {
            setSelected(false, position, holder.iv_img, holder.tv_title, holder.parent_view);
        }
        holder.tv_title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //TODO: Optimize view on select state (specially on TextView)
    //Add more Tags when adding views in row
    public void setSelected(boolean isSelected, int position, View ... views){
        if(isSelected){
            for(View view : views){
                if(view.getTag().equals("imageview")){
                    ((ImageView)view).setImageResource(data.get(position).getImg_src_selected());
                }else if(view.getTag().equals("textview")){
                    if(view.getClass().equals(AppCompatTextView.class))
                        ((AppCompatTextView)view).setTextColor(context.getResources().getColor(R.color.menu_item_title_selected));
                    else if (view.getClass().equals(TextView.class))
                        ((TextView)view).setTextColor(context.getResources().getColor(R.color.menu_item_title_selected));
                }else if(view.getTag().equals("parentview")){
                    view.setBackgroundResource(R.drawable.ripple_gray_bg);
                }
            }
        }else{
            for(View view : views){
                if(view.getTag().equals("imageview")){
                    ((ImageView)view).setImageResource(data.get(position).getImg_src());
                }else if(view.getTag().equals("textview")){
                    if(view.getClass().equals(AppCompatTextView.class))
                        ((AppCompatTextView)view).setTextColor(context.getResources().getColor(R.color.menu_item_title));
                    else if (view.getClass().equals(TextView.class))
                        ((TextView)view).setTextColor(context.getResources().getColor(R.color.menu_item_title));
                }else if(view.getTag().equals("parentview")){
                    view.setBackgroundResource(R.drawable.ripple_white_bg);
                }
            }
        }
    }

    public void setClickListener(MenuItemViewHolder.ClickListener clickListener){
        this.clickListener = clickListener;
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private ClickListener clickListener;
        public ImageView iv_img;
        public TextView tv_title;
        public RelativeLayout parent_view;

        public MenuItemViewHolder(Context context, ClickListener clickListener, View itemView){
            super(itemView);
            this.context = context;
            this.clickListener = clickListener;
            iv_img = (ImageView)itemView.findViewById(R.id.img_icon);
            tv_title = (TextView)itemView.findViewById(R.id.tv_itemname);
            parent_view = (RelativeLayout)itemView.findViewById(R.id.parent_view);
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
