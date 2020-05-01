package com.aygus.kroquiapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aygus.kroquiapp.Adapters.AdapterListLayers;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.R;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.OverlayManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdapterListLayers.OnClickItemLayer} interface
 * to handle interaction events.
 * Use the {@link FragMapView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragMapView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private MapController mapController;
    private OverlayManager overlayManager;
    private MapView mapView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AdapterListLayers.OnClickItemLayer mListener;

    public FragMapView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragMapView.
     */
    // TODO: Rename and change types and number of parameters
    public static FragMapView newInstance(String param1, String param2) {
        FragMapView fragment = new FragMapView();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_map_view, container, false);

        mapView = view.findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(false);
        mapView.setMultiTouchControls(true);
        overlayManager = mapView.getOverlayManager();
        mapController = (MapController) mapView.getController();

        return view;
    }

    public void addLayerOnMap(List<Layer> layerList){
        if (layerList.size()!=0){
            for (Layer layer: layerList) {
                FolderOverlay folderOverlay = layer.getOverlay().folderOverlay;
                BoundingBox bb = folderOverlay.getBounds();
                mapView.zoomToBoundingBox(bb, true);
                //mapView.getController().setCenter(new GeoPoint(bb.getCenterLatitude(), bb.getCenterLongitude()));
                overlayManager.add(folderOverlay);
            }
            mapView.invalidate();
        }
    }

    public void goToLayer(Layer layer, int position){
        BoundingBox bb = overlayManager.get(position).getBounds();
        mapView.zoomToBoundingBox(bb, true);
        mapView.getController().setCenter(new GeoPoint(bb.getCenterLatitude(), bb.getCenterLongitude()));
        mapView.invalidate();
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof AdapterListLayers.OnClickItemLayer) {
//            mListener = (AdapterListLayers.OnClickItemLayer) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
}
