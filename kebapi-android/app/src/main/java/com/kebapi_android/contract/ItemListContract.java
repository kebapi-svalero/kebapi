package com.kebapi_android.contract;

import com.kebapi_android.domain.Item;

import java.util.List;

public interface ItemListContract {
    interface Model {
        interface OnItemLoadedListener {
            void onLoadItemSuccess(List<Item> itemList);
            void onLoadItemFailed(String message);
        }
        void loadItems(OnItemLoadedListener listener);
    }

    interface View {
        void ListItems(List<Item> itemList);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void loadItems();
    }
}
