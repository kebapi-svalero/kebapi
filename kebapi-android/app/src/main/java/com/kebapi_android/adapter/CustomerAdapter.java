package com.kebapi_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kebapi_android.R;
import com.kebapi_android.domain.Customer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> {

    private List<Customer> customerList;

    public CustomerAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @NotNull
    @Override
    public CustomerAdapter.CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_view_item, parent, false);
        return new CustomerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerHolder customerHolder, int position) {
        customerHolder.username.setText(customerList.get(position).getName());
        customerHolder.phone.setText(customerList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class CustomerHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView phone;

        public CustomerHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.item_username);
            phone = itemView.findViewById(R.id.item_phone);
        }
    }
}
