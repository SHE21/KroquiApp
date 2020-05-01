package com.aygus.kroquiapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aygus.kroquiapp.Adapters.AdapterListLayers;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragManagerLayers.OnListenerManagerFragment} interface
 * to handle interaction events.
 * Use the {@link FragManagerLayers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragManagerLayers extends Fragment{
    private RecyclerView recyclerLayers;
    private TextView noNotifyLayers;
    private ProgressBar progressBar;
    private AdapterListLayers adapterListLayers;
    private AdapterListLayers.OnClickItemLayer onClickItemLayer;
    private List<Layer> layerList = new ArrayList<>();
    private OnListenerManagerFragment onListenerManagerFragment;

    public interface OnListenerManagerFragment{
        void resultDialogAddLayer(Layer layer);
        void layersFromManager(List<Layer> layerList);
        void openDialogAddLayer();
        void closeDialogAddLayer();
        void fragCreated();
    }

    private static final String PROJECT = "project";

    public FragManagerLayers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param project Parameter 1.
     * @return A new instance of fragment FragManagerLayers.
     */
    // TODO: Rename and change types and number of parameters
    public static FragManagerLayers newInstance(Project project) {
        FragManagerLayers fragment = new FragManagerLayers();
        Bundle args = new Bundle();
        args.putSerializable(PROJECT, project);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_manager_layers, container, false);

        noNotifyLayers = view.findViewById(R.id.noNotifyLayers);
        progressBar = view.findViewById(R.id.progressManagerLayer);

        recyclerLayers = view.findViewById(R.id.recyclerLayers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerLayers.setLayoutManager(layoutManager);
        recyclerLayers.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onListenerManagerFragment.fragCreated();
    }

    public void addLayerInList(Layer layer){
        layerList.add(layer);
        adapterListLayers.notifyDataSetChanged();
        if (layerList.size()!=0){noNotifyLayers.setVisibility(View.GONE); recyclerLayers.setVisibility(View.VISIBLE);}
        onListenerManagerFragment.layersFromManager(layerList);
    }

    public void addAllLayer(List<Layer> layerList1){
        if(layerList1.size()!=0) {
            progressBar.setVisibility(View.GONE);
            noNotifyLayers.setVisibility(View.GONE);
            recyclerLayers.setVisibility(View.VISIBLE);

            layerList = layerList1;
            adapterListLayers = new AdapterListLayers(layerList, onClickItemLayer, getContext());
            recyclerLayers.setAdapter(adapterListLayers);
            onListenerManagerFragment.layersFromManager(layerList1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdapterListLayers.OnClickItemLayer) {
            onClickItemLayer = (AdapterListLayers.OnClickItemLayer) context;
            onListenerManagerFragment = (FragManagerLayers.OnListenerManagerFragment) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onClickItemLayer = null;
        onListenerManagerFragment = null;
    }
}
