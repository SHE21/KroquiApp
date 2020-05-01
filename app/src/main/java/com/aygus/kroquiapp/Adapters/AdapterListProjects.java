package com.aygus.kroquiapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 14/10/2018.
 */
public class AdapterListProjects extends RecyclerView.Adapter<AdapterListProjects.ViewHolder> {
    private Context context;
    private List<Project> listProject;
    private OnClickItemProject onClickItemProject;

    public AdapterListProjects(Context context, List<Project> listProject, OnClickItemProject onClickItemProject){
        this.context = context;
        this.listProject = listProject;
        this.onClickItemProject = onClickItemProject;

    }

    public interface OnClickItemProject{
        void onClickItem(View view,Project project, int position);
        void onClickEdit(View view,Project project, int position);
        void onClickDelete(View view,Project project, int position);
        void onClickInfo(View view,Project project, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Project project = listProject.get(position);
        holder.title.setText(project.getNameProject());
        holder.subTitle.setText(project.getDateCreatedProject());

        holder.itemProject.setOnClickListener(v -> onClickItemProject.onClickItem(v, project, position));
        holder.btnDelete.setOnClickListener(v -> onClickItemProject.onClickDelete(v, project, position));
        holder.btnEdit.setOnClickListener(v -> onClickItemProject.onClickEdit(v, project, position));
        holder.btnInfo.setOnClickListener(v -> onClickItemProject.onClickInfo(v, project, position));

    }

    @Override
    public int getItemCount() {
        return this.listProject != null ? this.listProject.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout itemProject;
        private ImageButton btnEdit, btnDelete, btnInfo;
        private TextView title, subTitle;
        private View view;

        ViewHolder(View view) {
            super(view);
            this.view = itemView;

            itemProject = view.findViewById(R.id.itemProject);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnInfo = view.findViewById(R.id.btnInfo);

            title = view.findViewById(R.id.title);
            subTitle = view.findViewById(R.id.subTitle);
        }
    }

    public void setFilter(List<Project> projectArrayList){
        listProject.addAll(projectArrayList);
        notifyDataSetChanged();

    }

    public void notifyDataChange(){
        notifyDataSetChanged();
    }
}
