package com.kebapi_android.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.kebapi_android.R;
import com.kebapi_android.contract.CustomerListContract;
import com.kebapi_android.domain.Customer;
import com.kebapi_android.domain.Item;
import com.kebapi_android.presenter.CustomerListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CustomerProfileView extends AppCompatActivity implements CustomerListContract.View {

    private TextView userName, userEmail, userPhone, userPassword;
    private Button editButton;
    private CustomerListPresenter presenter;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_KEY = "user_key";
    SharedPreferences sharedpreferences;
    String customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_profile);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        customerName = sharedpreferences.getString(USER_KEY, null);

        // Initialize Views
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        editButton = findViewById(R.id.editButton);

        // Initialize Presenter
        presenter = new CustomerListPresenter(this);

        presenter.loadCustomer(0);

        editButton.setOnClickListener(v -> onEditButtonClicked());
        userName.setText(customerName);
        userPassword.setText("••••••••");
    }

    private void onEditButtonClicked() {
        Intent intent = new Intent(this, EditCustomer.class);
        startActivity(intent);
    }
    @Override
    public void ListCustomers(List<Customer> menuitemList) {

    }

    @Override
    public void ListCustomer(Customer customer) {
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSuccessMessage(String message) {

    }
}
