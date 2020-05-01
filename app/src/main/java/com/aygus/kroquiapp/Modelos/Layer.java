package com.aygus.kroquiapp.Modelos;

import java.io.Serializable;

/**
 * Created by SANTIAGO from AIGUS on 14/10/2018.
 */
public class Layer extends LayerStore implements Serializable{
    private int idLayer;
    private String idMaskLayer;
    private String idProjectMask;
    private String nameLayer;
    private String typeGeomLayer;
    private String descripLayer;
    private String sourceLayer;
    private String styleLayer;
    private String dateLayer;
    private String dateEditLayer;
    private transient FolderOverlayL overlay;
    private Project project;
    private AStyle aStyle;

    public Layer(){}

    public Layer(int idLayer, String idMaskLayer, String idProjectMask, String nameLayer, String typeGeomLayer, String descripLayer, String sourceLayer, String styleLayer, String dateLayer, String dateEditLayer, Project project) {
        this.idLayer = idLayer;
        this.idMaskLayer = idMaskLayer;
        this.idProjectMask = idProjectMask;
        this.nameLayer = nameLayer;
        this.typeGeomLayer = typeGeomLayer;
        this.descripLayer = descripLayer;
        this.sourceLayer = sourceLayer;
        this.styleLayer = styleLayer;
        this.dateLayer = dateLayer;
        this.dateEditLayer = dateEditLayer;
        this.project = project;
    }

    public int getIdLayer() {
        return idLayer;
    }

    public void setIdLayer(int idLayer) {
        this.idLayer = idLayer;
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

    public String getNameLayer() {
        return nameLayer;
    }

    public void setNameLayer(String nameLayer) {
        this.nameLayer = nameLayer;
    }

    public String getTypeGeomLayer() {
        return typeGeomLayer;
    }

    public void setTypeGeomLayer(String typeGeomLayer) {
        this.typeGeomLayer = typeGeomLayer;
    }

    public String getDescripLayer() {
        return descripLayer;
    }

    public void setDescripLayer(String descripLayer) {
        this.descripLayer = descripLayer;
    }

    public String getSourceLayer() {
        return sourceLayer;
    }

    public void setSourceLayer(String sourceLayer) {
        this.sourceLayer = sourceLayer;
    }

    public String getStyleLayer() {
        return styleLayer;
    }

    public void setStyleLayer(String styleLayer) {
        this.styleLayer = styleLayer;
    }

    public String getDateLayer() {
        return dateLayer;
    }

    public void setDateLayer(String dateLayer) {
        this.dateLayer = dateLayer;
    }

    public String getDateEditLayer() {
        return dateEditLayer;
    }

    public void setDateEditLayer(String dateEditLayer) {
        this.dateEditLayer = dateEditLayer;
    }

    public FolderOverlayL getOverlay() {
        return overlay;
    }

    public void setOverlay(FolderOverlayL overlay) {
        this.overlay = overlay;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public AStyle getaStyle() {
        return aStyle;
    }

    public void setaStyle(AStyle aStyle) {
        this.aStyle = aStyle;
    }

}
