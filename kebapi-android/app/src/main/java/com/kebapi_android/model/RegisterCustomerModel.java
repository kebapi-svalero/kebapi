package com.kebapi_android.model;

import android.util.Log;

import android.widget.Toast;
import com.kebapi_android.RegisterActivity;
import com.kebapi_android.api.CustomersApi;
import com.kebapi_android.api.CustomersApiInterface;
import com.kebapi_android.contract.RegisterCustomerContract;
import com.kebapi_android.domain.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCustomerModel implements RegisterCustomerContract.Model {

    @Override
    public void registerCustomer(Customer customer, OnRegisterCustomerListener listener) {
        CustomersApiInterface customersApi = CustomersApi.buildInstance();
        Call<Customer> callRegisterCustomer = customersApi.addCustomer(customer);
        callRegisterCustomer.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful() && response.code() == 201) {
                    Log.e("RegisterCustomerModel", "API call worked!!!!!!!!!");
                    listener.onRegisterCustomerSuccess(response.body());
                } else if (response.code() == 400) {
                    Log.e("RegisterCustomerModel", "API 400 errorrrrrrrr!");
                    listener.onRegisterCustomerError("Validation error: " + response.message());
                } else if (response.code() == 500) {
                    Log.e("RegisterCustomerModel", "API 500 errorrrrrrrr!");
                    listener.onRegisterCustomerError("Internal server error: " + response.message());
                } else {
                    Log.e("RegisterCustomerModel", "API other errorrrrrrrr!");
                    listener.onRegisterCustomerError("Unexpected error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e("RegisterCustomerModel", "API call failed", t);
                listener.onRegisterCustomerError("Connection error. Please try again.");
            }
        });
    }
}
