package com.example.covid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffecttedCounties extends AppCompatActivity {
    EditText editText;
    ListView listView;
    SimpleArcLoader simpleArcLoader;



    public static List<countrymodel> countrymodelList=new ArrayList<>();
    countrymodel countryModel;
    MyCustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affectted_counties);
        editText=findViewById(R.id.edit_search);
        listView=findViewById(R.id.listView);
        simpleArcLoader=findViewById(R.id.loader);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fetechData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailActivity.class).putExtra("position",position));
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    private void fetechData() {
        String url="https://corona.lmao.ninja/v2/countries/";

        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                             for(int i=0;i<jsonArray.length();i++){
                                 JSONObject jsonObject=jsonArray.getJSONObject(i);

                                 String countryName=jsonObject.getString("country");
                                 String cases=jsonObject.getString("cases");
                                 String todayCases=jsonObject.getString("todayCases");
                                 String deaths=jsonObject.getString("deaths");
                                 String todayDeaths=jsonObject.getString("todayDeaths");
                                 String recovered=jsonObject.getString("recovered");
                                 String todayRecovered=jsonObject.getString("todayRecovered");
                                 String active=jsonObject.getString("active");
                                 String critical=jsonObject.getString("critical");
                                 String casesPerOneMillion=jsonObject.getString("casesPerOneMillion");
                                 String deathsPerOneMillion=jsonObject.getString("deathsPerOneMillion");
                                 String  tests=jsonObject.getString("tests");
                                 String  testsPerOneMillion=jsonObject.getString("testsPerOneMillion");
                                 String  population=jsonObject.getString("population");

                                 JSONObject Object=jsonObject.getJSONObject("countryInfo");
                                 String flagUrl=Object.getString("flag");
                                 countryModel =new countrymodel(countryName,flagUrl,cases,todayCases,deaths,todayDeaths,recovered,todayRecovered,active,critical,casesPerOneMillion,deathsPerOneMillion,tests,testsPerOneMillion,population);
                                 countrymodelList.add(countryModel);
                             }
                             myCustomAdapter=new MyCustomAdapter(AffecttedCounties.this,countrymodelList);
                             listView.setAdapter(myCustomAdapter);
                             simpleArcLoader.stop();
                             simpleArcLoader.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AffecttedCounties.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}