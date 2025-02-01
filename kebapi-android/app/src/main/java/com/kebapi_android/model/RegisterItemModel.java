package com.kebapi_android.model;

import android.util.Log;

import com.kebapi_android.api.ItemsApi;
import com.kebapi_android.api.ItemsApiInterface;

import com.kebapi_android.contract.RegisterItemContract;

import com.kebapi_android.domain.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterItemModel implements RegisterItemContract.Model {

    @Override
    public void registerItem(Item item, OnRegisterItemListener listener) {
        ItemsApiInterface itemsApi = ItemsApi.buildInstance();
        Call<Item> callRegisterItem = itemsApi.addItem(item);
        callRegisterItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful() && response.code() == 201) {
                    listener.onRegisterItemSuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onRegisterItemError("Validation error: " + response.message());
                } else if (response.code() == 500) {
                    listener.onRegisterItemError("Internal server error: " + response.message());
                } else {
                    listener.onRegisterItemError("Unexpected error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.e("RegisterItemModel", "API call failed", t);
                listener.onRegisterItemError("Connection error. Please try again.");
            }
        });
    }
}
