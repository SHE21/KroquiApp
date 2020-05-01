package com.aygus.kroquiapp.Modelos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 14/10/2018.
 */
public class Project implements Serializable{
    private int idProject;
    private String idMaskProject;
    private String nameProject;
    private String descripProject;
    private String idUserProject;
    private String dateCreatedProject;
    private String dateEditedProject;
    private List<String> listLayers;

    public Project(){}

    public Project(int idProject, String idMaskProject, String nameProject, String descripProject, String idUserProject, String dateCreatedProject, String dateEditedProject) {
        this.idProject = idProject;
        this.idMaskProject = idMaskProject;
        this.nameProject = nameProject;
        this.descripProject = descripProject;
        this.idUserProject = idUserProject;
        this.dateCreatedProject = dateCreatedProject;
        this.dateEditedProject = dateEditedProject;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getIdMaskProject() {
        return idMaskProject;
    }

    public void setIdMaskProject(String idMaskProject) {
        this.idMaskProject = idMaskProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDescripProject() {
        return descripProject;
    }

    public void setDescripProject(String descripProject) {
        this.descripProject = descripProject;
    }

    public String getIdUserProject() {
        return idUserProject;
    }

    public void setIdUserProject(String idUserProject) {
        this.idUserProject = idUserProject;
    }

    public String getDateCreatedProject() {
        return dateCreatedProject;
    }

    public void setDateCreatedProject(String dateCreatedProject) {
        this.dateCreatedProject = dateCreatedProject;
    }

    public String getDateEditedProject() {
        return dateEditedProject;
    }

    public void setDateEditedProject(String dateEditedProject) {
        this.dateEditedProject = dateEditedProject;
    }

    public List<String> getListLayers() {
        return listLayers;
    }

    public void setListLayers(List<String> listLayers) {
        this.listLayers = listLayers;
    }
}
