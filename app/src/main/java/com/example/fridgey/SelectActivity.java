package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;




public class SelectActivity extends AppCompatActivity {



    public void proceedToOption(String option)
    {
        if(option.equals("random"))
        {
            Intent intent = new Intent(getApplicationContext(), CocktailDetailsActivity.class);
            intent.putExtra("drink", option);
            startActivity(intent);
        }
        if(option.equals("byName") || option.equals("byIngredient"))
        {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.putExtra("option", option);
            startActivity(intent);
        }
        if(option.equals("favorites")){
            Intent intent = new Intent(getApplicationContext(), MyFavoritesActivity.class);
            intent.putExtra("option", option);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        CardView randomSearch = findViewById(R.id.randomSearch);
        randomSearch.setOnClickListener(view -> proceedToOption("random"));

        CardView searchByName = findViewById(R.id.searchByName);
        searchByName.setOnClickListener(view -> proceedToOption("byName"));

        CardView MyFavorites = findViewById(R.id.myCollection);
        MyFavorites.setOnClickListener(view -> proceedToOption("favorites"));
    }
}