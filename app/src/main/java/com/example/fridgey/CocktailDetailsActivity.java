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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CocktailDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_details);
        ImageView thumbnail = findViewById(R.id.photo);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "https://thecocktaildb.com/api/json/v1/1/random.php";
        Cocktail random_cocktail = new Cocktail();
        JsonObjectRequest arrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray drinks = null;
                        try {
                            drinks = response.getJSONArray("drinks");
                            JSONObject drink = drinks.getJSONObject(0);
                            random_cocktail.setName(drink.getString("strDrink"));
                            random_cocktail.setImgurl(drink.getString("strDrinkThumb"));
                            List<String> ingredients = new ArrayList<String>();
                            description.setText("Ingredients: ");
                            for(int i=1;i<15;i++)
                            {
                                String ingredient = drink.getString("strIngredient"+i);
                                if(ingredient != "null")
                                {
                                    ingredients.add(ingredient);
                                    description.append("\n " + ingredient);
                                }

                            }
                            if(drink.getString("strInstructions") == "")
                            {
                                description.append("\n INSTRUCTIONS UNAVAILABLE.");
                            }
                            else
                            {
                                description.append("\n Instructions: \n"+drink.getString("strInstructions"));
                            }
                            name.setText(random_cocktail.getName());
                            Glide.with(getApplicationContext()).load(random_cocktail.imgurl).into(thumbnail);
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