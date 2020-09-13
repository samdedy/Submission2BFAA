package id.sam.submission2bfaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import de.hdodenhof.circleimageview.CircleImageView;
import id.sam.submission2bfaa.adapter.PageAdapter;
import id.sam.submission2bfaa.adapter.UserAdapter;
import id.sam.submission2bfaa.model.DetailModel;
import id.sam.submission2bfaa.model.search.Item;
import id.sam.submission2bfaa.service.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    DetailModel dataDetail;
    Item dataSearch;
    TextView txtName,txtUsername,txtBio,txtCompany,txtLocation,txtUrl,txtRepo,txtGist,txtFollowing,txtFollower;
    CircleImageView imgAvatar;
    ImageView imgCompany, imgLocation;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Detail User");
        txtName = findViewById(R.id.txtName);
        txtUsername = findViewById(R.id.txtUsername);
        txtBio = findViewById(R.id.txtBio);
        txtCompany = findViewById(R.id.txtCompany);
        txtLocation = findViewById(R.id.txtLocation);
        txtUrl = findViewById(R.id.txtUrl);
        txtRepo = findViewById(R.id.txtRepo);
        txtGist = findViewById(R.id.txtGist);
        txtFollowing = findViewById(R.id.txtFollowing);
        txtFollower = findViewById(R.id.txtFollower);
        imgAvatar = findViewById(R.id.imgAvatar);
        imgCompany = findViewById(R.id.imgCompany);
        imgLocation = findViewById(R.id.imgLocation);

        Bundle bundle = getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataSearch = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_SEARCH));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap tunggu ...");
        progressDialog.show();

        Picasso.get().load(dataSearch.getAvatarUrl()).into(imgAvatar);
        txtUsername.setText(dataSearch.getLogin());
        txtUrl.setText(dataSearch.getHtmlUrl());

        getDetailUser();

        //TabLayout dan ViewPager
        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);
    }

    public void getDetailUser(){
        Call<DetailModel> request = ApiClient.getApiService().getDetailUser(dataSearch.getLogin());
        request.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, @NotNull Response<DetailModel> response) {
                if (response.isSuccessful()) {
                    dataDetail = response.body();
                    if (dataDetail.getName() != null){
                        txtName.setText(dataDetail.getName());
                    } else {
                        txtName.setVisibility(View.GONE);
                    }
                    if (dataDetail.getBio() != null){
                        txtBio.setText(dataDetail.getBio());
                    } else {
                        txtBio.setVisibility(View.GONE);
                    }
                    if (dataDetail.getCompany() != null){
                        txtCompany.setText(dataDetail.getCompany());
                    } else {
                        txtCompany.setVisibility(View.GONE);
                        imgCompany.setVisibility(View.GONE);
                    }
                    if (dataDetail.getLocation() != null){
                        txtLocation.setText(dataDetail.getLocation());
                    } else {
                        txtLocation.setVisibility(View.GONE);
                        imgLocation.setVisibility(View.GONE);
                    }
                    txtRepo.setText(String.valueOf(dataDetail.getPublicRepos()));
                    txtGist.setText(String.valueOf(dataDetail.getPublicGists()));
                    txtFollowing.setText(String.valueOf(dataDetail.getFollowing()));
                    txtFollower.setText(String.valueOf(dataDetail.getFollowers()));
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(DetailActivity.this, "Request Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Request Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}