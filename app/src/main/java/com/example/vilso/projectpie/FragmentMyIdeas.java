package com.example.vilso.projectpie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import models.IdeaItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyIdeas extends Fragment  implements IdeaItemAdapter.IdeaItemHolder.ClickListener{
    private RecyclerView recyclerView;
    private IdeaItemAdapter itemAdapter;

    public FragmentMyIdeas() {
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
        List<IdeaItem> data = new ArrayList();

        for(int i = 0; i < 20; i++){
            data.add(new IdeaItem("App name", 0.3f, 300, "", "In Development"));
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getActivity(), MyIdeaStatisticsActivity.class);
        startActivity(intent);
    }
}
