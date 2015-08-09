package com.example.vilso.projectpie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vilso.projectpie.floatingactionbutton.FloatingActionButton;

import models.IdeaContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFeeds extends Fragment implements View.OnClickListener{
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[]={"Hot","Trending","Vote"};
    private int Numboftabs = 3;
    private FloatingActionButton btn_fab;


    public FragmentFeeds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_feeds, container, false);

        adapter =  new ViewPagerAdapter(getActivity().getSupportFragmentManager(),Titles,Numboftabs);

        pager = (ViewPager) layout.findViewById(R.id.viewpager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) layout.findViewById(R.id.sliding_tab);

        // True if Tabs are same Width
        tabs.setDistributeEvenly(true);

        //Scroll Bar Custom Color
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);

        btn_fab = (FloatingActionButton)layout.findViewById(R.id.btn_fab);
        btn_fab.setOnClickListener(this);

        return layout;
    }


    @Override
    public void onClick(View v) {
        if(v.equals(btn_fab)){

            Intent intent = new Intent(getActivity(), AddPitchActivity.class);
            startActivity(intent);
        }
    }
}
