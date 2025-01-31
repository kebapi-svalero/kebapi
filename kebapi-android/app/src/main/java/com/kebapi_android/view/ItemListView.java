package com.kebapi_android.view;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kebapi_android.R;
import com.kebapi_android.adapter.ItemAdapter;
import com.kebapi_android.contract.ItemListContract;
import com.kebapi_android.domain.Item;
import com.kebapi_android.presenter.ItemListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ItemListView extends AppCompatActivity implements ItemListContract.View {
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private ItemListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);

        presenter = new ItemListPresenter(this);
        itemList = new ArrayList<>();

        RecyclerView itemView = findViewById(R.id.item_view);
        itemView.setHasFixedSize(true);
        itemView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(itemList);
        itemView.setAdapter(itemAdapter);

        Button nextButton = findViewById(R.id.registerMeal);
        nextButton.setOnClickListener(v -> onNextButtonClicked());

        presenter.loadItems();  // Ensure this is called after initialization
    }

    @Override
    public void ListItems(List<Item> menuItemList) {
        itemList.clear();  // Prevent duplicate items
        itemList.addAll(menuItemList);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onNextButtonClicked() {
        List<Item> selectedMenuItems = itemAdapter.getSelectedItems();
        if (selectedMenuItems.isEmpty()) {
            Toast.makeText(this, "Please select at least one item.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Proceed with selected items (e.g., start new activity or send data)
        Toast.makeText(this, selectedMenuItems.size() + " items selected.", Toast.LENGTH_SHORT).show();
    }
}
