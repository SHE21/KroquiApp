package com.aygus.kroquiapp.Modelos;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.aygus.kroquiapp.R;

import java.io.Serializable;

/**
 * Created by SANTIAGO from AIGUS on 02/01/2019.
 */
public class LayerStore implements Serializable{
    private int id;
    private String idMaskLayer;
    private String idProjectMask;
    private String idMaskMetadata;
    private String idGeoDataOnBd;
    private String idPurchase;
    private String sourceOnApp;
    private String sourceOnBd;
    private String title;
    private String category;
    private String description;
    private String tags;
    private String formatFile;
    private String typeGeom;
    private String representatOfData;
    private String style;
    private int freeOrBuy;
    private double prize;
    private boolean statusVissibility;

    public static final String LAYER = "layer";

    public LayerStore() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMaskLayer() {
        return idMaskLayer;
    }

    public void setIdMaskLayer(String idMaskLayer) {
        this.idMaskLayer = idMaskLayer;
    }

    public String getIdProjectMask() {
        return idProjectMask;
    }

    public void setIdProjectMask(String idProjectMask) {
        this.idProjectMask = idProjectMask;
    }

    public String getIdMaskMetadata() {
        return idMaskMetadata;
    }

    public void setIdMaskMetadata(String idMaskMetadata) {
        this.idMaskMetadata = idMaskMetadata;
    }

    public String getIdGeoDataOnBd() {
        return idGeoDataOnBd;
    }

    public void setIdGeoDataOnBd(String idGeoDataOnBd) {
        this.idGeoDataOnBd = idGeoDataOnBd;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getSourceOnApp() {
        return sourceOnApp;
    }

    public void setSourceOnApp(String sourceOnApp) {
        this.sourceOnApp = sourceOnApp;
    }

    public String getSourceOnBd() {
        return sourceOnBd;
    }

    public void setSourceOnBd(String sourceOnBd) {
        this.sourceOnBd = sourceOnBd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    public String getTypeGeom() {
        return typeGeom;
    }

    public void setTypeGeom(String typeGeom) {
        this.typeGeom = typeGeom;
    }

    public String getRepresentatOfData() {
        return representatOfData;
    }

    public void setRepresentatOfData(String representatOfData) {
        this.representatOfData = representatOfData;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getFreeOrBuy() {
        return freeOrBuy;
    }

    public void setFreeOrBuy(int freeOrBuy) {
        this.freeOrBuy = freeOrBuy;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public boolean isStatusVissibility() {
        return statusVissibility;
    }

    public void setStatusVissibility(boolean statusVissibility) {
        this.statusVissibility = statusVissibility;
    }

    public static Drawable setIconGeometryType(String geometryType, Context context){
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_card_point, null);

        if (geometryType!=null){
            switch (geometryType){
                case "point" :
                    drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_card_point, null);
                    break;

                case "polygon" :
                    drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_card_point, null);
                    break;

                case "line" :
                    drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_card_point, null);
                    break;
            }
        }

        return drawable;
    }
}
