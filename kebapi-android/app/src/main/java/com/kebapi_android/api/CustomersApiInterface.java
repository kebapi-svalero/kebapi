package com.kebapi_android.api;

import java.util.List;

import com.kebapi_android.domain.Customer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface CustomersApiInterface {

    @GET("customers")
    Call<List<Customer>> getCustomers();

    @POST("customers")
    Call<Customer> addCustomer(@Body Customer customer);

    @GET("customers/{customerId}")
    Call<Customer> getCustomer(@Path("customerId") int customerId);

    @GET("customers/search")
    Call<Customer> getCustomerByName(@Query("name") String name);

    @PUT("customers/{customerId}")
    Call<Void> updateCustomer(@Path("customerId") long id, @Body Customer customer);
}
