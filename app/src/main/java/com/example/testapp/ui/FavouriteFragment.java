package com.example.testapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.adapter.FavouriteAdapter;
import com.example.testapp.data.local.FavouriteService;
import com.example.testapp.model.Favourite;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {


    private RelativeLayout emptyLayout;

    private RecyclerView favouritesRecyclerView;
    private FavouriteAdapter adapter;

    static FavouriteFragment newInstance() {

        return new FavouriteFragment();

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);

        emptyLayout = root.findViewById(R.id.layout_empty);

        favouritesRecyclerView = root.findViewById(R.id.recyclerview_favourites);
        adapter = new FavouriteAdapter(requireContext(), new ArrayList<Favourite>());

        favouritesRecyclerView.setAdapter(adapter);

        getData();

        return root;
    }

    //getData from local database(sqlite), it observes the livedata for changes
    private void getData(){
        new FavouriteService(requireContext()).getAllFavourites().observe(getViewLifecycleOwner(), new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                //if the number of favourites movies is > 0, show list
                if(favourites.size() > 0){
                    adapter.setFavourites(favourites);
                    showData();
                }else{ //else show layout indication empty
                    showEmpty();
                }
            }
        });
    }

    private void showData(){
        favouritesRecyclerView.setVisibility(View.VISIBLE);
        emptyLayout.setVisibility(View.GONE);
    }

    private void showEmpty(){
        favouritesRecyclerView.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.VISIBLE);
    }

}
