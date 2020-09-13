package id.sam.submission2bfaa.service;

import id.sam.submission2bfaa.model.DetailModel;
import id.sam.submission2bfaa.model.search.SearchModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    Call<SearchModel> getSearchUser(@Query("q") String username);

    @GET("users/{username}")
    Call<DetailModel> getDetailUser(@Path("username") String username);
}
