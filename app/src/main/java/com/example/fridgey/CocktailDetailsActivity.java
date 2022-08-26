package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.fridgey.APIControlls.APICallback;
import com.example.fridgey.APIControlls.APIController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        mQueue = Volley.newRequestQueue(this);

        mQueue.add(APIController.getRandomCocktail(new APICallback() {
            @Override
            public void onSuccess(JSONObject result)
            {

            }

            @Override
            public void onError(String result) {
                System.out.println(result);
            }
        }));

    }
}