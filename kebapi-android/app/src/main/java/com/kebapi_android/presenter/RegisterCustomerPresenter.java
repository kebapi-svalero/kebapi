package com.kebapi_android.presenter;


import com.kebapi_android.contract.RegisterCustomerContract;
import com.kebapi_android.domain.Customer;
import com.kebapi_android.model.RegisterCustomerModel;

public class RegisterCustomerPresenter implements RegisterCustomerContract.Presenter, RegisterCustomerContract.Model.OnRegisterCustomerListener {

    private RegisterCustomerContract.Model model;
    private RegisterCustomerContract.View view;

    public RegisterCustomerPresenter(RegisterCustomerContract.View view) {
        model = new RegisterCustomerModel();
        this.view = view;
    }

    @Override
    public void registerCustomer(Customer customer) {
        if (customer.getName().isEmpty()) {
            view.showErrorMessage("El campo marca no puede estar vacío");
            return;
        }

        model.registerCustomer(customer, this);
    }

    @Override
    public void onRegisterCustomerSuccess(Customer registeredCustomer) {
        view.showSuccessMessage("Coche registrado correctamente con el identificador " + registeredCustomer.getId());
    }

    @Override
    public void onRegisterCustomerError(String message) {
        view.showErrorMessage(message);
    }
}
