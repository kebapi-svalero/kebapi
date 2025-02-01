package com.kebapi_android.contract;



import com.kebapi_android.domain.Item;

import java.util.List;

public interface RegisterItemContract {

    void showErrorMessage(String message);

    void showSuccessMessage(String message);

    interface Model {
        interface OnRegisterItemListener {
            void onRegisterItemSuccess(Item registeredItem);
            void onRegisterItemError(String message);
        }
        void registerItem(Item item, OnRegisterItemListener listener);
    }

    interface View {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void registerItem(Item item);
    }
}
