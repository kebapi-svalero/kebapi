package com.kebapi_android.presenter;


import com.kebapi_android.contract.RegisterItemContract;
import com.kebapi_android.domain.Item;
import com.kebapi_android.model.RegisterItemModel;

public class RegisterItemPresenter implements RegisterItemContract.Presenter, RegisterItemContract.Model.OnRegisterItemListener {

    private RegisterItemContract.Model model;
    private RegisterItemContract.View view;

    public RegisterItemPresenter(RegisterItemContract.View view) {
        model = new RegisterItemModel();
        this.view = view;
    }

    @Override
    public void registerItem(Item item) {
        if (item.getName().isEmpty()) {
            view.showErrorMessage("El campo marca no puede estar vacío");
            return;
        }

        model.registerItem(item, this);
    }

    @Override
    public void onRegisterItemSuccess(Item registeredItem) {
        view.showSuccessMessage("Item registrado correctamente con el identificador " + registeredItem.getId());
    }

    @Override
    public void onRegisterItemError(String message) {
        view.showErrorMessage(message);
    }
}
