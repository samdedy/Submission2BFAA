package id.sam.submission2bfaa.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import id.sam.submission2bfaa.DetailActivity;
import id.sam.submission2bfaa.R;
import id.sam.submission2bfaa.adapter.FollowersAdapter;
import id.sam.submission2bfaa.adapter.UserAdapter;
import id.sam.submission2bfaa.model.FollowersModel;
import id.sam.submission2bfaa.model.search.Item;
import id.sam.submission2bfaa.model.search.SearchModel;
import id.sam.submission2bfaa.service.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersFragment extends Fragment {

    RecyclerView rvFollowers;
    Item dataSearch;

    public FollowersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Mengambil data dari search user
        DetailActivity detailActivity = (DetailActivity) getActivity();
        Bundle bundle = detailActivity.getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataSearch = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_SEARCH));

        rvFollowers = view.findViewById(R.id.rvFollowers);
        rvFollowers.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowersModel>> request = ApiClient.getApiService().getFollowerUser(dataSearch.getLogin());
        request.enqueue(new Callback<List<FollowersModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<FollowersModel>> call, @NotNull Response<List<FollowersModel>> response) {
                List<FollowersModel> listFollower = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listFollower.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse: " +listFollower.size());
                        rvFollowers.setAdapter(new FollowersAdapter(getContext(), listFollower));
                    }
                } else {
                    Toast.makeText(getContext(), "Request Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowersModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}