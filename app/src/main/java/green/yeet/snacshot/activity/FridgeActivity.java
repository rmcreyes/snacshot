package green.yeet.snacshot.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import green.yeet.snacshot.R;
import green.yeet.snacshot.adapter.FridgeItemAdapter;
import green.yeet.snacshot.model.GroceryItem;


    public class FridgeActivity extends AppCompatActivity {

        private ListView itemList;

        private List<GroceryItem> groceryItems;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fridge);
            setTitle("Fridge");


            itemList = findViewById(R.id.item_list);

            fillItems();

            itemList = findViewById(R.id.item_list);
            FridgeItemAdapter fridgeItemAdapter = new FridgeItemAdapter(groceryItems, this);
            itemList.setAdapter(fridgeItemAdapter);

            itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GroceryItem tappedGroceryItem = groceryItems.get(position);

                }
            });

            itemList.setAdapter(fridgeItemAdapter);
        }



        private void fillItems() {
            groceryItems = new ArrayList<GroceryItem>();
            GroceryItem item1 = new GroceryItem("apple", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
            GroceryItem item2 = new GroceryItem("banana", new Date(), new Date(), new Date(), 1, 1, 1, 1, 1, 1, 1, 1, 1);
            groceryItems.add(item1);
            groceryItems.add(item2);

        }
}
