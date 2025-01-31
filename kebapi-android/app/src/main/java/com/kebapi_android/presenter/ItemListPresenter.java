
package com.kebapi_android.presenter;

import com.kebapi_android.contract.ItemListContract;
import com.kebapi_android.model.ItemListModel;
import com.kebapi_android.domain.Item;

import java.util.List;

public class ItemListPresenter implements ItemListContract.Presenter, ItemListContract.Model.OnItemLoadedListener {

    private ItemListContract.View view;
    private ItemListContract.Model model;

    public ItemListPresenter(ItemListContract.View view) {
        this.view = view;
        model = new ItemListModel();
    }

    @Override
    public void onLoadItemSuccess(List<Item> itemList) {
        view.ListItems(itemList);
    }

    @Override
    public void onLoadItemFailed(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void loadItems() {
        model.loadItems(this);
    }

}
