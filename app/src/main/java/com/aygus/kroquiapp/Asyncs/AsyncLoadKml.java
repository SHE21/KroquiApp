package com.aygus.kroquiapp.Asyncs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Utils.StylerGeneral;

import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.OverlayManager;

import java.io.File;
import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 21/11/2018.
 */

public class AsyncLoadKml  extends AsyncTask<Void, Void, FolderOverlay> {
    private List<Layer> layerList;
    private String filesKml;
    private HelperLoaderKml helperLoaderKml;
    private Context context;
    private MapView mapView;
    private KmlDocument kmlDocument;

    public AsyncLoadKml(List<Layer> layerList, HelperLoaderKml helperLoaderKml, Context context, MapView mapView, String filesKml) {
        this.layerList = layerList;
        this.helperLoaderKml = helperLoaderKml;
        this.context = context;
        this.mapView = mapView;
        this.filesKml = filesKml;
    }

    public interface HelperLoaderKml {
        void onPreExecute();
        void onPosExecute(FolderOverlay kmlOverlay);
    }

    @Override
    protected FolderOverlay doInBackground(Void... voids) {
        StylerGeneral styler = new StylerGeneral(context);

        try {

            try {
                kmlDocument = new KmlDocument();
                kmlDocument.parseKMLFile(new File(filesKml));
                FolderOverlay kmlOverlay = (FolderOverlay)kmlDocument.mKmlRoot.buildOverlay(mapView, null, styler,kmlDocument);
                mapView.getOverlays().add(kmlOverlay);

                return kmlOverlay;

            }catch (IndexOutOfBoundsException ignored){
                return null;
            }

        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    protected void onPostExecute(FolderOverlay kmlOverlay) {

        helperLoaderKml.onPosExecute(kmlOverlay);

        if (kmlOverlay!=null) {
            BoundingBox bb = kmlDocument.mKmlRoot.getBoundingBox();
            mapView.zoomToBoundingBox(bb, true);
            mapView.getController().setCenter(new GeoPoint(bb.getCenterLatitude(), bb.getCenterLongitude()));
            double z = mapView.getZoomLevelDouble();
            mapView.getController().setZoom(z - 10.0);

            Log.d("ZOMMMM", "" + mapView.getZoomLevelDouble());

            mapView.invalidate();
        }

        super.onPostExecute(kmlOverlay);
    }
}
