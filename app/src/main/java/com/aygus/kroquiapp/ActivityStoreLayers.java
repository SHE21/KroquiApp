package com.aygus.kroquiapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.aygus.kroquiapp.Adapters.AdapterListLayerStore;
import com.aygus.kroquiapp.Adapters.AdapterListTags;
import com.aygus.kroquiapp.Modelos.Category;
import com.aygus.kroquiapp.Modelos.LayerStore;
import com.aygus.kroquiapp.Utils.UtilsAndroid;

import java.util.ArrayList;
import java.util.List;

public class ActivityStoreLayers extends AppCompatActivity {
    private RecyclerView recyclerViewLayers, recyclerTags, recyCategory, recyclerRegion;
    private List<LayerStore> storeList;
    private String[] tags = new String[]{"Tudo","Saneamento básico"," Vegetação nativa","Erosão","Aterro Sanitário","Coleta de Lixo", "Coleta Seletiva", "Reciclagem"};
    private String[] regions = new String[]{"Tudo","Brasil","Uruguai","Bolivia","Argentina","Colombia", "Equador", "Chile"};
    private ImageView loaddingQuery;

    private int spanRows = 2;
    private Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_layers);
        Category category = (Category) getIntent().getSerializableExtra(Category.CATEGORY);
        Toolbar toolbar =  findViewById(R.id.toolbarStore);
        toolbar.setTitle(category.getNameCategory());
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Point point = UtilsAndroid.getDimensDisplay(this);
        int width = point.x;

        if (width > 480){
            spanRows = 3;
        }

        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), spanRows);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewLayers = findViewById(R.id.recyclerViewLayers);
        recyclerViewLayers.setLayoutManager(layoutManager);
        recyclerViewLayers.setHasFixedSize(true);
        recyclerViewLayers.setNestedScrollingEnabled(false);
        recyclerViewLayers.setItemAnimator(new DefaultItemAnimator());

        storeList = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            LayerStore layer = new LayerStore();
            layer.setTitle("Escolas Estaduais de Ensino - MA " + i);
            layer.setCategory("Category " + i);
            layer.setTypeGeom("point");
            layer.setPrize(230.0);
            storeList.add(layer);
        }

        int adapter = R.layout.adapter_item_product_layer;
        AdapterListLayerStore layerStore = new AdapterListLayerStore(storeList, onClickItemStore(), getBaseContext(), adapter);
        recyclerViewLayers.setAdapter(layerStore);

        recyclerTags = findViewById(R.id.recyclerTags);
        recyclerTags.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerTags.setHasFixedSize(true);
        recyclerTags.setNestedScrollingEnabled(false);
        recyclerTags.setItemAnimator(new DefaultItemAnimator());

        AdapterListTags adapterListTags = new AdapterListTags(tags,onClickItemTag());
        recyclerTags.setAdapter(adapterListTags);

        recyCategory = findViewById(R.id.recyCategory);
        recyCategory.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL,false));
        recyCategory.setHasFixedSize(true);
        recyCategory.setItemAnimator(new DefaultItemAnimator());

        AdapterListTags adapterTagsCategory = new AdapterListTags(Category.arrayCatrgory(Category.getListCategory(getBaseContext())),onClickItemTagCategory());
        recyCategory.setAdapter(adapterTagsCategory);

        recyclerRegion = findViewById(R.id.recyclerRegion);
        recyclerRegion.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerRegion.setHasFixedSize(true);
        recyclerRegion.setItemAnimator(new DefaultItemAnimator());

        AdapterListTags adapterRegion = new AdapterListTags(regions,onClickItemRegion());
        recyclerRegion.setAdapter(adapterRegion);

    }

    private AdapterListTags.OnClickItemTag onClickItemTagCategory(){
        return string -> Toast.makeText(this, " " + string, Toast.LENGTH_SHORT).show();
    }

    private AdapterListTags.OnClickItemTag onClickItemTag(){
        return string ->
            Toast.makeText(this, " " + string, Toast.LENGTH_SHORT).show();
    }

    private AdapterListTags.OnClickItemTag onClickItemRegion(){
        return string ->
                Toast.makeText(this, " " + string, Toast.LENGTH_SHORT).show();
    }
    private AdapterListLayerStore.OnClickItemStore onClickItemStore(){
        return layerStore -> {
            Intent intent =  new Intent(this, ActivityStoreDetails.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(LayerStore.LAYER, layerStore);
            intent.putExtras(bundle);
            startActivity(intent);
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean b){
        super.onWindowFocusChanged(b);

    }
}
