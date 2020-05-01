package com.aygus.kroquiapp.Fragments;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aygus.kroquiapp.Asyncs.AsyncLoadKml;
import com.aygus.kroquiapp.Interfaces.CallBackImportLayers;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Utils.DateApp;
import com.aygus.kroquiapp.Utils.FileBrowserActivity;
import com.aygus.kroquiapp.Utils.GenerateId;
import com.aygus.kroquiapp.Utils.ToastCostum;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;


public class FragCreateFile extends Fragment{
    private TextView stringDir, NoPreviewMap;
    private EditText layerName;
    private ProgressBar progrMapPreview;
    private CallBackImportLayers callBackImportLayers;
    private MapView mapViewPreview;
    private MapController mapController;

    private Project project;
    private Intent data;

    public static FragCreateFile newInstance(CallBackImportLayers call, Project project){
        FragCreateFile fragCreateFile = new FragCreateFile();
        fragCreateFile.callBackImportLayers = call;

        Bundle args = new Bundle();
        args.putSerializable("project", project);
        fragCreateFile.setArguments(args);
        return fragCreateFile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            project = (Project) getArguments().getSerializable("project");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_create_file, container, false);
        NoPreviewMap = view.findViewById(R.id.NoPreviewMap);

        progrMapPreview = view.findViewById(R.id.progrMapPreview);
        mapViewPreview = view.findViewById(R.id.mapViewPreview);
        mapViewPreview.setTileSource(TileSourceFactory.MAPNIK);
        mapViewPreview.setBuiltInZoomControls(false);
        mapViewPreview.setMultiTouchControls(false);
        mapViewPreview.setFlingEnabled(true);
        IMapController mp = mapViewPreview.getController();
        mp.stopPanning();

        stringDir = view.findViewById(R.id.stringDir);
        layerName = view.findViewById(R.id.layerName);
        ImageButton chooseFile = view.findViewById(R.id.chooseFileBtn);
        chooseFile.setOnClickListener(v -> {
            Intent fileExploreIntent = new Intent(FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
                    null,
                    getContext(),FileBrowserActivity.class
            );

            startActivityForResult(
                    fileExploreIntent,
                    2
            );

        });

        return view;
    }

    private AsyncLoadKml.HelperLoaderKml helperLoaderKml(){
        return new AsyncLoadKml.HelperLoaderKml() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPosExecute(FolderOverlay kmlOverlay) {
                if (kmlOverlay!=null) {
                    progrMapPreview.setVisibility(View.GONE);
                    NoPreviewMap.setVisibility(View.GONE);
                    mapViewPreview.setVisibility(View.VISIBLE);

                    //resultado para activity  principal
                    Layer layer = new Layer();
                    layer.setIdMaskLayer(GenerateId.generateId());
                    layer.setNameLayer(data.getStringExtra("fileName"));
                    layer.setSourceLayer(data.getStringExtra(FileBrowserActivity.returnFileParameter));
                    layer.setDescripLayer(kmlOverlay.getDescription());
                    layer.setIdProjectMask(project.getIdMaskProject());
                    layer.setDateLayer(DateApp.getDate());

                    callBackImportLayers.returnResut(layer);
                    //resultado para activity  principal

                    layerName.setText(layer.getNameLayer());
                    stringDir.setTextColor(Color.BLACK);
                    stringDir.setText(layer.getSourceLayer());

                }else{
                    ToastCostum toast = new ToastCostum("Arquivo com formatação invalida.", getActivity(), Toast.LENGTH_LONG, ToastCostum.ERROR);
                    toast.show();
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data1) {
        // TODO Auto-generated method stub

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                data = data1;
                AsyncLoadKml asyncLoadKml = new AsyncLoadKml(null, helperLoaderKml(),
                        getContext(), mapViewPreview, data1.getStringExtra(FileBrowserActivity.returnFileParameter));
                asyncLoadKml.execute();

            } else {
                Toast.makeText(
                        getContext(),
                        "Received NO result from file browser",
                        Toast.LENGTH_LONG)
                        .show();
            }
        }else{

            Toast.makeText(
                    getContext(),
                    "Erro, requestCode is diferent",
                    Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
