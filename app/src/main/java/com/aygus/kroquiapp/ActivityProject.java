package com.aygus.kroquiapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aygus.kroquiapp.Adapters.AdapterListProjects;
import com.aygus.kroquiapp.Asyncs.AsyncGetProjects;
import com.aygus.kroquiapp.Asyncs.AsyncMenagerProject;
import com.aygus.kroquiapp.Dialogs.DialogMenagerProject;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.SqLite.DataBase;
import com.aygus.kroquiapp.Utils.ToastCostum;
import com.aygus.kroquiapp.Utils.UtilsAndroid;

import java.util.ArrayList;
import java.util.List;

public class ActivityProject extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private AdapterListProjects adapterListProjects;
    private DataBase dataBase;
    private DialogMenagerProject dmp = new DialogMenagerProject();
    private boolean contFab = true;

    private List<Project> projectList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Santiago");
        setSupportActionBar(toolbar);

        dataBase = new DataBase(getBaseContext());
        getProjects();

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator( new DefaultItemAnimator());

        dmp.setCancelable(false);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            if (contFab) {
                dmp.setHelperDialogProjectMg(dialogMenagerProject());
                dmp.setProject(null);
                dmp.show(getSupportFragmentManager(), null);
                contFab = false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

        Point point = UtilsAndroid.getDimensDisplay(this);
        ToastCostum toast = new ToastCostum("Ocorreu um erro, tente mais tarde!", this, Toast.LENGTH_LONG, ToastCostum.SUCESSE);
        toast.show();
        //Toast.makeText(this, "Width: " + point.x + " height: " + point.y, Toast.LENGTH_SHORT).show();
    }

    private DialogMenagerProject.HelperDialogProjectMg dialogMenagerProject(){
        return new DialogMenagerProject.HelperDialogProjectMg() {
            @Override
            public void positiveResult(Project project) {
                contFab = true;
                dmp.dismiss();

                AsyncMenagerProject menagerProject = new AsyncMenagerProject(1, dataBase, helperAsyncMenagerProject());
                menagerProject.execute(project);
            }

            @Override
            public void negativeResult() {
                contFab = true;
                dmp.dismiss();
            }
        };
    }

    private AsyncMenagerProject.HelperAsyncMenagerProject helperAsyncMenagerProjectUpdate(){
        return new AsyncMenagerProject.HelperAsyncMenagerProject() {
            @Override
            public void onPreExecuteHelper() {
                progressDialog.setMessage("Salvando ...");
                progressDialog.show();
            }

            @Override
            public void onPosExecuteHelper(Project project) {
                progressDialog.dismiss();
                if (project!=null) {
                    int index = projectList2.indexOf(project);
                    projectList2.remove(index);
                    projectList2.add(index, project);
                    adapterListProjects.notifyDataChange();
                    Toast.makeText(ActivityProject.this, "saved", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ActivityProject.this, "Não foi spossivel criar o projeto", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private DialogMenagerProject.HelperDialogProjectMg dialogMenagerProjectUpdate(){
        return new DialogMenagerProject.HelperDialogProjectMg() {
            @Override
            public void positiveResult(Project project) {
                contFab = true;
                dmp.dismiss();

                AsyncMenagerProject menagerProject = new AsyncMenagerProject(2, dataBase, helperAsyncMenagerProjectUpdate());
                menagerProject.execute(project);
            }

            @Override
            public void negativeResult() {
                contFab = true;
                dmp.dismiss();
            }
        };
    }

    private AsyncMenagerProject.HelperAsyncMenagerProject helperAsyncMenagerProject(){
        return new AsyncMenagerProject.HelperAsyncMenagerProject() {
            @Override
            public void onPreExecuteHelper() {
                progressDialog.setMessage("Criando projeto ...");
                progressDialog.show();
            }

            @Override
            public void onPosExecuteHelper(Project project) {
                progressDialog.dismiss();
                if (project!=null) {
                    projectList2.add(project);
                    adapterListProjects.notifyDataChange();

                }else{
                    Toast.makeText(ActivityProject.this, "Não foi spossivel criar o projeto", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private AdapterListProjects.OnClickItemProject onClickItemProject(){
        return new AdapterListProjects.OnClickItemProject() {
            @Override
            public void onClickItem(View view, Project project, int position) {
                Intent intent = new Intent(getBaseContext(), ActivityLayers.class);
                intent.putExtra("project", project);
                startActivity(intent);
            }

            @Override
            public void onClickEdit(View view, Project project, int position) {
                if (contFab) {
                    dmp.setHelperDialogProjectMg(dialogMenagerProjectUpdate());
                    dmp.setProject(project);
                    dmp.show(getSupportFragmentManager(), null);
                }
            }

            @Override
            public void onClickDelete(View view, Project project, int position) {
                onCreateDialogDelete(project);
            }

            @Override
            public void onClickInfo(View view, Project project, int position) {
                onCreateDialogInfo(project);
            }
        };
    }

    private void getProjects(){
        AsyncGetProjects getProjects = new AsyncGetProjects(dataBase, helperAsyncGetProject());
        getProjects.execute();
    }

    private AsyncGetProjects.HelperAsyncGetProject helperAsyncGetProject(){
        return new AsyncGetProjects.HelperAsyncGetProject() {
            @Override
            public void onPreExecuteHelper() {

            }

            @Override
            public void onPosExecuteHelper(List<Project> projectList) {
                progressBar.setVisibility(View.GONE);
                projectList2 = projectList;
                adapterListProjects = new AdapterListProjects(getBaseContext(), projectList2, onClickItemProject());
                recyclerView.setAdapter(adapterListProjects);
            }
        };
    }

    private void onCreateDialogDelete(Project project){
        DialogInterface.OnClickListener dialogInterface = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    AsyncMenagerProject menagerProject = new AsyncMenagerProject(3, dataBase, mHelperDeleteProject());
                    menagerProject.execute(project);

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.deleteProject);
        String msn = getResources().getString(R.string.MsnDeleteProject)+ " " + '"' + project.getNameProject() + '"';
        alertDialog.setMessage(msn);
        alertDialog.setPositiveButton(R.string.delete, dialogInterface);
        alertDialog.setNegativeButton(R.string.cancel, dialogInterface);
        alertDialog.show();
    }

    private void onCreateDialogInfo(Project project){
        DialogInterface.OnClickListener dialogInterface = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(project.getNameProject());

        String edited = "";
        if(!project.getDateEditedProject().equals("n")){ edited = "edited on day: " + project.getDateEditedProject() + " \n";}
        alertDialog.setMessage(edited + project.getDescripProject());

        alertDialog.setPositiveButton(R.string.ok, dialogInterface);
        alertDialog.show();
    }

    private AsyncMenagerProject.HelperAsyncMenagerProject mHelperDeleteProject(){
        return new AsyncMenagerProject.HelperAsyncMenagerProject() {
            @Override
            public void onPreExecuteHelper() {
                progressDialog.show();
                progressDialog.setMessage(getResources().getString(R.string.deleting));

            }

            @Override
            public void onPosExecuteHelper(Project project) {
                progressDialog.dismiss();
                if (project!=null){
                    projectList2.remove(project);
                    adapterListProjects.notifyDataChange();

                }else{
                    Toast.makeText(ActivityProject.this, "Não foi possivel excluir", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_project, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, ActivitySettings.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<Project> list = new ArrayList<>();

        for (Project project : projectList2){
            String proj = project.getNameProject().toLowerCase();
            if (proj.contains(newText)){
                list.add(project);
            }
        }

        adapterListProjects.setFilter(list);
        return true;
    }

}
