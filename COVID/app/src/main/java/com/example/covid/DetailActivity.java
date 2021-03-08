package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView country,cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, active, critical, casesPerOneMillion, deathsPerOneMillion, tests, testsPerOneMillion, population;

    private int positionCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Details of "+AffecttedCounties.countrymodelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        country=findViewById(R.id.country);
        cases=findViewById(R.id.Cases);
        todayCases=findViewById(R.id.today_Cases);
        recovered=findViewById(R.id.recovered);
        todayRecovered=findViewById(R.id.todayrecovered);
        active=findViewById(R.id.active);
        critical=findViewById(R.id.critical);
        deaths=findViewById(R.id.deaths);
        todayDeaths=findViewById(R.id.todayDeaths);
        casesPerOneMillion=findViewById(R.id.cpomilion);
        deathsPerOneMillion=findViewById(R.id.death_per_milion);
        tests=findViewById(R.id.tests);
        testsPerOneMillion=findViewById(R.id.test_per_one_milion);
        population=findViewById(R.id.population);

        country.setText(AffecttedCounties.countrymodelList.get(positionCountry).getCountry());
        todayDeaths.setText(AffecttedCounties.countrymodelList.get(positionCountry).getTodayDeaths());
        deaths.setText(AffecttedCounties.countrymodelList.get(positionCountry).getDeaths());
        cases.setText(AffecttedCounties.countrymodelList.get(positionCountry).getCases());
        todayCases.setText(AffecttedCounties.countrymodelList.get(positionCountry).getTodayCases());
        recovered.setText(AffecttedCounties.countrymodelList.get(positionCountry).getRecovered());
        todayRecovered.setText(AffecttedCounties.countrymodelList.get(positionCountry).getTodayRecovered());
        active.setText(AffecttedCounties.countrymodelList.get(positionCountry).getActive());
        critical.setText(AffecttedCounties.countrymodelList.get(positionCountry).getCritical());
        casesPerOneMillion.setText(AffecttedCounties.countrymodelList.get(positionCountry).getCasesPerOneMillion());
        deathsPerOneMillion.setText(AffecttedCounties.countrymodelList.get(positionCountry).getDeathsPerOneMillion());
        tests.setText(AffecttedCounties.countrymodelList.get(positionCountry).getTests());
        testsPerOneMillion.setText(AffecttedCounties.countrymodelList.get(positionCountry).getTestsPerOneMillion());
        population.setText(AffecttedCounties.countrymodelList.get(positionCountry).getPopulation());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}