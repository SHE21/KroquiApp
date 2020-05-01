package com.aygus.kroquiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class ActivityNewAccount extends AppCompatActivity {
    private AutoCompleteTextView countries;
    private Spinner spinnerProfi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.registrese);
        }

        countries = findViewById(R.id.countries);
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.arrayCountries, android.R.layout.simple_dropdown_item_1line);
        countries.setAdapter(adapterCountries);

        spinnerProfi = findViewById(R.id.spinnerProfi);
        ArrayAdapter<CharSequence> adapterProfUser = ArrayAdapter.createFromResource(this, R.array.arrayProfission, android.R.layout.simple_spinner_item);
        adapterProfUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfi.setAdapter(adapterProfUser);
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
