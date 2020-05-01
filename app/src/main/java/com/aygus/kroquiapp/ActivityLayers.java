package com.aygus.kroquiapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aygus.kroquiapp.Adapters.AdapterListLayers;
import com.aygus.kroquiapp.Asyncs.AsyncLoadFileToMap;
import com.aygus.kroquiapp.Asyncs.AsyncManagerLayer;
import com.aygus.kroquiapp.Dialogs.DialogImportLayer;
import com.aygus.kroquiapp.Fragments.FragManagerLayers;
import com.aygus.kroquiapp.Fragments.FragManagerTableAttributes;
import com.aygus.kroquiapp.Fragments.FragMapView;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Modelos.LayerStore;
import com.aygus.kroquiapp.Modelos.Project;
import com.aygus.kroquiapp.SqLite.DataBase;
import com.aygus.kroquiapp.Utils.StylerGeneral;
import com.aygus.kroquiapp.Utils.ToastCostum;
import com.aygus.kroquiapp.Utils.UtilsAndroid;
import com.aygus.kroquiapp.Utils.ViewPagerAdapter;
import com.google.gson.Gson;

import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayManager;

import java.util.ArrayList;
import java.util.List;

public class ActivityLayers extends AppCompatActivity implements AdapterListLayers.OnClickItemLayer, FragManagerLayers.OnListenerManagerFragment {
    private Project project;
    private Toolbar toolbarToLayer, toolbar;
    private View resize;
    private CardView boxLayersTables;
    private Layer selectedLayer = null;
    private Menu menu;

    private MapView mapView;
    private MapController mapController;
    private OverlayManager overlayManager;

    private FragManagerLayers fragManagerLayers;
    private FragManagerTableAttributes tableAttibutes;
    private FragMapView fragMapView;
    private DialogImportLayer dialogImportLayer;

    private DataBase dataBase;

    private Activity getActivity(){
        return this;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layers);

        project = (Project)getIntent().getSerializableExtra("project");
        dataBase = new DataBase(getBaseContext());

        Log.d("PROJECT", " - > " + project.getIdMaskProject());

        toolbar = findViewById(R.id.toolbar);
        toolbar.getWidth();
        toolbar.setTitle(project.getNameProject());
        setSupportActionBar(toolbar);

        toolbarToLayer = findViewById(R.id.toolbarToLayer);
        toolbarToLayer.setTitle("Layers (0)");
        toolbarToLayer.inflateMenu(R.menu.menu_frag_manager_layers);
        toolbarToLayer.setOnMenuItemClickListener(menuItemClickListener());
        menu = toolbarToLayer.getMenu();

        boxLayersTables = findViewById(R.id.boxLayersTables);
        resize = findViewById(R.id.resize);
        resize.setOnClickListener(v -> v.setBackgroundColor(Color.GREEN));
        resize.setOnTouchListener(onTouchListenerToolBar());


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewPager viewPager = findViewById(R.id.viewpagerLayers);
        viewPager.addOnPageChangeListener(onPageChangeListener());

        TabLayout tabLayout = findViewById(R.id.tabsLayers);
        tabLayout.setupWithViewPager(viewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragMapView = FragMapView.newInstance("0", "0");
        fragmentTransaction.add(R.id.contMapView, fragMapView);
        fragmentTransaction.commit();

        tableAttibutes = FragManagerTableAttributes.newInstance("123", "123");
        fragManagerLayers = FragManagerLayers.newInstance(project);

        adapter.addFragment(fragManagerLayers, "Camadas");
        adapter.addFragment(tableAttibutes, "Tabela");
        viewPager.setAdapter(adapter);
//        Point point = UtilsAndroid.getDimensDisplay(this);
//        ToastCostum toast = new ToastCostum("Width: " + point.x + " height: " + point.y, this, Toast.LENGTH_LONG, ToastCostum.SUCESSE);
//        toast.show();
    }

    private View.OnTouchListener onTouchListenerToolBar(){
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View s, MotionEvent event) {
                int x=(int)event.getX();
                int y=(int)event.getY();
                int width= boxLayersTables.getLayoutParams().width;
                int height = boxLayersTables.getLayoutParams().height;

                int h = Integer.parseInt(String.valueOf(y).replace("-", ""));
                int p;
                Point point = UtilsAndroid.getDimensDisplay(getActivity());


                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_UP:

                            break;
                        case MotionEvent.ACTION_MOVE:
                            p = h + height;
                            if (point.y-103 < height) {
                                boxLayersTables.getLayoutParams().height = p;
                                boxLayersTables.requestLayout();
                            }
                            break;
                    }

                Log.e(">>","width:"+width+" height:"+height+" x:"+x+" y:"+y + " TOOLBAR " + toolbar.getLayoutParams().height);
                return true;
            }
        };
    }

    private ViewPager.OnPageChangeListener onPageChangeListener(){
        return new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        menu.findItem(R.id.action_add_layer).setEnabled(true).setIcon(R.drawable.ic_add_enable_24dp);
                        if (selectedLayer!=null){menu.findItem(R.id.action_delete_layer).setEnabled(true).setIcon(R.drawable.ic_delete_enable_24dp);}
                        break;

                    case 1:
                        menu.findItem(R.id.action_add_layer).setEnabled(false).setIcon(R.drawable.ic_add_gray_24dp);
                        menu.findItem(R.id.action_delete_layer).setEnabled(false).setIcon(R.drawable.ic_delete_gray_24dp);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    private AsyncManagerLayer.HelperAsyncManagerLayer helperAsyncManagerLayer(){
        return new AsyncManagerLayer.HelperAsyncManagerLayer() {
            @Override
            public void onPreExcuteHelper() {

            }

            @Override
            public void onPosExecute(Layer layer) {
                String msn = null;
                int status = -1;

                if (layer!=null){
                    msn = "Camada salva ";
                    status = ToastCostum.SUCESSE;
                    //////

                    fragManagerLayers.addLayerInList(layer);

                    List<Layer> layers = new ArrayList<>();
                    layers.add(layer);


                }else{
                    msn = "Não foi possÍvel salvar no banco de dados";
                    status = ToastCostum.ERROR;
                }

                ToastCostum toastCostum = new ToastCostum(msn, getActivity(), Toast.LENGTH_SHORT, status);
                toastCostum.show();
            }
        };
    }

    private Toolbar.OnMenuItemClickListener menuItemClickListener(){
        return item -> {
            switch (item.getItemId()) {
                case R.id.action_add_layer :
                    dialogImportLayer = DialogImportLayer.newInstance(getSupportFragmentManager(), project);
                    break;

                case R.id.action_delete_layer:
                    break;

                case R.id.action_expander_layer:
                    ViewGroup.LayoutParams lp = boxLayersTables.getLayoutParams();
                    lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    boxLayersTables.setLayoutParams(lp);
                    boxLayersTables.setForegroundGravity(Gravity.TOP);

                    break;
            }
            return true;
        };
    }

    private void getLayers(Project project){
        AsyncLoadFileToMap loadKml = new AsyncLoadFileToMap(mapView, dataBase, helperLoaderKml(), new StylerGeneral(getBaseContext()), getBaseContext());
        loadKml.setMapView(mapView);
        loadKml.execute(project);
    }

    private AsyncLoadFileToMap.HelperLoaderKml helperLoaderKml(){
        return new AsyncLoadFileToMap.HelperLoaderKml() {
        @Override
        public void onPreExecute() {

        }

        @Override
        public void onPosExecute(List<Layer> layerList) {

            if (layerList != null) {

                if (layerList.size()!=0) {
                    fragMapView.addLayerOnMap(layerList);
                    fragManagerLayers.addAllLayer(layerList);

                }else{

                }

            }else{

                ToastCostum.toast("Ocorreu um erro ao ler o aquivo. ERROR[001]", getActivity(),Toast.LENGTH_LONG, ToastCostum.ERROR).show();
            }
        }
    };}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_layers, menu);
        // menu search
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

                case R.id.action_store:
                    Intent intent = new Intent(this, ActivityStore.class);
                    startActivity(intent);
                    break;
        }
        return true;
    }

    @Override
    public void onClickItem(View view, Layer layer, int position) {
        selectedLayer = layer;
        menu.findItem(R.id.action_delete_layer).setEnabled(true).setIcon(R.drawable.ic_delete_enable_24dp);
        fragMapView.goToLayer(layer, position);
        tableAttibutes.setDataLayer(layer);

    }

    @Override
    public void onClickStyleDraw(View view, Layer layer) {
        Intent intent = new Intent(getBaseContext(), ActivityStyleLayer.class);
        intent.putExtra(LayerStore.LAYER, layer);
        startActivity(intent);

    }

    @Override
    public void onClickMoreOptions(View view, Layer layer) {

    }

    @Override
    public void onClickCheck(View view, Layer layer, int position) {

    }

    @Override
    public void resultDialogAddLayer(Layer layer) {
        if (layer!=null) {
            dialogImportLayer.dismiss();
            Log.d("PROJECT", " - > " + new Gson().toJson(layer));
            AsyncManagerLayer managerLayer = new AsyncManagerLayer(dataBase, helperAsyncManagerLayer(), AsyncManagerLayer.INSERT);
            managerLayer.execute(layer);
        }
    }

    @Override
    public void layersFromManager(List<Layer> layerList) {
        toolbarToLayer.setTitle("Layers (" + layerList.size()+")");
    }

    @Override
    public void openDialogAddLayer() {
        dialogImportLayer = DialogImportLayer.newInstance(getSupportFragmentManager(), project);
    }

    @Override
    public void closeDialogAddLayer() {

    }

    @Override
    public void fragCreated() {
        getLayers(project);
    }
}
