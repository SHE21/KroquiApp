package com.aygus.kroquiapp.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Utils.DateApp;
import com.aygus.kroquiapp.Utils.GenerateId;

/**
 * Created by SANTIAGO from AIGUS on 14/10/2018.
 */
public class DialogMenagerProject extends DialogFragment {
    private HelperDialogProjectMg helperDialogProjectMg;
    private int typeCrud;
    private Project project;

    public void setHelperDialogProjectMg(HelperDialogProjectMg helperDialogProjectMg) {
        this.helperDialogProjectMg = helperDialogProjectMg;
    }

    public void setTypeCrud(int typeCrud) {
        this.typeCrud = typeCrud;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public interface HelperDialogProjectMg{
        void positiveResult(Project project);
        void negativeResult();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_menager_project, null);

        EditText nameProject = view.findViewById(R.id.nameProject);
        EditText descripProject = view.findViewById(R.id.descProject);
        Button btnCreate = view.findViewById(R.id.btnCreate);

        if (project==null){
            builder.setTitle(R.string.createProject);

        }else{
            btnCreate.setText(R.string.save);
            nameProject.setText(project.getNameProject());
            descripProject.setText(project.getDescripProject());
            builder.setTitle(R.string.editProject);
        }

        btnCreate.setOnClickListener(v -> {
            String sNameProject = nameProject.getText().toString();
            String sdescripProject = descripProject.getText().toString();

            if (!sNameProject.isEmpty()&&!sdescripProject.isEmpty()){
                if (sNameProject.length() < 20 && sdescripProject.length() < 100) {

                    if (project==null) {
                        Project project = new Project();
                        project.setIdMaskProject(GenerateId.generateId());
                        project.setDateCreatedProject(DateApp.getDate());
                        project.setNameProject(sNameProject);
                        project.setDateEditedProject("n");
                        project.setDescripProject(sdescripProject);
                        project.setIdUserProject("useruser");

                        helperDialogProjectMg.positiveResult(project);

                    }else{
                        project.setNameProject(sNameProject);
                        project.setDescripProject(sdescripProject);
                        project.setDateEditedProject(DateApp.getDate());
                        helperDialogProjectMg.positiveResult(project);
                    }

                }else{
                    Toast.makeText(getContext(), "Número de caracteres execedido.", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(getContext(), R.string.errorCampoBranco, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            helperDialogProjectMg.negativeResult();
        });

        builder.setIcon(R.drawable.ic_add_kroqui);

        builder.setView(view);
        return builder.create();
    }
}
