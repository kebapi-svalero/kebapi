package com.kebapi_android.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.kebapi_android.R;
import com.kebapi_android.contract.CustomerListContract;
import com.kebapi_android.domain.Customer;
import com.kebapi_android.presenter.CustomerListPresenter;

import java.util.List;

public class ViewCustomer extends AppCompatActivity implements CustomerListContract.View {

    private TextView userName, userEmail, userPhone, userPassword;
    private Button editButton;
    private CustomerListPresenter presenter;

    private SharedPreferences sharedpreferences;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_KEY = "user_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_customer);

        // Initialize Views
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPhone = findViewById(R.id.userPhone);
        userPassword = findViewById(R.id.userPassword);
        editButton = findViewById(R.id.editButton);

        // Initialize Presenter
        presenter = new CustomerListPresenter(this);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String username = sharedpreferences.getString(USER_KEY, null);



        presenter.loadCustomer(1);
    }

    @Override
    public void ListCustomers(List<Customer> menuitemList) {

    }

    @Override
    public void ListCustomer(Customer customer) {
        userName.setText(customer.getName());
        userEmail.setText(customer.getEmail());
        userPhone.setText(customer.getPhone());
        userPassword.setText("••••••••");
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSuccessMessage(String message) {

    }
}