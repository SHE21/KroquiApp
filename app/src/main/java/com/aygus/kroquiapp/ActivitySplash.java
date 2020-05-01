package com.aygus.kroquiapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.aygus.kroquiapp.Utils.PreferenceAplication;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        new Handler().postDelayed(() -> {
            PreferenceAplication preferencesDarwin = new PreferenceAplication(getBaseContext());
            if (preferencesDarwin.getStatusLogUser()){
                if (preferencesDarwin.getLicenseStatus()) {
                    Intent intent = new Intent(getBaseContext(), ActivityProject.class);
                    startActivity(intent);

                }else{
                    //Intent intent = new Intent(getBaseContext(), ActivityLicense.class);
                    //startActivity(intent);
                }
            }else{
                Intent intent = new Intent(getBaseContext(), ActivityLogin.class);
                startActivity(intent);
            }

            finish();
        }, 1000);
    }
}
