package com.example.vilso.projectpie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jeh on 09/08/2015.
 */
public class StatisticsTabAdapter extends FragmentStatePagerAdapter {
    private CharSequence Titles[];
    private int NumbOfTabs;

    public StatisticsTabAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            FragmentStatistics tab1 = new FragmentStatistics();
            tab1.setValues(0.3f, 0.7f, "You need more users");
            return tab1;
        }
        else if (position == 1)
        {
            FragmentStatistics  tab2 = new FragmentStatistics ();
            tab2.setValues(0.2f, 0.8f, "You need more users");
            return tab2;
        }
        else if (position == 2)
        {
            FragmentStatistics  tab3 = new FragmentStatistics ();
            tab3.setValues(0.6f, 0.4f, "You need more users");
            return tab3;
        }
        else if (position == 3)
        {
            FragmentStatistics  tab4 = new FragmentStatistics ();
            tab4.setValues(0.7f, 0.3f, "You need more users");
            return tab4;
        }
        else
        {
            FragmentStatistics  tab5 = new FragmentStatistics ();
            tab5.setValues(0.5f, 0.5f, "You need more users");
            return tab5;
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