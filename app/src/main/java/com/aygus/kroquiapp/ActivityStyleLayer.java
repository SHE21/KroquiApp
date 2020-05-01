package com.aygus.kroquiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.aygus.kroquiapp.Modelos.AStyle;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.Style.PreviewDrawable;
import com.google.gson.Gson;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ActivityStyleLayer extends AppCompatActivity {
    private Layer layer;
    private AStyle style, restStyle;
    private EditText fildRadExtern, fildRadIntern, fildAlphaExtern, fildAlphaIntern;
    private Button btnColorExtern, btnColorIntern, btnRestStyle, btnSaveStyle;
    private SeekBar seekRadExternAlpha, seekRadInternAlpha, seekRadIntern, seekRadExtern;
    private PreviewDrawable pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_layer);
        if (getIntent()!=null) {

            layer = (Layer) getIntent().getSerializableExtra("layer");
            style = layer.getaStyle();
            style.setAlphaIntern(255);
            style.setAlpha(255);

            restStyle = new AStyle();
            restStyle = style;

            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Estilo da Camada");
            toolbar.setSubtitle(layer.getNameLayer());
            setSupportActionBar(toolbar);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            pd = findViewById(R.id.previewDraw);
            pd.setaStyle(style);

            btnColorExtern = findViewById(R.id.btnColorExtern);
            btnColorExtern.setBackgroundColor(style.colorRadExtern);
            btnColorExtern.setOnClickListener(palleteColorExtern());

            btnColorIntern = findViewById(R.id.btnColorIntern);
            btnColorIntern.setBackgroundColor(style.colorRadIntern);
            btnColorIntern.setOnClickListener(palleteColorIntern());

            fildRadExtern = findViewById(R.id.fildRadExtern);
            fildRadExtern.setText(String.valueOf(style.radExtern));
            fildRadIntern = findViewById(R.id.fildRadIntern);
            fildRadIntern.setText(String.valueOf(style.radInter));

            fildAlphaExtern = findViewById(R.id.fildAlphaExtern);
            fildAlphaExtern.setText(String.valueOf(style.alpha));
            fildAlphaIntern = findViewById(R.id.fildAlphaIntern);
            fildAlphaIntern.setText(String.valueOf(style.alphaIntern));

            seekRadExternAlpha = findViewById(R.id.seekRadExternAlpha);
            seekRadExternAlpha.setMax(255);
            seekRadExternAlpha.setProgress(255);
            seekRadExternAlpha.setOnSeekBarChangeListener(seekOnChangeAlphaExtern());

            seekRadInternAlpha = findViewById(R.id.seekRadInternAlpha);
            seekRadInternAlpha.setMax(255);
            seekRadInternAlpha.setProgress(255);
            seekRadInternAlpha.setOnSeekBarChangeListener(seekOnChangeAlphaIntern());

            seekRadIntern = findViewById(R.id.seekRadIntern);
            seekRadIntern.setProgress(style.radInter);
            seekRadIntern.setMax(style.radExtern-5);
            seekRadIntern.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    style.radInter = progress;
                    fildRadIntern.setText(String.valueOf(style.radInter));
                    pd.setaStyle(style);
                    pd.invalidate();
                    Log.d("SEEKBAR", "" + style.radInter);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            });

            seekRadExtern = findViewById(R.id.seekRadExtern);
            seekRadExtern.setMax(50);
            seekRadExtern.setProgress(style.radExtern);
            seekRadExtern.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    style.radExtern = progress;
                    fildRadExtern.setText(String.valueOf(style.radExtern));
                    pd.setaStyle(style);
                    pd.invalidate();
                    Log.d("SEEKBAR", "" + style.radInter);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            });

            btnRestStyle = findViewById(R.id.btnRestStyle);
            btnRestStyle.setOnClickListener(onClickBtnResert());

            btnSaveStyle = findViewById(R.id.btnSaveStyle);
            btnSaveStyle.setOnClickListener(onClickBtnSave());
        }
    }

    private View.OnClickListener palleteColorExtern(){
        return v -> {

            AmbilWarnaDialog colorDialog =  new AmbilWarnaDialog(this, style.colorRadExtern, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) { }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    style.colorRadExtern = color;
                    btnColorExtern.setBackgroundColor(color);
                    pd.invalidate();

                }
            });
            colorDialog.show();
        };
    }

    private View.OnClickListener palleteColorIntern(){
        return v -> {

            AmbilWarnaDialog colorDialog =  new AmbilWarnaDialog(this, style.colorRadIntern, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) { }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    style.colorRadIntern = color;
                    btnColorIntern.setBackgroundColor(color);
                    pd.invalidate();

                }
            });
            colorDialog.show();
        };
    }

    private SeekBar.OnSeekBarChangeListener seekOnChangeAlphaExtern(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                style.setAlpha(progress);
                fildAlphaExtern.setText(String.valueOf(progress));
                pd.invalidate();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };
    }

    private SeekBar.OnSeekBarChangeListener seekOnChangeAlphaIntern(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                style.setAlphaIntern(progress);
                fildAlphaIntern.setText(String.valueOf(progress));
                pd.invalidate();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };
    }

    private View.OnClickListener onClickBtnResert(){
        return v -> {
            style = restStyle;
            pd.setaStyle(restStyle);
            pd.invalidate();

            seekRadExtern.setProgress(restStyle.radExtern);
            fildRadExtern.setText(String.valueOf(restStyle.radExtern));
            seekRadExternAlpha.setProgress(restStyle.alpha);
            fildAlphaExtern.setText(String.valueOf(restStyle.alpha));

            seekRadIntern.setProgress(restStyle.radInter);
            fildRadIntern.setText(String.valueOf(restStyle.radInter));
            seekRadInternAlpha.setProgress(restStyle.alphaIntern);
            fildAlphaIntern.setText(String.valueOf(restStyle.alphaIntern));
        };
    }

    private View.OnClickListener onClickBtnSave(){
        return v -> Toast.makeText(ActivityStyleLayer.this, "" + new Gson().toJson(style), Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

}
