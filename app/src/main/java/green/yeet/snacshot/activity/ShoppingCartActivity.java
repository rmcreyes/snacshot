package green.yeet.snacshot.activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import green.yeet.snacshot.R;
import green.yeet.snacshot.adapter.CartItemAdapter;
import green.yeet.snacshot.model.GroceryItem;

public class ShoppingCartActivity extends AppCompatActivity {


    private static final int ADD_ITEM = 0;

    private ListView itemList;
    private FloatingActionButton addButton;
    private ImageView checkoutButton;

    private List<GroceryItem> groceryItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        groceryItems = new ArrayList<GroceryItem>();

        itemList = findViewById(R.id.item_list);
        addButton = findViewById(R.id.add_btn);

        checkoutButton = findViewById(R.id.checkout_btn);
        View nutritionBottomSheet = findViewById(R.id.nutrients_bottom_sheet);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(nutritionBottomSheet);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                checkoutButton.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShoppingCartActivity.this, "checkout", Toast.LENGTH_SHORT).show();
                JSONArray jsonGroceryItems = new JSONArray();
                for(GroceryItem groceryItem : groceryItems) {
                    try {
                        JSONObject jsonGroceryItem = new JSONObject(groceryItem.jsonify());
                        jsonGroceryItems.put(jsonGroceryItem);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent myIntent = new Intent(getApplicationContext(), FridgeActivity.class);
                myIntent.putExtra("key", jsonGroceryItems.toString());
                startActivity(myIntent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ItemValidationActivity.class);
                startActivity(intent);
                startActivityForResult(intent, ADD_ITEM);
            }
        });

        CartItemAdapter cartItemAdapter = new CartItemAdapter(groceryItems, getApplicationContext());
        itemList.setAdapter(cartItemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String newGroceryItem_s = data.getStringExtra("key");
        GroceryItem newGroceryItem = new GroceryItem(newGroceryItem_s);
        groceryItems.add(newGroceryItem);
        CartItemAdapter cartItemAdapter = new CartItemAdapter(groceryItems, getApplicationContext());
        itemList.setAdapter(cartItemAdapter);
    }

}
