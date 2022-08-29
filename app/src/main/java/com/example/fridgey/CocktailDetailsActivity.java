package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.fridgey.APIControlls.Repository;
import com.example.fridgey.models.Cocktail;

import org.json.JSONObject;

public class CocktailDetailsActivity extends AppCompatActivity {

    private RequestQueue mQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_details);
        ImageView thumbnail = findViewById(R.id.photo);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        Cocktail displayCocktail = new Cocktail();




    }
}