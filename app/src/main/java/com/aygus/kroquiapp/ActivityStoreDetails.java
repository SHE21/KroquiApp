package com.aygus.kroquiapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aygus.kroquiapp.Adapters.AdapterListLayerStore;
import com.aygus.kroquiapp.Modelos.LayerStore;
import com.aygus.kroquiapp.Service.OkHttpService;
import com.aygus.kroquiapp.Utils.ToastCostum;
import com.aygus.kroquiapp.Utils.UtilsAndroid;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayManager;
import java.util.ArrayList;
import java.util.List;

public class ActivityStoreDetails extends AppCompatActivity {
    private TextView titleMetadado, categoryMetadado, fileFormatMetadado, autorMetadado, prizeDetail, noConnect;
    private TextView termsUse, aboutGpay, support;
    private RatingBar ratingBar;
    private Button btnDownload;
    private ImageButton btnFullMap;
    private FrameLayout contTopMap2;

    private Activity getActivity(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        LayerStore layerStore = (LayerStore) getIntent().getSerializableExtra(LayerStore.LAYER);

        Toolbar toolbar =  findViewById(R.id.toolbarDetails);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnDownload = findViewById(R.id.btnDownload);
        String prize = "R$ " + String.valueOf(layerStore.getPrize());
        btnDownload.setText(prize);

        MapView mapPreview = findViewById(R.id.mapPreview);
        mapPreview.setTileSource(TileSourceFactory.MAPNIK);
        mapPreview.setBuiltInZoomControls(false);
        mapPreview.setMultiTouchControls(true);

        MapController mapController = new MapController(mapPreview);
        mapController.stopPanning();

        OverlayManager overlays = mapPreview.getOverlayManager();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL,false);

        RecyclerView recyclerViewRelac = findViewById(R.id.recyclerViewRelac);
        recyclerViewRelac.setLayoutManager(layoutManager);
        recyclerViewRelac.setHasFixedSize(true);
        recyclerViewRelac.setNestedScrollingEnabled(false);
        recyclerViewRelac.setItemAnimator(new DefaultItemAnimator());

        List<LayerStore> storeList = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            LayerStore layer = new LayerStore();
            layer.setTitle("Escolas Estaduais de Ensino - MA " + i);
            layer.setCategory("Category " + i);
            layer.setTypeGeom("point");
            layer.setPrize(230.0);
            storeList.add(layer);
        }

        int adapter = R.layout.adapter_item_product_layer2;
        AdapterListLayerStore adapterListLayerStore = new AdapterListLayerStore(storeList, onClickItemStore(), getBaseContext(), adapter);
        recyclerViewRelac.setAdapter(adapterListLayerStore);

        WebView webViewMetadado = findViewById(R.id.webview_metadado);
        ProgressBar progWebView = findViewById(R.id.progWebView);
        FrameLayout contentMetadado = findViewById(R.id.contentMetadado);
        Button btnOpenMetadado = findViewById(R.id.btnOpenMetadado);
        TextView noConnect = findViewById(R.id.noConnect);

        webViewMetadado.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView webView1, String url, Bitmap favicon) {
                progWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView1, String url) {
                progWebView.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView webView1, int error, String description, String failUrl) {
                webViewMetadado.setVisibility(View.GONE);
                progWebView.setVisibility(View.GONE);
            }
        });

        noConnect.setOnClickListener(v -> webViewMetadado.reload());

        btnOpenMetadado.setOnClickListener(v -> {

            if (UtilsAndroid.getStatusConnect(getBaseContext())) {
                getWebMetaDados(webViewMetadado, progWebView, contentMetadado, "http://www.aygus.com/apps/service/kroqui/metadado.html");

            }else{
                toastNoConnection();
                progWebView.setVisibility(View.GONE);
                noConnect.setVisibility(View.VISIBLE);
            }
                if (contentMetadado.getVisibility() == View.VISIBLE) {
                    contentMetadado.setVisibility(View.GONE);
                    btnOpenMetadado.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_keyboard_arrow_down_black_24dp, 0, 0, 0);

                } else {
                    contentMetadado.setVisibility(View.VISIBLE);
                    btnOpenMetadado.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_keyboard_arrow_up_black_24dp, 0, 0, 0);
                }

        });

        webViewMetadado.setOnLongClickListener(v -> true);

        FrameLayout contentMetadado2 = findViewById(R.id.contentMetadado2);
        ProgressBar progWebView2 = findViewById(R.id.progWebView2);
        WebView webview_fildtable = findViewById(R.id.webview_fildtable);
        TextView noConnect2 = findViewById(R.id.noConnect2);

        webview_fildtable.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView webView1, String url, Bitmap favicon) {
                progWebView2.setVisibility(View.VISIBLE);
                Log.d("WEB", url);
            }

            @Override
            public void onPageFinished(WebView webView1, String url) {
                Log.d("WEB", url);
                progWebView2.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView webView1, int error, String description, String failUrl) {
                Log.d("WEB", description);
                webview_fildtable.setVisibility(View.GONE);
                progWebView2.setVisibility(View.GONE);
                noConnect2.setVisibility(View.VISIBLE);
            }
        });

        noConnect2.setOnClickListener(v -> webview_fildtable.reload());

        Button btnOpenFildTable = findViewById(R.id.btnOpenFildTable);
        btnOpenFildTable.setOnClickListener(v -> {

            if (UtilsAndroid.getStatusConnect(getBaseContext())) {
                getWebMetaDados(webview_fildtable, progWebView2, contentMetadado2, "http://www.aygus.com/apps/service/kroqui/metadad.html");

            }else{
                toastNoConnection();
                progWebView2.setVisibility(View.GONE);
                noConnect2.setVisibility(View.VISIBLE);
            }

            if (contentMetadado2.getVisibility() == View.VISIBLE) {
                contentMetadado2.setVisibility(View.GONE);
                btnOpenFildTable.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_keyboard_arrow_down_black_24dp, 0, 0, 0);

            } else {
                contentMetadado2.setVisibility(View.VISIBLE);
                btnOpenFildTable.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_keyboard_arrow_up_black_24dp, 0, 0, 0);
            }
        });

        String url = "http://www.aygus.com/apps/service/kroqui/metadado.html";
        OkHttpService okHttpService = new OkHttpService(url, okHttpHelper(), OkHttpService.GET);
        okHttpService.execute();

        termsUse = findViewById(R.id.termsUse);
        aboutGpay = findViewById(R.id.aboutGpay);
        support = findViewById(R.id.support);

        termsUse.setOnClickListener(v -> {
            Toast.makeText(this, "termsUse", Toast.LENGTH_SHORT).show();
        });

        aboutGpay.setOnClickListener(v -> {
            Toast.makeText(this, "aboutGpay", Toast.LENGTH_SHORT).show();
        });

        support.setOnClickListener(v -> {
            Toast.makeText(this, "support", Toast.LENGTH_SHORT).show();
        });

        contTopMap2 = findViewById(R.id.contTopMap2);
        btnFullMap = findViewById(R.id.btnFullMap);
        btnFullMap.setOnClickListener(v -> {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            params.height = LinearLayout.LayoutParams.MATCH_PARENT;
            contTopMap2.setLayoutParams(params);
            contTopMap2.requestLayout();
        });
    }

    private OkHttpService.OkHttpHelper okHttpHelper(){
        return new OkHttpService.OkHttpHelper() {
            @Override
            public void onPreExecuteHelper() {

            }

            @Override
            public void onPosExeculte(OkHttpService.ResponseResultOkHttp result) {
                if (result!=null){
                    if (result.code==200){
                        Log.d("OkHttp - > Result", " " + result.result);
                        Log.d("OkHttp - > Message", " " + result.mensage);

                    }else{
                        Log.d("OkHttp - > Result", " " + result.result);
                        Log.d("OkHttp - > Message", " " + result.mensage);
                        Log.d("OkHttp - > Code", " " + result.code);
                    }

                }else{
                    //Log.d("OkHttp -> Result", " is " + result.result);
                }
            }
        };
    }

    private AdapterListLayerStore.OnClickItemStore onClickItemStore(){
        return layerStore -> {
            Intent intent = new Intent(getBaseContext(), ActivityStoreDetails.class);
            intent.putExtra(LayerStore.LAYER, layerStore);
            startActivity(intent);

        };
    }

    private void getWebMetaDados(WebView webView, ProgressBar progressBar, FrameLayout contentMetadado, String url){
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView webView1, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView1, String url) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView webView1, int error, String description, String failUrl) {
                webView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void toastNoConnection(){
        String noConnect = getResources().getString(R.string.noConnect);
        ToastCostum.toast(noConnect, getActivity(), ToastCostum.ERROR, Toast.LENGTH_LONG).show();
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
}
