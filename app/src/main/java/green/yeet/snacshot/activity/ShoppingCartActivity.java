package green.yeet.snacshot.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    private ActionBarDrawerToggle toggle;

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

//    private void fillItems() {
//        groceryItems = new ArrayList<GroceryItem>();
//        GroceryItem item1 = new GroceryItem("apple", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
//        GroceryItem item2 = new GroceryItem("banana", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
//        GroceryItem item3 = new GroceryItem("orange", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
//        groceryItems.add(item1);
//        groceryItems.add(item2);
//        groceryItems.add(item3);
//    }


}
