package com.example.vilso.projectpie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDrawer extends Fragment implements MenuItemAdapter.MenuItemViewHolder.ClickListener, View.OnClickListener {
    //Step 1 of 3: Customize Menu Items Here
    public static List<MenuItemModel> getData(){
        List<MenuItemModel> data = new ArrayList();
        int[] images = new int[]{R.drawable.ic_peach, R.drawable.ic_grass, R.drawable.ic_connections};
        int[] images_selected = new int[]{R.drawable.ic_peach_selected, R.drawable.ic_grass_selected, R.drawable.ic_connections_selected};
        String[] titles = new String[]{"Pitches","My Pitches","Connections"};

        for(int i = 0; i < images.length; i++){
            data.add(new MenuItemModel(images[i], images_selected[i], titles[i]));
        }
        return data;
    }

    //Step 2 of 3: OnClick Functions
    @Override
    public void itemClicked(View view, int position) {
        setSelected((ViewGroup)view, position);
        if(position == 0){
            changeFragment(new FragmentFeeds());
        }else if(position == 1){
            changeFragment(new FragmentMyIdeas());
        }else if(position == 2){
            changeFragment(new FragmentMyConnections());
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == btn_settings.getId()){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        }
    }

    //Step 3 of 3: Fragment Transition Animation
    private void changeFragment(Fragment frag){
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
        ft.replace(R.id.fragment_container, frag);
        ft.addToBackStack(null);
        ft.commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;

    private RecyclerView recylerNavView;
    private MenuItemAdapter itemAdapter;
    private Button btn_settings;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private static final String PREF_FILE_NAME = "testpref0";
    public static final String KEY_ULEARNED = "user_learned_drawer0";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private LinearLayoutManager layoutManager;
    private int itemSelected = 0;

    private Toolbar appbar;
    private View appBarOverlay;

    public FragmentDrawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.getBoolean(readFromPref(getActivity(), KEY_ULEARNED, "false"));
        if(savedInstanceState != null){
            mFromSavedInstanceState = true;
        }

        fm = getFragmentManager();
        ft = fm.beginTransaction();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_drawer, container, false);

        recylerNavView = (RecyclerView)layout.findViewById(R.id.recyclerview_menu);
        btn_settings = (Button)layout.findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(this);

        itemAdapter = new MenuItemAdapter(getActivity(), getData());
        itemAdapter.setClickListener(this);

        layoutManager = new LinearLayoutManager(getActivity());

        recylerNavView.setAdapter(itemAdapter);
        recylerNavView.setLayoutManager(layoutManager);

        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar){
        this.appbar = toolbar;
        containerView = getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPref(getActivity(), KEY_ULEARNED, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        if(mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    private void setSelected(ViewGroup view, int position){
        ImageView imgView = (ImageView) recylerNavView.getChildAt(itemSelected).findViewById(R.id.img_icon);
        TextView textView = (TextView) recylerNavView.getChildAt(itemSelected).findViewById(R.id.tv_itemname);
        ViewGroup parentView = (ViewGroup) recylerNavView.getChildAt(itemSelected).findViewById(R.id.parent_view);
        itemAdapter.setSelected(false, itemSelected, imgView, textView, parentView);

        itemSelected = position;
        imgView = (ImageView)view.findViewById(R.id.img_icon);
        textView = (TextView)view.findViewById(R.id.tv_itemname);
        parentView = (ViewGroup) recylerNavView.getChildAt(itemSelected).findViewById(R.id.parent_view);
        itemAdapter.setSelected(true, itemSelected, imgView, textView, parentView);
    }

    public static void saveToPref(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readFromPref(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key,value);
    }
}
