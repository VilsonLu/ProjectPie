package com.example.vilso.projectpie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyConnections extends Fragment implements ConnectionAdapter.ConnectionViewHolder.ClickListener{
    private RecyclerView recyclerView;
    private ConnectionAdapter itemAdapter;

    public FragmentMyConnections() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_my_connections, container, false);

        recyclerView = (RecyclerView)layout.findViewById(R.id.recyclerview);

        itemAdapter = new ConnectionAdapter(getActivity(), getData());
        itemAdapter.setClickListener(this);

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return layout;
    }

    public static List<ParseUser> getData(){
        List<ParseUser> data = new ArrayList();

        for(int i = 0; i < 20; i++){
            ParseUser temp = new ParseUser();
            temp.setUsername("Dionisia");
            temp.setEmail("dioni@gmail.com");
            data.add(temp);
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position) {

    }
}
