package id.sam.submission2bfaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sam.submission2bfaa.adapter.UserAdapter;
import id.sam.submission2bfaa.model.search.Item;
import id.sam.submission2bfaa.model.search.SearchModel;
import id.sam.submission2bfaa.service.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Item> data = new ArrayList<>();
    RecyclerView rvSearchUser;
    ProgressBar progressBar;
    SearchView svUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Search User");
        rvSearchUser = findViewById(R.id.rvSearchUser);
        rvSearchUser.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progressBar);
        svUser = findViewById(R.id.svUser);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null){
            svUser.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            svUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    progressBar.setVisibility(View.VISIBLE);
                    getSearchUser(s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }
    }

    private void getSearchUser(final String usernames) {
        Call<SearchModel> request = ApiClient.getApiService().getSearchUser(usernames);
        request.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    data = response.body().getItems();
                    rvSearchUser.setAdapter(new UserAdapter(MainActivity.this, data));
                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "Request Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}