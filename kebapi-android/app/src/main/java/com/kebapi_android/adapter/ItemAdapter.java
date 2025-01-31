package com.kebapi_android.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kebapi_android.R;
import com.kebapi_android.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private List<Item> itemList;
    private List<Item> selectedMenuItems = new ArrayList<>();  // To track the selected menu items

    public ItemAdapter(List<Item> menuItemList) {
        this.itemList = menuItemList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuitem_view_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());

        // Handle radio button state
        holder.radioButton.setChecked(selectedMenuItems.contains(item));

        // Set listener for radio button click
        holder.radioButton.setOnClickListener(v -> {
            if (selectedMenuItems.contains(item)) {
                selectedMenuItems.remove(item);  // Deselect item if it was already selected
            } else {
                selectedMenuItems.add(item);  // Select item
            }
            notifyDataSetChanged();  // Notify adapter to update the view
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<Item> getSelectedMenuItems() {
        return selectedMenuItems;  // Return the list of selected menu items
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;
        private RadioButton radioButton;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            radioButton = itemView.findViewById(R.id.radioButton);  // Assuming you have a RadioButton in your item layout
        }
    }
}