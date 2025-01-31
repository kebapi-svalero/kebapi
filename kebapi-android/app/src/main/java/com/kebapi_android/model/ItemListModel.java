package com.kebapi_android.model;

import android.view.MenuItem;
import com.kebapi_android.api.ItemsApi;
import com.kebapi_android.api.ItemsApiInterface;
import com.kebapi_android.contract.ItemListContract;
import com.kebapi_android.domain.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListModel implements ItemListContract.Model {

    @Override
    public void loadItems(OnItemLoadedListener listener) {
        ItemsApiInterface itemsApi = ItemsApi.buildInstance();
        Call<List<Item>> getItemsCall = itemsApi.getItems();
        getItemsCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.code() == 200) {
                    listener.onLoadItemSuccess(response.body());
                } else if (response.code() == 500 ){
                    listener.onLoadItemFailed("La API no se encuentra disponible en este momento. Prueba de nuevo");
                } else {
                    listener.onLoadItemFailed(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                listener.onLoadItemFailed("No se puedo conectar con el origen de datos. " +
                        "Comprueba la conexión e inténtalo otra vez");
            }
        });
    }
}
