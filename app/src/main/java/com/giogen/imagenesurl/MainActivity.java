package com.giogen.imagenesurl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BNH";
    public static ArrayList<Heroe> heroesList;
    RequestQueue requestQueue;
    private RecyclerView heroRecyclerView;
    private AdaptadorHereos adapter;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtenerHeroes();
    }

    public void obtenerHeroes(){
        requestQueue = Volley.newRequestQueue(this);
        String urlHeroes = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        JsonRequest jsonRequest = new JsonObjectRequest(
                urlHeroes,
                null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("heroes");
                        Type listType = new TypeToken<ArrayList<Heroe>>(){}.getType();
                        Gson gson = new Gson();
                        heroesList = gson.fromJson(jsonArray.toString(), listType);

                        for(Heroe hero: heroesList){
                            Log.d(TAG, "heroesRequests: nombre: " + hero.getName() + ", Imagen: " + hero.getImageurl());
                        }

                        heroRecyclerView = findViewById(R.id.rbHeroes);
                        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        adapter = new AdaptadorHereos(getApplicationContext(), MainActivity.heroesList);
                        heroRecyclerView.setLayoutManager(layoutManager);
                        heroRecyclerView.setAdapter(adapter);

                        HeroesFragment selectorFragment = new HeroesFragment();
                        /*
                        if (this.findViewById(R.id.constrain_layout) != null && this.getSupportFragmentManager().findFragmentById(R.id.constrain_layout) == null){
                            this.getSupportFragmentManager().beginTransaction().add(R.id.constrain_layout, selectorFragment).commit();
                        }

                         */

                        Log.d(TAG, "heroesRequests: Héroes cargados");
                    } catch (JSONException e) {
                        Log.d(TAG, "heroesRequests: " + e.getMessage());
                    }
                },
                error -> {
                    Log.d(TAG, "heroesRequests: Error: " + error);
                }
        );



        requestQueue.add(jsonRequest);
        Log.d(TAG, "heroesRequests: Pasó request");





    }
}