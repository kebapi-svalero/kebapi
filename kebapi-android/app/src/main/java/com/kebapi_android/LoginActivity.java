package com.kebapi_android;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kebapi_android.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kebapi_android.contract.CustomerListContract;
import com.kebapi_android.domain.Customer;
import com.kebapi_android.presenter.CustomerListPresenter;
import com.kebapi_android.view.ItemListView;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements CustomerListContract.View {

    private EditText customerNameField;
    private EditText customerPasswordField;
    private CustomerListPresenter presenter;
    String username, password;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_KEY = "user_key";
    public static final String PASSWORD_KEY = "password_key";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        customerNameField = findViewById(R.id.username);
        customerPasswordField = findViewById(R.id.password);

        username = sharedpreferences.getString(USER_KEY, null);
        password = sharedpreferences.getString(PASSWORD_KEY, null);


        presenter = new CustomerListPresenter(this);

        presenter.loadCustomers();
    }

    public void validateCustomer(View view) {
        String name = customerNameField.getText().toString().trim();
        String password = customerPasswordField.getText().toString().trim();

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.loadCustomers();
    }

    public void registerPage(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ListCustomers(List<Customer> customerList) {
        // Perform validation here
        String enteredName = customerNameField.getText().toString();
        String enteredPassword = customerPasswordField.getText().toString();

        for (Customer customer : customerList) {
            if (customer.getName().equals(enteredName) && customer.getPassword().equals(enteredPassword)) {
                showSuccessMessage("Login successful!");
                return;
            }
        }
        showErrorMessage("Invalid username or password.");
    }

    @Override
    public void ListCustomer(Customer customer) {

    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.loginButton), message, BaseTransientBottomBar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Snackbar.make(findViewById(R.id.loginButton), message, BaseTransientBottomBar.LENGTH_SHORT)
                .addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(USER_KEY, customerNameField.getText().toString());
                        editor.putString(PASSWORD_KEY, customerPasswordField.getText().toString());

                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, ItemListView.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}
