package com.example.fridgey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.example.fridgey.APIControlls.ImageLoader;
import com.example.fridgey.APIControlls.MySingleton;
import com.example.fridgey.APIControlls.Repository;
import com.example.fridgey.models.Cocktail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        String drinkID = getIntent().getExtras().getString("drink");

        if(drinkID.equals("random")){

            String URL = "https://thecocktaildb.com/api/json/v1/1/random.php";
            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray drinks = response.getJSONArray("drinks");

                                JSONObject currentDrink = drinks.getJSONObject(0);
                                name.setText(currentDrink.getString("strDrink"));
                                Glide.with(getApplicationContext()).load(currentDrink.getString("strDrinkThumb")).into(thumbnail);
                                String ingredientsAndInstructions = "Ingredients: \n";
                                for(int i = 1; i<15; i++){
                                    if(!currentDrink.getString("strIngredient"+i).equals("null")){
                                        ingredientsAndInstructions += currentDrink.getString("strIngredient"+i);
                                        ingredientsAndInstructions += "\n";
                                    }
                                }
                                ingredientsAndInstructions += "Instructions: \n" + currentDrink.getString("strInstructions");
                                description.setText(ingredientsAndInstructions);

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
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(arrayRequest);
        }


    }
}