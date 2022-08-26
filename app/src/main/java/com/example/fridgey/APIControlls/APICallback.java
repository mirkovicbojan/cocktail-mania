package com.example.fridgey.APIControlls;

import com.example.fridgey.Cocktail;
import com.example.fridgey.CocktailSearchDTO;

import org.json.JSONObject;

import java.util.List;

public interface APICallback {
    void onSuccess(JSONObject result);
    void onError(String result);
}
