package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private void displayItems(String input)
    {
        String option = getIntent().getStringExtra("picked_option");
        if(option.equals("byName"))
        {

        }
        else if(option.equals("byIngredient"))
        {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ScrollView display = findViewById(R.id.display_view);
        Button searchButton = findViewById(R.id.search_btn);
        TextView searchInput = findViewById(R.id.search_input);

        searchButton.setOnClickListener(view -> displayItems(searchInput.getText().toString()));
    }
}