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
        presenter.loadItems();

        itemList = new ArrayList<>();

        RecyclerView itemView = findViewById(R.id.item_recycler_view);
        itemView.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemView.setLayoutManager(layoutManager);

        itemAdapter = new ItemAdapter(itemList);
        itemView.setAdapter(itemAdapter);


        nextButton.setOnClickListener(v -> onNextButtonClicked());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public void ListItems(List<Item> menuitemList) {
        this.menuItemList.addAll(menuitemList);
        menuItemAdapter.notifyDataSetChanged();
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
        List<Item> selectedMenuItems = menuItemAdapter.getSelectedItems();

    }
}
