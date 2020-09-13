package id.sam.submission2bfaa.service;

import id.sam.submission2bfaa.model.search.UserSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    Call<UserSearch> getSearchUser(
            @Query("q") String username
    );
}
