package com.aygus.kroquiapp.Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Style.StyleMarker;
import com.aygus.kroquiapp.Utils.GenerateId;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class FragManagerTableAttributes extends Fragment {
    private List<Layer> layerList = new ArrayList<>();
    private TextView noNotifyLayersTbl;
    private Toolbar toolbar;
    private ImageButton getColor;
    private StyleMarker sm;
    private int colorInter;
    private int colorExter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragManagerTableAttributes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragManagerLayers.
     */
    // TODO: Rename and change types and number of parameters
    public static FragManagerTableAttributes newInstance(String param1, String param2) {
        FragManagerTableAttributes fragment = new FragManagerTableAttributes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_manager_table_attributes, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView()!=null){
            setView(getView());
        }
    }

    private void setView(View view){
        noNotifyLayersTbl = view.findViewById(R.id.noNotifyLayersTbl);
    }


    public void setDataLayer(Layer layer){
        noNotifyLayersTbl.setText(layer.getNameLayer());
    }

}
