package com.aygus.kroquiapp.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Style.StyleMarker;
import com.google.gson.JsonObject;

import org.osmdroid.bonuspack.kml.KmlFeature;
import org.osmdroid.bonuspack.kml.KmlLineString;
import org.osmdroid.bonuspack.kml.KmlPlacemark;
import org.osmdroid.bonuspack.kml.KmlPoint;
import org.osmdroid.bonuspack.kml.KmlPolygon;
import org.osmdroid.bonuspack.kml.KmlTrack;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;

/**
 * Created by SANTIAGO from AIGUS on 21/11/2018.
 */

public class StylerGeneral implements KmlFeature.Styler {
    private Context context;

    public StylerGeneral(Context context) {
        this.context = context;
    }

    @Override
    public void onFeature(Overlay overlay, KmlFeature kmlFeature) {

    }

    @Override
    public void onPoint(Marker marker, KmlPlacemark kmlPlacemark, KmlPoint kmlPoint) {
        StyleMarker sm = new StyleMarker("#27FF0F", "#186B46", 5, 15, context);
        Drawable dr = context.getResources().getDrawable(R.drawable.ic_add_kroqui);
        marker.setDraggable(true);
        marker.setIcon(sm);

        marker.setOnMarkerClickListener((marker1, mapView) -> {
            JsonObject s = kmlPlacemark.asGeoJSON(true);
            Toast.makeText(context, "Click" +  s.toString(), Toast.LENGTH_LONG).show();
            return true;
        });
    }

    @Override
    public void onLineString(Polyline polyline, KmlPlacemark kmlPlacemark, KmlLineString kmlLineString) {

    }

    @Override
    public void onPolygon(Polygon polygon, KmlPlacemark kmlPlacemark, KmlPolygon kmlPolygon) {
        polygon.setStrokeColor(Color.parseColor("#660505"));
        polygon.setStrokeWidth(2);
        polygon.setFillColor(Color.parseColor("#afe41f1f"));

    }

    @Override
    public void onTrack(Polyline polyline, KmlPlacemark kmlPlacemark, KmlTrack kmlTrack) {

    }
}
