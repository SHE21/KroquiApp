package com.aygus.kroquiapp.Asyncs;

import android.os.AsyncTask;

import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.SqLite.DataBase;

import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 15/10/2018.
 */
public class AsyncGetLayers extends AsyncTask<Project, Void, List<Layer>>{
    private DataBase dataBase;
    private HelperAsyncGetLayer helperAsyncGetLayer;

    public AsyncGetLayers(DataBase dataBase, HelperAsyncGetLayer helperAsyncGetLayer) {
        this.dataBase = dataBase;
        this.helperAsyncGetLayer = helperAsyncGetLayer;
    }

    public interface HelperAsyncGetLayer{
        void onPreExecuteHelper();
        void onPosExecuteHelper(List<Layer> layerList);
    }

    @Override
    protected void onPreExecute() {
        helperAsyncGetLayer.onPreExecuteHelper();
    }

    @Override
    protected List<Layer> doInBackground(Project... projects) {
        return dataBase.getLayers(projects[0]);
    }


    @Override
    protected void onPostExecute(List<Layer> layerList) {
        helperAsyncGetLayer.onPosExecuteHelper(layerList);

    }
}
