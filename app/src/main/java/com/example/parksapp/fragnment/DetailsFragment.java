package com.example.parksapp.fragnment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parksapp.R;
import com.example.parksapp.adapter.ViewPagerAdapter;
import com.example.parksapp.databinding.FragmentDetailsBinding;
import com.example.parksapp.model.EntranceFees;
import com.example.parksapp.model.Root;
import com.example.parksapp.repository.ParkRepository;
import com.example.parksapp.view.ActivitiesViewModel;
import com.example.parksapp.view.FeesViewModel;
import com.example.parksapp.view.ParkViewModel;
import com.example.parksapp.view.ParksViewModel;
import com.example.parksapp.view.TopicsViewModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment {
    private static final String TAG = "Testing";
    FragmentDetailsBinding binding;
    private ParkViewModel viewModelPark;
    private String id = null;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance() {
        DetailsFragment fragment = new DetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        if (getArguments() != null) {
            id = getArguments().getString("id", "Default");
            getParkDetails(id);
        } else {
            Log.d(TAG, "onCreate: " + "Empty");
        }

        return binding.getRoot();
    }

    private void getParkDetails(String id) {
        ActivitiesViewModel activitiesViewModel = new ViewModelProvider(this).get(ActivitiesViewModel.class);
        List<String> activityList = new ArrayList<>();
        TopicsViewModel topicsViewModel = new ViewModelProvider(this).get(TopicsViewModel.class);
        List<String> topicList = new ArrayList<>();
        List<EntranceFees> feesList = new ArrayList<>();
        FeesViewModel feesViewModel = new ViewModelProvider(this).get(FeesViewModel.class);
        viewModelPark = ViewModelProviders.of(this).get(ParkViewModel.class);
        viewModelPark.getPark(id).observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                binding.setPark(root.getData().get(0));

                ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(), root.getData().get(0).getImages());
                binding.imageViewPager.setAdapter(adapter);
                binding.detailsProgressbar.setVisibility(View.INVISIBLE);

                for (int i = 0; i < root.getData().get(0).getActivities().size(); i++) {
                    activityList.add(root.getData().get(0).getActivities().get(i).getName());
                }
              //  Log.d(TAG, "onChangedd: " + activityList.size());
                binding.setActivityList(activityList);
                binding.setActivitiesViewModel(activitiesViewModel);

                for (int i = 0; i < root.getData().get(0).getTopics().size(); i++) {
                    topicList.add(root.getData().get(0).getTopics().get(i).getName());
                }
              //  Log.d(TAG, "onChangedd: " + activityList.size());
                binding.setTopicList(topicList);
                binding.setTopicsViewModel(topicsViewModel);

                for (int i = 0; i < root.getData().get(0).getEntranceFees().size(); i++) {
                    feesList.add(root.getData().get(0).getEntranceFees().get(i));
                }
                //  Log.d(TAG, "onChangedd: " + activityList.size());
                binding.setFeesList(feesList);
                binding.setFeesViewModel(feesViewModel);
            }
        });
    }

}



