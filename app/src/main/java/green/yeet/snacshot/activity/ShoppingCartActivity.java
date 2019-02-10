package green.yeet.snacshot.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import green.yeet.snacshot.R;
import green.yeet.snacshot.adapter.CartItemAdapter;
import green.yeet.snacshot.model.GroceryItem;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView itemList;

    private List<GroceryItem> groceryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setTitle("Shopping Cart");

        itemList = findViewById(R.id.item_list);

        fillItems();

        CartItemAdapter cartItemAdapter = new CartItemAdapter(groceryItems, getApplicationContext());
        itemList.setAdapter(cartItemAdapter);
    }

    private void fillItems() {
        groceryItems = new ArrayList<GroceryItem>();
        GroceryItem item1 = new GroceryItem("apple", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
        GroceryItem item2 = new GroceryItem("banana", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
        GroceryItem item3 = new GroceryItem("orange", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
        groceryItems.add(item1);
        groceryItems.add(item2);
        groceryItems.add(item3);
    }


}
