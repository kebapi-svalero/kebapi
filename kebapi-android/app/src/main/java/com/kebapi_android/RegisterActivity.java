package com.kebapi_android;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.kebapi_android.R;
import com.kebapi_android.contract.RegisterCustomerContract;
import com.kebapi_android.presenter.RegisterCustomerPresenter;
import com.kebapi_android.domain.Customer;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kebapi_android.view.ItemListView;


public class RegisterActivity extends AppCompatActivity implements RegisterCustomerContract.View {

    private RegisterCustomerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        presenter = new RegisterCustomerPresenter(this);
    }

    public void addCustomer(View view) {
        try {
            EditText CustomerName = findViewById(R.id.username);
            EditText CustomerPhone = findViewById(R.id.phone);
            EditText CustomerEmail = findViewById(R.id.email);
            EditText CustomerPassword = findViewById(R.id.password);

            String name = CustomerName.getText().toString();
            String phone = CustomerPhone.getText().toString();
            String email = CustomerEmail.getText().toString();
            String password = CustomerPassword.getText().toString();

            Customer customer = new Customer(name, email, phone, password);
            presenter.registerCustomer(customer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginPage(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.registerButton), message, BaseTransientBottomBar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Snackbar.make(findViewById(R.id.registerButton), message, BaseTransientBottomBar.LENGTH_SHORT)
                .addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        Intent intent = new Intent(RegisterActivity.this, ItemListView.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}