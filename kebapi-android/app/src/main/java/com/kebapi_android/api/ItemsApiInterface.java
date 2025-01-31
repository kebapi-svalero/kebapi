package com.kebapi_android.api;

import java.util.List;

import com.kebapi_android.domain.Item;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemsApiInterface {

    @GET("items")
    Call<List<Item>> getItems();

    @POST("items")
    Call<Item> addItem(@Path("id") int id, @Body Item item);
}
