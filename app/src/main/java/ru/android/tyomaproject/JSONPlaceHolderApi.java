package ru.android.tyomaproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/posts/")
    public Call<List<Post>> getPostWithUserID(@Query("userId") int userId);
}
