package com.example.vilso.projectpie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStatistics extends Fragment {
    private float right;
    private float wrong;
    private String message;

    public FragmentStatistics() {
        // Required empty public constructor
    }

    public void setValues(float right, float wrong, String message) {
        this.right = right;
        this.wrong = wrong;
        this.message = message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }


}
