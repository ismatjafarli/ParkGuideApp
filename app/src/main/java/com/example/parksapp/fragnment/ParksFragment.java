package com.example.parksapp.fragnment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parksapp.R;
import com.example.parksapp.adapter.OnParkClickListener;
import com.example.parksapp.adapter.ParksAdapter;
import com.example.parksapp.databinding.FragmentParksBinding;
import com.example.parksapp.model.Data;
import com.example.parksapp.model.Root;
import com.example.parksapp.view.ParkViewModel;
import com.example.parksapp.view.ParksViewModel;

public class ParksFragment extends Fragment implements OnParkClickListener {
    private static final String TAG = "Testing";
    FragmentParksBinding binding;
    private ParksViewModel viewModel;
    private ParkViewModel parkViewModel;

    public ParksFragment() {
        // Required empty public constructor
    }

    public static ParksFragment newInstance() {
        ParksFragment fragment = new ParksFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parks, container, false);
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()) {
                    getFragmentManager().popBackStackImmediate();
                } else {
                    Bundle args = new Bundle();
                    args.putString("stateCode", s.toString().toString().trim());
                    SearchFragment searchFragment = new SearchFragment();
                    searchFragment.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_park_without_search, searchFragment)
                            .commit();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getData();
        return binding.getRoot();
    }


    private void getData() {
        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setHasFixedSize(true);

        viewModel = ViewModelProviders.of(this).get(ParksViewModel.class);
        viewModel.getParks().observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                ParksAdapter adapter = new ParksAdapter(getContext(), root.getData(), ParksFragment.this);
                binding.recyclerView.setAdapter(adapter);
                binding.progressBarParks.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onParkClicked(Data data) {
     //   Log.d(TAG, "onParkClicked: "+ data.getId());
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