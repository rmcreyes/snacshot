package green.yeet.snacshot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import green.yeet.snacshot.R;
import green.yeet.snacshot.model.GroceryItem;

public class ItemInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        Intent myIntent = getIntent();

        String jsonGroceryItem = myIntent.getStringExtra("itemString");

        GroceryItem groceryItem = new GroceryItem(jsonGroceryItem);

        findViewById(R.id.item_list);



    }
}
