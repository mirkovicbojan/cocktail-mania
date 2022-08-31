package com.example.fridgey.APIControlls;



import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fridgey.models.Cocktail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository extends ViewModel{

    private MutableLiveData<List<Cocktail>> data;

    public LiveData<List<Cocktail>> getData(Context context, String URL){
        if(data == null){
            data = new MutableLiveData<>();
            fetchData(null, context, URL);
        }
        return data;
    }

    public void fetchData(List<Cocktail> newData, Context context, String URL){
        List<Cocktail> list = new ArrayList<Cocktail>();
        //RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        if(newData == null) {

            JsonObjectRequest arrayRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray drinks = response.getJSONArray("drinks");
                                for(int i = 0; i<drinks.length(); i++){
                                    JSONObject drink = drinks.getJSONObject(i);
                                    Log.d("Entered request", "onResponse: " + drink);
                                    Cocktail c = new Cocktail();
                                    c.setId(drink.getString("idDrink"));
                                    c.setName(drink.getString("strDrink"));
                                    c.setImgurl(drink.getString("strDrinkThumb"));
                                    c.setInstructions(drink.getString("strInstructions"));


                                    list.add(c);

                                }
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
            MySingleton.getInstance(context).addToRequestQueue(arrayRequest);
            //queue.add(arrayRequest);
        }
        this.data.setValue(list);
        }

    }

