package com.kebapi_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kebapi_android.R;
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

        // Set up the adapter with click handling
        itemAdapter = new ItemAdapter(itemList, this::onItemClick);
        itemView.setAdapter(itemAdapter);

        presenter.loadItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public void ListItems(List<Item> menuItemList) {
        itemList.clear();
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

    // Handle item clicks
    private void onItemClick(Item item) {
        Intent intent = new Intent(this, ItemDetailView.class);
        intent.putExtra("name", item.getName());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("price", item.getPrice());
        startActivity(intent);
    }
}
