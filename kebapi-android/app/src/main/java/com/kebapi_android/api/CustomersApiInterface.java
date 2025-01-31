package com.kebapi_android.api;

import java.util.List;

import com.kebapi_android.domain.Customer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomersApiInterface {

    @GET("customers")
    Call<List<Customer>> getCustomers();

    @POST("customers")
    Call<Customer> addCustomer(@Path("id") int id, @Body Customer customer);
}