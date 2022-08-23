package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class SelectActivity extends AppCompatActivity {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //ImageView thumbnail = findViewById(R.id.cocktailThumb);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "https://thecocktaildb.com/api/json/v1/1/random.php";
        Cocktail koktel_dana = new Cocktail();
        queue.getCache().clear();
        JsonObjectRequest arrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray drinks = response.getJSONArray("drinks");
                            JSONObject drink = drinks.getJSONObject(0);
                            koktel_dana.setId(Integer.parseInt(drink.getString("idDrink")));
                            koktel_dana.setName(drink.getString("strDrink"));
                            koktel_dana.setImgurl(drink.getString("strDrinkThumb"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("api-key", "1");
                return headers;

            }
        };
        queue.add(arrayRequest);

    }
}