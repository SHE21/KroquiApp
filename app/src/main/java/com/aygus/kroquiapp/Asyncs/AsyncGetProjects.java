package com.aygus.kroquiapp.Asyncs;

import android.os.AsyncTask;

import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.SqLite.DataBase;

import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 15/10/2018.
 */
public class AsyncGetProjects extends AsyncTask<String, Void, List<Project>>{
    private DataBase dataBase;
    private HelperAsyncGetProject helperAsyncMenagerProject;

    public AsyncGetProjects(DataBase dataBase, HelperAsyncGetProject helperAsyncMenagerProject) {
        this.dataBase = dataBase;
        this.helperAsyncMenagerProject = helperAsyncMenagerProject;
    }

    public interface HelperAsyncGetProject{
        void onPreExecuteHelper();
        void onPosExecuteHelper(List<Project> projectList);
    }

    @Override
    protected void onPreExecute() {
        helperAsyncMenagerProject.onPreExecuteHelper();
    }

    @Override
    protected List<Project> doInBackground(String... strings) {
        return dataBase.getProjects();
    }


    @Override
    protected void onPostExecute(List<Project> projectList) {
        helperAsyncMenagerProject.onPosExecuteHelper(projectList);
    }
}
