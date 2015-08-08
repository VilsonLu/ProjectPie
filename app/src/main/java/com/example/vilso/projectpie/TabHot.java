package com.example.vilso.projectpie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import models.IdeaContext;
import models.IdeaItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabHot extends Fragment implements IdeaItemAdapter.IdeaItemHolder.ClickListener{
    private RecyclerView recyclerView;
    private IdeaItemAdapter itemAdapter;

    public TabHot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_tab_hot, container, false);

        recyclerView = (RecyclerView)layout.findViewById(R.id.recyclerview);

        itemAdapter = new IdeaItemAdapter(getActivity(), getData());
        itemAdapter.setClickListener(this);

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<IdeaItem> getData(){

        IdeaContext context = new IdeaContext();
        List<ParseObject> ideaObject = context.getListOfIdeas();

        List<IdeaItem> data = new ArrayList();

        for(ParseObject item: ideaObject){
            IdeaItem itemObject = new IdeaItem(item.getString("title"), 0.3f, context.getLikeCount(item), item.getString("youtube"), item.getString("status"));
            itemObject.setObjectId(item.getObjectId());
            data.add(itemObject);
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position){

        Intent intent = new Intent(getActivity(), ViewIdeaActivity.class);
        if(getData().get(position) != null) {
            Log.e("Message Error", getData().get(position).getObjectId());
        }
        intent.putExtra("ideaID", getData().get(position).getObjectId().toString());
        startActivity(intent);
    }


}
