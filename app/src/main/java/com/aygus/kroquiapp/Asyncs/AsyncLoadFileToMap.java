package com.aygus.kroquiapp.Asyncs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aygus.kroquiapp.Modelos.AStyle;
import com.aygus.kroquiapp.Modelos.FolderOverlayL;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.SqLite.DataBase;
import com.aygus.kroquiapp.Utils.GenerateId;
import com.aygus.kroquiapp.Utils.StylerGeneral;

import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 21/11/2018.
 */

public class AsyncLoadFileToMap extends AsyncTask<Project, Void, List<Layer>> {
    private DataBase dataBase;
    private StylerGeneral styler;
    private HelperLoaderKml helperLoaderKml;
    private MapView mapView;
    private Context context;

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public AsyncLoadFileToMap(MapView mapView, DataBase dataBase, HelperLoaderKml helperLoaderKml, StylerGeneral styler, Context context) {
        this.dataBase = dataBase;
        this.helperLoaderKml = helperLoaderKml;
        this.styler = styler;
        this.mapView = mapView;
        this.context = context;
    }

    public interface HelperLoaderKml {
        void onPreExecute();
        void onPosExecute(List<Layer> layerList);
    }

    @Override
    protected List<Layer> doInBackground(Project... projects) {
        List<Layer> layerList = dataBase.getLayers(projects[0]);
        List<Layer> newList = new ArrayList<>();

        try {

            for (int i = 0; i < layerList.size(); i++) {
                Layer layer = layerList.get(i);

                KmlDocument kmlDocument = new KmlDocument();
                boolean b = kmlDocument.parseKMLFile(new File(layer.getSourceLayer()));
                FolderOverlay folderOverlay = null;

                if (b) {
                    kmlDocument.mKmlRoot.buildOverlay(mapView, null, styler, kmlDocument);
                    folderOverlay = (FolderOverlay) kmlDocument.mKmlRoot.buildOverlay(mapView, null, styler, kmlDocument);
                }

                AStyle aStyle = new AStyle(10, 25, GenerateId.generateColor(context, 0), R.color.color011, 0);
                layer.setaStyle(aStyle);

                layer.setOverlay(new FolderOverlayL(folderOverlay));
                newList.add(layer);
            }

            return newList;

        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Layer> layerList) {
        helperLoaderKml.onPosExecute(layerList);
    }
}
