package com.aygus.kroquiapp.Modelos;

import android.content.Context;

import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Utils.GenerateId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 23/12/2018.
 **/
public class Category implements Serializable{
    private int IDCategory;
    private String IDMaskCategory;
    private String nameCategory;
    private int totalLayerCategory;
    private String descripCategory;
    private boolean statusVisibility;
    private Date dateCreated;

    public static final String CATEGORY = "category";

    public Category(){}

    public Category(int IDCategory, String IDMaskCategory, String nameCategory, int totalLayerCategory, String descripCategory, boolean statusVisibility, Date dateCreated) {
        this.IDCategory = IDCategory;
        this.IDMaskCategory = IDMaskCategory;
        this.nameCategory = nameCategory;
        this.totalLayerCategory = totalLayerCategory;
        this.descripCategory = descripCategory;
        this.statusVisibility = statusVisibility;
        this.dateCreated = dateCreated;
    }

    public int getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(int IDCategory) {
        this.IDCategory = IDCategory;
    }

    public String getIDMaskCategory() {
        return IDMaskCategory;
    }

    public void setIDMaskCategory(String IDMaskCategory) {
        this.IDMaskCategory = IDMaskCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getTotalLayerCategory() {
        return totalLayerCategory;
    }

    public void setTotalLayerCategory(int totalLayerCategory) {
        this.totalLayerCategory = totalLayerCategory;
    }

    public String getDescripCategory() {
        return descripCategory;
    }

    public void setDescripCategory(String descripCategory) {
        this.descripCategory = descripCategory;
    }

    public boolean isStatusVisibility() {
        return statusVisibility;
    }

    public void setStatusVisibility(boolean statusVisibility) {
        this.statusVisibility = statusVisibility;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public static List<Category> getListCategory(Context context){
        List<Category> categoryList = new ArrayList<>();
        String[] categ = context.getResources().getStringArray(R.array.categorias);

        for (int i = 0; i < categ.length; i++){
            categoryList.add(new Category(i, GenerateId.generateKey(), categ[i], i, "descriptions", true, new Date()));
        }

        return categoryList;
    }

    public static String[] arrayCatrgory(List<Category> list){
        String[] strings = new String[list.size()];

        for (int i = 0; i < list.size();i++){
            strings[i] = list.get(i).getNameCategory();
        }

        return strings;
    }

    public static List<Category> getRegions(String[] strings){
        List<Category> list = new ArrayList<>();

        for (int i = 0; i < strings.length; i++){
            Category category = new Category();
            category.setNameCategory(strings[i]);
            category.setIDCategory(i);
            list.add(category);
        }

        return list;
    }
}
