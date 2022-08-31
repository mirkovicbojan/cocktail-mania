package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fridgey.databaselayer.DBRepository;
import com.example.fridgey.databaselayer.DatabaseHelper;
import com.example.fridgey.models.Cocktail;

import org.w3c.dom.Text;

import java.util.List;

public class MyFavoritesActivity extends AppCompatActivity {

    private DBRepository dbRepo;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.DESC };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorites);

        LinearLayout l = findViewById(R.id.listLayout);

        dbRepo = new DBRepository(this);
        dbRepo.open();
        List<Cocktail> favorites = dbRepo.fetch();



        for(Cocktail cocktail : favorites){
            TextView name = new TextView(this);
            name.setText(cocktail.getName());
            l.addView(name);
        }


    }
}