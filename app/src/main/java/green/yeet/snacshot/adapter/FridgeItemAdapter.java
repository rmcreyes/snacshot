package green.yeet.snacshot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import green.yeet.snacshot.R;
import green.yeet.snacshot.model.GroceryItem;

public class FridgeItemAdapter extends BaseAdapter {
    private List<GroceryItem> groceryItems;
    private LayoutInflater layoutInflater;

    public FridgeItemAdapter(List<GroceryItem> groceryItems, Context context) {
        this.groceryItems = groceryItems;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return groceryItems.size();
    }

    @Override
    public Object getItem(int i) {
        return groceryItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View fridgeItemView = layoutInflater.inflate(R.layout.content_fridge, null);

        GroceryItem groceryItem = groceryItems.get(i);

        TextView foodName = fridgeItemView.findViewById(R.id.food_name);
        foodName.setText(groceryItem.getName());

        TextView expiryDate = fridgeItemView.findViewById(R.id.expiry_date);
        TextView purchasedDate = fridgeItemView.findViewById(R.id.purchased_date);
        expiryDate.setText("1/1/19");
        purchasedDate.setText("1/1/18");

        return fridgeItemView;
    }


}
