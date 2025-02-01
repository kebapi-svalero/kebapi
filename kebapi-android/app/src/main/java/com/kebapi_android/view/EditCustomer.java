package com.kebapi_android.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.kebapi_android.R;
import com.kebapi_android.contract.CustomerListContract;
import com.kebapi_android.domain.Customer;
import com.kebapi_android.presenter.CustomerListPresenter;

import java.util.List;

public class EditCustomer extends AppCompatActivity implements CustomerListContract.View {

    private CustomerListPresenter presenter;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText password2EditText;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_KEY = "user_key";
    SharedPreferences sharedpreferences;
    String customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        customerName = sharedpreferences.getString(USER_KEY, null);

        // Initialize the EditText fields
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        password2EditText = findViewById(R.id.password2);
    }

    // Method to validate the customer input when the "Edit Profile" button is clicked
    public void validateCustomer(android.view.View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String password2 = password2EditText.getText().toString();

        // Validate inputs
        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(password2)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        presenter = new CustomerListPresenter(this);
        presenter.loadCustomerByName(customerName);

    }

    private void updateCustomerProfile(String username, String password) {
        Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
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
    public void showSuccessMessage(String message){
    }
}