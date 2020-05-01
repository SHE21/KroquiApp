package com.aygus.kroquiapp.Fragments;

/**
 * Created by GEOPRO 03 on 20/03/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aygus.kroquiapp.R;


public class FragCreateOnMap extends Fragment{

    public FragCreateOnMap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_create_on_map, container, false);
    }

}
