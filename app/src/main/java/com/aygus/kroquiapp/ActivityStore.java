package com.aygus.kroquiapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aygus.kroquiapp.Adapters.AdapterListCategory;
import com.aygus.kroquiapp.Modelos.BannerStore;
import com.aygus.kroquiapp.Modelos.Category;
import com.aygus.kroquiapp.Utils.UtilsAndroid;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class ActivityStore extends AppCompatActivity {
    private RecyclerView recyclerView, recyRegion;
    private CarouselView carouselTags;
    private String[] tags = new String[]{"Saneamento básico"," Vegetação nativa","Erosão","Aterro Sanitário","Coleta de Lixo"};
    private ProgressBar progStore;
    private int spanRows = 2;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Layers Store");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progStore = findViewById(R.id.progStore);

        Point point = UtilsAndroid.getDimensDisplay(this);
        int width = point.x;

        if (width > 540){
            spanRows = 3;
        }

        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), spanRows);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        int[] imgs = new int[]{
                R.drawable.ic_category_geology,
                R.drawable.ic_category_hidrology,
                R.drawable.ic_category_veget,
                R.drawable.ic_category_infra,
        };

        AdapterListCategory adapterListCategory = new AdapterListCategory(Category.getListCategory(getBaseContext()), onClickItem(), imgs, getBaseContext());
        recyclerView.setAdapter(adapterListCategory);

        LinearLayoutManager layoutMRecyRegion = new GridLayoutManager(getBaseContext(), spanRows);
        layoutMRecyRegion.setOrientation(LinearLayoutManager.VERTICAL);

        recyRegion = findViewById(R.id.recyRegion);
        recyRegion.setLayoutManager(layoutMRecyRegion);
        recyRegion.setHasFixedSize(true);
        recyRegion.setNestedScrollingEnabled(false);
        recyRegion.setItemAnimator(new DefaultItemAnimator());

        int[] ic_regions = new int[]{
                R.drawable.ic_cont_africa,
                R.drawable.ic_cont_asia,
                R.drawable.ic_cont_australia,
                R.drawable.ic_cont_europe,
                R.drawable.ic_cont_north_america,
                R.drawable.ic_cont_south_america
        };

        //int[] ic_regions = getBaseContext().getResources().getIntArray(R.array.icons_contnents);
        String[] n_regions = getBaseContext().getResources().getStringArray(R.array.names_continents);

        AdapterListCategory adapterListRegion = new AdapterListCategory(Category.getRegions(n_regions), onClickItem(), ic_regions, getBaseContext());
        recyRegion.setAdapter(adapterListRegion);

        //List<BannerStore> list = BannerStore.getStyle(getResources());
        //setDataBanner(GenerateId.gBanner(list));

        carouselTags = findViewById(R.id.carouselTags);
        carouselTags.setPageCount(4);
        carouselTags.setViewListener(viewListener(tags));
    }

    private ViewListener viewListener(String[] tags){
        return position -> {
            View custumView = getLayoutInflater().inflate(R.layout.adapter_carrusel_banner, null);
            TextView banTitle = custumView.findViewById(R.id.banTitle);
            banTitle.setText(tags[position]);
            return custumView;
        };
    }

    private AdapterListCategory.OnClickItem onClickItem(){
        return category -> {
            Intent intent = new Intent(this, ActivityStoreLayers.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Category.CATEGORY, category);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setDataBanner(BannerStore storeList){
        FrameLayout contentBanner = findViewById(R.id.contentBanner);
        contentBanner.setBackground(storeList.getBackground());

        ImageView imgBanner = findViewById(R.id.imgBanner);
        imgBanner.setImageResource(storeList.getImgBanner());

        TextView banTitle = findViewById(R.id.banTitle);
        banTitle.setText(storeList.getBanTitle());

        TextView banMenssage = findViewById(R.id.banMenssage);
        banMenssage.setText(storeList.getBanMenssage());
    }
}
