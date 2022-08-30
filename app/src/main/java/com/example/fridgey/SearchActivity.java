package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.example.fridgey.fragments.displayFragment;
import com.example.fridgey.models.Cocktail;

import java.util.List;


public class SearchActivity extends AppCompatActivity {

    private RequestQueue mQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ScrollView display = findViewById(R.id.display_view);
        Button searchButton = findViewById(R.id.search_btn);
        TextView searchInput = findViewById(R.id.search_input);
        Bundle b = getIntent().getExtras();
        String option = b.getString("option");
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display.removeAllViews();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                displayFragment fr = displayFragment.newInstance(searchInput.getText().toString(), option);
                ft.add(R.id.list_layout, fr, "Added");
                ft.commit();
                Log.d("Children", "onClick: "+display.getChildCount());
            }
        });

    }
}