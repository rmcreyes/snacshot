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

public class CartItemAdapter extends BaseAdapter {

    private List<GroceryItem> groceryItems;
    private LayoutInflater layoutInflater;

    public CartItemAdapter(List<GroceryItem> groceryItems, Context context) {
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
        View cartItemView = layoutInflater.inflate(R.layout.detail_cart_item, null);

        GroceryItem groceryItem = groceryItems.get(i);

        TextView foodName = cartItemView.findViewById(R.id.food_name);
        foodName.setText(groceryItem.getName());

        TextView fatVal = cartItemView.findViewById(R.id.fat_value);
        TextView carbVal = cartItemView.findViewById(R.id.carb_value);
        TextView proteinVal = cartItemView.findViewById(R.id.protein_value);
        fatVal.setText(stringifyMacroVal(groceryItem.getNutrient("totalFat")));
        carbVal.setText(stringifyMacroVal(groceryItem.getNutrient("totalCarbohydrates")));
        proteinVal.setText(stringifyMacroVal(groceryItem.getNutrient("protein")));

        return cartItemView;
    }


    private String stringifyMacroVal(double val) {
        return Double.toString(val) + "g";
    }
}
