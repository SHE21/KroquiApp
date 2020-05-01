package com.aygus.kroquiapp.Asyncs;

import android.os.AsyncTask;

import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.SqLite.DataBase;

/**
 * Created by SANTIAGO from AIGUS on 04/12/2018.
 */
public class AsyncManagerLayer extends AsyncTask<Layer, Void, Layer> {
    private DataBase dataBase;
    private HelperAsyncManagerLayer helperAsyncManagerLayer;
    private int typeCrud;

    public static final int INSERT = 1;
    public static final int DELETE = 2;
    public static final int UPDATE = 3;
    public static final int DELETE_BY_PROJECT = 4;

    public AsyncManagerLayer(DataBase dataBase, HelperAsyncManagerLayer helperAsyncManagerLayer, int typeCrud) {
        this.dataBase = dataBase;
        this.helperAsyncManagerLayer = helperAsyncManagerLayer;
        this.typeCrud = typeCrud;
    }

    public interface HelperAsyncManagerLayer{
        void onPreExcuteHelper();
        void onPosExecute(Layer layer);
    }

    @Override
    protected void onPreExecute() {
        helperAsyncManagerLayer.onPreExcuteHelper();
    }

    @Override
    protected Layer doInBackground(Layer... layers) {
        Layer layer = null;

        switch (this.typeCrud){
            case 1:
                if (dataBase.setLayer(layers[0])!=0){
                    layer = layers[0];
                }
                break;

            case 2:
                if (dataBase.deleteLayer(layers[0])!=0){
                    layer = layers[0];
                }
                break;

            case 3:
                if (dataBase.updateLayer(layers[0])!=0){
                    layer = layers[0];
                }
                break;

            case 4:
                if (dataBase.deleteLayerByProject(layers[0].getProject())!=0){
                    layer = layers[0];
                }
                break;

                default:
                    layer = null;
                    break;
        }

        return layer;
    }

    @Override
    protected void onPostExecute(Layer layer) {
        helperAsyncManagerLayer.onPosExecute(layer);
    }
}
