package com.aygus.kroquiapp.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aygus.kroquiapp.Fragments.FragCreateDataBase;
import com.aygus.kroquiapp.Fragments.FragCreateFile;
import com.aygus.kroquiapp.Fragments.FragCreateOnMap;
import com.aygus.kroquiapp.Fragments.FragManagerLayers;
import com.aygus.kroquiapp.Interfaces.CallBackImportLayers;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.Utils.ViewPagerAdapter;
import com.aygus.kroquiapp.R;

import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by SANTIAGO from AIGUS on 14/10/2018.
 */
public class DialogImportLayer extends DialogFragment {
    private FragManagerLayers.OnListenerManagerFragment onListenerManagerFragment;
    private FragManagerLayers.OnListenerManagerFragment onListenerManagerFragment2;
    private MapView mapViewPreview;
    private Button btnCreateLayer;
    private Layer layer1;
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static DialogImportLayer newInstance(FragmentManager fragmentManager, Project project){
        DialogImportLayer dialogImportLayer = new DialogImportLayer();
        dialogImportLayer.setProject(project);
        dialogImportLayer.setStyle(DialogFragment.STYLE_NORMAL, R.style.styleDialog);
        dialogImportLayer.show(fragmentManager, null);

        return dialogImportLayer;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialog_import_layer, null);
        setHasOptionsMenu(true);

        mapViewPreview = view.findViewById(R.id.mapViewPreview);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_frag_import_layer);
        toolbar.setOnMenuItemClickListener(menuItemClickListener());
        toolbar.setTitle("Nova Camada");

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        btnCreateLayer = view.findViewById(R.id.btnCreateLayer);

        btnCreateLayer.setOnClickListener(v -> onListenerManagerFragment.resultDialogAddLayer(layer1));
        return view;
    }

    private CallBackImportLayers callBackImportLayers(){
        return layer -> {
            layer1 = layer;
        };
    }

    private Toolbar.OnMenuItemClickListener menuItemClickListener(){
        return item -> {
            switch (item.getItemId()) {
                case R.id.action_close :
                    dismiss();
            }
            return true;
        };
    }

    private void setupViewPager (ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(FragCreateFile.newInstance(callBackImportLayers(), project), "Arquivo");
        adapter.addFragment(new FragCreateOnMap(), "Criar");
        adapter.addFragment(new FragCreateDataBase(), "Banco de dados");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragManagerLayers.OnListenerManagerFragment) {
            onListenerManagerFragment = (FragManagerLayers.OnListenerManagerFragment) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onListenerManagerFragment = null;
    }
}
