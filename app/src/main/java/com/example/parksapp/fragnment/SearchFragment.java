package com.example.parksapp.fragnment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.example.parksapp.R;
import com.example.parksapp.adapter.OnParkClickListener;
import com.example.parksapp.adapter.SearchAdapter;
import com.example.parksapp.databinding.FragmentSearchBinding;
import com.example.parksapp.model.Data;
import com.example.parksapp.model.Root;
import com.example.parksapp.view.SearchViewModel;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class SearchFragment extends Fragment implements OnParkClickListener {
    private static final String TAG = "Testing";
    FragmentSearchBinding binding;
    private SearchViewModel searchViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hideSoftKeyboard(getActivity());


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);


        if(!getArguments().isEmpty()) {
            String stateCode = getArguments().getString("stateCode");
            getSearchedParks(stateCode);
        } else {
            Log.d(TAG, "onCreateView: " + "Empty");
        }


        return binding.getRoot();
    }

    private void getSearchedParks(String stateCode) {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.searchRecyclerView.setLayoutManager(linearLayoutManager);
        binding.searchRecyclerView.setHasFixedSize(true);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.getSearch(stateCode).observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                SearchAdapter searchAdapter = new SearchAdapter(getContext(), root.getData(), SearchFragment.this);
                binding.searchRecyclerView.setAdapter(searchAdapter);
                binding.searchProgressbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onParkClicked(Data data) {
        Bundle args = new Bundle();
        args.putString("id", data.getId().toString());
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_park, fragment)
                .addToBackStack(null)
                .commit();
    }


}