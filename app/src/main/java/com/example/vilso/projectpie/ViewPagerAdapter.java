package com.example.vilso.projectpie;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private CharSequence Titles[];
    private int NumbOfTabs;

    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            TabHot tab1 = new TabHot();
            return tab1;
        }
        else if(position == 1)
        {
            TabTrending tab2 = new TabTrending();
            return tab2;
        }else{
            TabVote tab3 = new TabVote();
            return tab3;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}