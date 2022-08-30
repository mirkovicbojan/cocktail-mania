package com.example.fridgey.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.fridgey.APIControlls.Repository;
import com.example.fridgey.R;
import com.example.fridgey.models.Cocktail;


import java.util.List;


public class displayFragment extends Fragment {

    String searchParam;
    String option;
    Repository repo;

    public displayFragment() {
        // Required empty public constructor
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public static displayFragment newInstance(String param, String option) {
        displayFragment fragment = new displayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setSearchParam(param);
        fragment.setOption(option);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repo = new ViewModelProvider(requireActivity(), new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                try{
                    return modelClass.newInstance();
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }catch( java.lang.InstantiationException e){
                    e.printStackTrace();
                }
                return null;
            }

        }).get(Repository.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display, container, false);
        if(option.equals("byName")){
            String url = "https://thecocktaildb.com/api/json/v1/1/search.php?s="+searchParam;
            repo.getData(getActivity().getApplicationContext(), url).observe(getViewLifecycleOwner(), cocktails -> drawList(cocktails, (ViewGroup) v));

        }
        return v;
    }

    public void drawList(List<Cocktail> data, ViewGroup container){
        container.removeAllViews();
        /*LinearLayout list = new LinearLayout(getActivity().getApplicationContext());
        list.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        list.setOrientation(LinearLayout.VERTICAL);*/
        for(Cocktail cocktail : data){
            Log.d("Cocktail", "drawList: "+ cocktail.name);

            /*ImageView img = new ImageView(getContext());

            Glide.with(getContext()).load(cocktail.getImgurl()).into(img);*/
            TextView text = new TextView(getActivity().getApplicationContext());
            text.setText(cocktail.getName());
            container.addView(text);

        }
        //container.addView(list);
    }
}