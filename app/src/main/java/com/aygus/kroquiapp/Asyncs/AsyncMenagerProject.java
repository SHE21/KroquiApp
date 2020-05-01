package com.aygus.kroquiapp.Asyncs;

import android.os.AsyncTask;

import com.aygus.kroquiapp.Modelos.MensageDb;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.SqLite.DataBase;

/**
 * Created by SANTIAGO from AIGUS on 15/10/2018.
 */
public class AsyncMenagerProject extends AsyncTask<Project, Void, Project> {
    private DataBase dataBase;
    private HelperAsyncMenagerProject helperAsyncMenagerProject;
    private int typeCrud;

    public interface HelperAsyncMenagerProject{
        void onPreExecuteHelper();
        void onPosExecuteHelper(Project project);
    }

    public AsyncMenagerProject(int typeCrud,DataBase dataBase, HelperAsyncMenagerProject helperAsyncMenagerProject) {
        this.typeCrud = typeCrud;
        this.dataBase = dataBase;
        this.helperAsyncMenagerProject = helperAsyncMenagerProject;
    }

    @Override
    protected void onPreExecute() {
        helperAsyncMenagerProject.onPreExecuteHelper();
    }

    @Override
    protected Project doInBackground(Project... projects) {
        Project project = null;

        switch (this.typeCrud){
            case 1:
                long iProj = dataBase.setProject(projects[0]);
                if (iProj!=0) {
                    project = projects[0];
                }
                break;

            case 2:
                long dProj = dataBase.updateProject(projects[0]);
                if (dProj!=0){
                    project = projects[0];
                }
                break;

            case 3:
                long fProj = dataBase.deleteProject(projects[0]);
                if (fProj!=0){
                    project = projects[0];
                }
                break;
        }

        return project;
    }

    @Override
    protected void onPostExecute(Project project) {
        helperAsyncMenagerProject.onPosExecuteHelper(project);
    }
}
