package com.kebapi_android.model;

import android.util.Log;

import com.kebapi_android.api.CustomersApi;
import com.kebapi_android.api.CustomersApiInterface;
import com.kebapi_android.contract.CustomerListContract;
import com.kebapi_android.domain.Customer;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerListModel implements CustomerListContract.Model {

    @Override
    public void loadCustomers(CustomerListContract.Model.OnCustomerLoadedListener listener) {
        CustomersApiInterface customersApi = CustomersApi.buildInstance();
        Call<List<Customer>> getCustomersCall = customersApi.getCustomers();
        getCustomersCall.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (response.code() == 200) {
                    listener.onLoadCustomersSuccess(response.body());
                } else if (response.code() == 500 ){
                    listener.onLoadCustomerFailed("La API no se encuentra disponible en este momento. Prueba de nuevo");
                } else {
                    listener.onLoadCustomerFailed(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.e("loadCustomers", "error", t);
                listener.onLoadCustomerFailed("No se puedo conectar con el origen de datos. " +
                        "Comprueba la conexión e inténtalo otra vez");
            }
        });
    }

    @Override
    public void loadCustomer(OnCustomerLoadedListener listener, int customerId) {
        CustomersApiInterface customersApi = CustomersApi.buildInstance();
        Call<Customer> getCustomerCall = customersApi.getCustomer(customerId);
        getCustomerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.code() == 200) {
                    listener.onLoadCustomerSuccess(response.body());
                } else if (response.code() == 500 ){
                    listener.onLoadCustomerFailed("La API no se encuentra disponible en este momento. Prueba de nuevo");
                } else {
                    listener.onLoadCustomerFailed(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e("loadCustomer", "error", t);
                listener.onLoadCustomerFailed("No se puedo conectar con el origen de datos. " +
                        "Comprueba la conexión e inténtalo otra vez");
            }
        });
    }

    @Override
    public void loadCustomerByName(OnCustomerLoadedListener listener, String customerName) {
        CustomersApiInterface customersApi = CustomersApi.buildInstance();
        Call<Customer> getCustomerCall = customersApi.getCustomerByName(customerName);
        getCustomerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.code() == 200) {
                    listener.onLoadCustomerSuccess(response.body());
                } else if (response.code() == 500 ){
                    listener.onLoadCustomerFailed("La API no se encuentra disponible en este momento. Prueba de nuevo");
                } else {
                    listener.onLoadCustomerFailed(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                listener.onLoadCustomerFailed("No se puedo conectar con el origen de datos. " +
                        "Comprueba la conexión e inténtalo otra vez");
            }
        });
    }
}
