package com.example.vilso.projectpie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFeeds extends Fragment {
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[]={"Hot","Trending","Vote"};
    private int Numboftabs = 3;


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

        return layout;
    }


}
