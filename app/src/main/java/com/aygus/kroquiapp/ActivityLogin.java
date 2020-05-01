package com.aygus.kroquiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        EditText nameUser = findViewById(R.id.nameUser);
        EditText password = findViewById(R.id.password);

        Button btnLogar = findViewById(R.id.btnLogar);
        btnLogar.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ActivityProject.class);
            startActivity(intent);
        });

        Button btnNewAccount = findViewById(R.id.btnNewAccount);
        btnNewAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), ActivityNewAccount.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_login, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_active_id:

                break;

            case R.id.action_help:

                break;
        }

        return true;
    }

}
