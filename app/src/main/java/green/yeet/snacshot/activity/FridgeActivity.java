package green.yeet.snacshot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import green.yeet.snacshot.R;
import green.yeet.snacshot.adapter.FridgeItemAdapter;
import green.yeet.snacshot.model.GroceryItem;


    public class FridgeActivity extends AppCompatActivity {

        private static final int ADD_ITEM = 0;
        private ListView itemList;

        private List<GroceryItem> groceryItems;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fridge);
            setTitle("Fridge");

            groceryItems = new ArrayList<GroceryItem>();

            itemList = findViewById(R.id.item_list);

            fillItems();

            itemList = findViewById(R.id.item_list);
            FridgeItemAdapter fridgeItemAdapter = new FridgeItemAdapter(groceryItems, this);
            itemList.setAdapter(fridgeItemAdapter);

            itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GroceryItem tappedGroceryItem = groceryItems.get(position);
                    Intent myIntent = new Intent(getApplicationContext(), ItemInfoActivity.class);
                    myIntent.putExtra("itemString", tappedGroceryItem.jsonify());
                    startActivity(myIntent);
                }
            });



            itemList.setAdapter(fridgeItemAdapter);
        }


        private void fillItems() {
            Map<String, Integer> commitmentDate = new HashMap<String, Integer>();
            Map<String, Integer> expirationDate = new HashMap<String, Integer>();
            groceryItems = new ArrayList<GroceryItem>();
            GroceryItem item1 = new GroceryItem("Apple", commitmentDate, expirationDate, 50,4,6,7,3,80,80,35 );
            GroceryItem item2 = new GroceryItem("Orange", commitmentDate, expirationDate, 80,5,6,7,9,90,120,25 );
            groceryItems.add(item1);
            groceryItems.add(item2);

        }
}
