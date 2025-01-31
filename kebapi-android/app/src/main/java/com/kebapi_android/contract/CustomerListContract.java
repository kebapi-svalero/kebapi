package com.kebapi_android.contract;

import com.kebapi_android.domain.Customer;

import java.util.List;

public interface CustomerListContract {
    interface Model {
        interface OnCustomerLoadedListener {
            void onLoadCustomersSuccess(List<Customer> customerList);
            void onLoadCustomerSuccess(Customer customer);
            void onLoadCustomerFailed(String message);
        }
        void loadCustomers(OnCustomerLoadedListener listener);
        void loadCustomer(OnCustomerLoadedListener listener, int customerId);
        void loadCustomerByName(OnCustomerLoadedListener listener, String customerName);
    }

    interface View {
        void ListCustomers(List<Customer> menuitemList);
        void ListCustomer(Customer customer);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void loadCustomers();
        void loadCustomer(int customerId);
        void loadCustomerByName(String customerName);

    }
}