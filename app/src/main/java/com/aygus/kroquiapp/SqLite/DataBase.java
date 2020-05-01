package com.aygus.kroquiapp.SqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.Project;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SANTIAGO from AYGUS on 15/10/2018.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "kroquiDb";
    private static final String PROJECT = "project";
    private static final String LAYER = "layer";
    private static int DB_VERSION = 1;

    private static final String LOG = "DATA_BASE";

    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qProject = "CREATE TABLE project (idProject INTEGER PRIMARY KEY," +
                " idMaskProject TEXT," +
                " nameProject TEXT," +
                " descripProject TEXT," +
                " idUserProject TEXT," +
                " dateCreatedProject TEXT," +
                " dateEditedProject TEXT)";

        String qLayer = "CREATE TABLE layer (idLayer INTEGER PRIMARY KEY," +
                " idMaskLayer TEXT," +
                " idProjectMask TEXT," +
                " nameLayer TEXT," +
                " typeGeomLayer TEXT," +
                " descripLayer TEXT," +
                " sourceLayer TEXT," +
                " styleLayer TEXT," +
                " dateLayer TEXT," +
                " dateEditLayer TEXT)";

        String qLicense = "CREATE TABLE license (idLicense INTEGER PRIMARY KEY," +
                " idMaskLicense TEXT," +
                " idKeyLicense TEXT, idStoreLicense TEXT," +
                " idControllerLicense INTEGER," +
                " idUserLicense TEXT," +
                " statusLicense BOOLEAN," +
                " dominusLicense TEXT," +
                " nDivices INTEGER," +
                " subLicense INTEGER," +
                " dateLicense TEXT)";

        String qUser = "CREATE TABLE user (id INTEGER PRIMARY KEY," +
                " idMaskUser TEXT," +
                " firstName TEXT," +
                " lastName TEXT," +
                " generus TEXT," +
                " birthDay TEXT," +
                " email TEXT," +
                " country TEXT," +
                " stated TEXT," +
                " city TEXT," +
                " profession TEXT," +
                " institution TEXT," +
                " pass TEXT," +
                " date TEXT," +
                " dateEdit TEXT)";

        db.execSQL(qProject);
        db.execSQL(qLayer);
        db.execSQL(qLicense);
        db.execSQL(qUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //inseri um projeto no bd
    public long setProject(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idMaskProject", project.getIdMaskProject());
        values.put("nameProject", project.getNameProject());
        values.put("descripProject", project.getDescripProject());
        values.put("idUserProject", project.getIdUserProject());
        values.put("dateCreatedProject", project.getDateCreatedProject());
        values.put("dateEditedProject", project.getDateEditedProject());

        long i = db.insert(PROJECT, null, values);
        db.close();
        return i;
    }

    public List<Project> getProjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Project> projectList = new ArrayList<>();
        String query = "SELECT*FROM " + PROJECT;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Project project = new Project();
                project.setIdProject(cursor.getInt(0));
                project.setIdMaskProject(cursor.getString(1));
                project.setNameProject(cursor.getString(2));
                project.setDescripProject(cursor.getString(3));
                project.setIdUserProject(cursor.getString(4));
                project.setDateCreatedProject(cursor.getString(5));
                project.setDateEditedProject(cursor.getString(6));

                projectList.add(project);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return projectList;
    }

    public int updateProject(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put("idMaskProject", project.getIdMaskProject());
        values.put("nameProject", project.getNameProject());
        values.put("descripProject", project.getDescripProject());
        //values.put("idUserProject", project.getIdUserProject());
        //values.put("dateCreatedProject", project.getDateCreatedProject());
        values.put("dateEditedProject", project.getDateEditedProject());

        int i = db.update(PROJECT, values, "idMaskProject = ?", new String[]{project.getIdMaskProject()});
        db.close();

        return i;
    }

    public int deleteProject(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        int i =  db.delete(PROJECT, "idMaskProject = ?", new String[]{project.getIdMaskProject()});
        db.close();
        return i;
    }

    public long setLayer(Layer layer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idMaskLayer", layer.getIdMaskLayer());//1
        values.put("idProjectMask", layer.getIdProjectMask());//2
        values.put("nameLayer", layer.getNameLayer());//3
        values.put("typeGeomLayer", layer.getTypeGeomLayer());//4
        values.put("descripLayer", layer.getDescripLayer());//5
        values.put("sourceLayer", layer.getSourceLayer());//6
        values.put("dateLayer", layer.getDateLayer());//7
        values.put("dateEditLayer", layer.getDateEditLayer());//8

        long i = db.insert(LAYER, null, values);
        db.close();
        return i;
    }

    public List<Layer> getLayers(Project project) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Layer> layerList = new ArrayList<>();

        String query = "SELECT*FROM " + LAYER + " WHERE idProjectMask = '" + project.getIdMaskProject()+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Layer layer = new Layer();
                layer.setIdLayer(cursor.getInt(0));
                layer.setIdMaskLayer(cursor.getString(1));
                layer.setIdProjectMask(cursor.getString(2));
                layer.setNameLayer(cursor.getString(3));
                layer.setTypeGeomLayer(cursor.getString(4));
                layer.setDescripLayer(cursor.getString(5));
                layer.setSourceLayer(cursor.getString(6));
                layer.setDateLayer(cursor.getString(7));
                layer.setDateEditLayer(cursor.getString(8));
                layer.setProject(project);

                layerList.add(layer);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return layerList;
    }

    public List<Layer> getAllLayers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Layer> layerList = new ArrayList<>();

        String query = "SELECT*FROM " + LAYER ;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Layer layer = new Layer();
                layer.setIdLayer(cursor.getInt(0));
                layer.setIdMaskLayer(cursor.getString(1));
                layer.setIdProjectMask(cursor.getString(2));
                layer.setNameLayer(cursor.getString(3));
                layer.setTypeGeomLayer(cursor.getString(4));
                layer.setDescripLayer(cursor.getString(5));
                layer.setSourceLayer(cursor.getString(6));
                layer.setDateLayer(cursor.getString(7));
                layer.setDateEditLayer(cursor.getString(8));

                layerList.add(layer);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return layerList;
    }

    public int deleteLayer(Layer layer){
        SQLiteDatabase db = this.getWritableDatabase();
        int i =  db.delete(LAYER, "idMaskLayer = ?", new String[]{layer.getIdMaskLayer()});
        db.close();
        return i;
    }

    public int deleteLayerByProject(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        int i =  db.delete(LAYER, "idProjectMask = ?", new String[]{project.getIdMaskProject()});
        db.close();
        return i;
    }

    public int updateLayer(Layer layer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nameLayer", layer.getNameLayer());
        values.put("descripLayer", layer.getDescripLayer());
        values.put("dateEditLayer", layer.getDateEditLayer());

        int i = db.update(LAYER, values, "idMaskProject = ?", new String[]{layer.getIdMaskLayer()});
        db.close();

        return i;

    }

//    " idMaskLayer TEXT," +
//            " idProjectMask TEXT," +
//            " nameLayer TEXT," +
//            " typeGeomLayer TEXT," +
//            " descripLayer TEXT," +
//            " sourceLayer TEXT," +
//            " styleLayer TEXT," +
//            " dateLayer TEXT," +
//            " dateEditLayer TEXT)";
}
