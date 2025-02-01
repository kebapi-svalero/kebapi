package com.kebapi_android.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.kebapi_android.R;

public class ItemDetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        TextView itemName = findViewById(R.id.detail_item_name);
        TextView itemDescription = findViewById(R.id.detail_item_description);
        TextView itemPrice = findViewById(R.id.detail_item_price);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String price = getIntent().getStringExtra("price");

        itemName.setText(name);
        itemDescription.setText(description);
        itemPrice.setText(price);
    }
}
