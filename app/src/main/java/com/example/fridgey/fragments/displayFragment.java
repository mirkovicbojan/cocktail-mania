package com.example.fridgey.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.fridgey.APIControlls.Repository;
import com.example.fridgey.APIControlls.ImageLoader;
import com.example.fridgey.R;
import com.example.fridgey.models.Cocktail;


import org.w3c.dom.Text;

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
        LinearLayout layout = v.findViewById(R.id.display_view);
        if(option.equals("byName")){
            String url = "https://thecocktaildb.com/api/json/v1/1/search.php?s="+searchParam;
            repo.getData(getActivity().getApplicationContext(), url).observe(getViewLifecycleOwner(), cocktails -> drawList(cocktails, layout));

        }
        return v;
    }

    public void drawList(List<Cocktail> data, ViewGroup container){
        container.removeAllViews();
        for(Cocktail cocktail : data){
            Log.d("Cocktail", "drawList: "+ cocktail.name);
            LinearLayout list = new LinearLayout(getActivity().getApplicationContext());
            list.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            list.setOrientation(LinearLayout.VERTICAL);
            ImageView img = new ImageView(getContext());
            Glide.with(getContext()).load(cocktail.getImgurl()).into(img);
            container.addView(img);
        }
    }
}