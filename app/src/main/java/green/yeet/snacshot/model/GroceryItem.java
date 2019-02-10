package green.yeet.snacshot.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GroceryItem {
    private String name;
    double calories;
    private Map<String, Integer> commitmentDate;
    private Map<String, Integer> expirationDate;
    private Map<String, Double> nutrients;


    public GroceryItem(String name,
                       Map<String, Integer> commitmentDate, Map<String, Integer> expirationDate,
                       double calories, double totalFat, double cholesterol,
                       double sodium, double potassium, double totalCarbohydrates, double sugars, double protein) {
        this.name = name;
        this.commitmentDate = commitmentDate;
        this.expirationDate = expirationDate;
        this.calories = calories;
        this.nutrients = new HashMap<String, Double>();
        this.nutrients.put("totalFat", totalFat);
        this.nutrients.put("cholesterol", cholesterol);
        this.nutrients.put("sodium", sodium);
        this.nutrients.put("potassium", potassium);
        this.nutrients.put("totalCarbohydrates", totalCarbohydrates);
        this.nutrients.put("sugars", sugars);
        this.nutrients.put("protein", protein);
    }

    public GroceryItem(String json) {
        try {
            JSONObject jsonGroceryItem = new JSONObject(json);
            this.name = jsonGroceryItem.getString("name");

            Map<String, Integer> commitmentDate = new HashMap<String, Integer>();
            JSONObject jsonCommitmentDate = new JSONObject(jsonGroceryItem.getString("commitmentDate"));
            commitmentDate.put("day", jsonCommitmentDate.getInt("day"));
            commitmentDate.put("month", jsonCommitmentDate.getInt("month"));
            commitmentDate.put("year", jsonCommitmentDate.getInt("year"));
            this.commitmentDate = commitmentDate;

            Map<String, Integer> expirationDate = new HashMap<String, Integer>();
            JSONObject jsonExpirationDate = new JSONObject(jsonGroceryItem.getString("expirationDate"));
            commitmentDate.put("day", jsonExpirationDate.getInt("day"));
            commitmentDate.put("month", jsonExpirationDate.getInt("month"));
            commitmentDate.put("year", jsonExpirationDate.getInt("year"));
            this.expirationDate = expirationDate;

            this.calories = jsonGroceryItem.getDouble("calories");

            Map<String, Double> nutrients = new HashMap<String, Double>();
            JSONObject jsonNutrients = new JSONObject(jsonGroceryItem.getString("jsonNutrients"));
            nutrients.put("totalFat", jsonNutrients.getDouble("totalFat"));
            nutrients.put("cholesterol", jsonNutrients.getDouble("cholesterol"));
            nutrients.put("sodium", jsonNutrients.getDouble("sodium"));
            nutrients.put("potassium", jsonNutrients.getDouble("potassium"));
            nutrients.put("totalCarbohydrates", jsonNutrients.getDouble("totalCarbohydrates"));
            nutrients.put("sugars", jsonNutrients.getDouble("sugars"));
            nutrients.put("protein", jsonNutrients.getDouble("protein"));

        } catch(JSONException e) {
            e.printStackTrace();
        }

    }

    public String jsonify() {
        JSONObject jsonGroceryItem = new JSONObject();
        try {
            jsonGroceryItem.put("name", name);

            JSONObject jsonCommitmentDate  = new JSONObject();
            jsonCommitmentDate.put("day", this.commitmentDate.get("day"));
            jsonCommitmentDate.put("month", this.commitmentDate.get("month"));
            jsonCommitmentDate.put("year", this.commitmentDate.get("year"));
            jsonGroceryItem.put("commitmentDate", jsonCommitmentDate.toString());

            JSONObject jsonExpirationDate = new JSONObject();
            jsonExpirationDate.put("day", this.commitmentDate.get("day"));
            jsonExpirationDate.put("month", this.commitmentDate.get("month"));
            jsonExpirationDate.put("year", this.commitmentDate.get("year"));
            jsonGroceryItem.put("expirationDate", jsonGroceryItem.toString());

            jsonGroceryItem.put("calories", calories);

            JSONObject jsonNutrients = new JSONObject();
            jsonNutrients.put("totalFat", nutrients.get("totalFat"));
            jsonNutrients.put("cholesterol", nutrients.get("cholesterol"));
            jsonNutrients.put("sodium", nutrients.get("sodium"));
            jsonNutrients.put("potassium", nutrients.get("potassium"));
            jsonNutrients.put("totalCarbohydrates", nutrients.get("totalCarbohydrates"));
            jsonNutrients.put("sugar", nutrients.get("sugar"));
            jsonNutrients.put("protein", nutrients.get("protein"));
            jsonGroceryItem.put("nutrients", jsonNutrients.toString());

            return jsonGroceryItem.toString();

        } catch(JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getCommitmentDateDate() {
        return this.commitmentDate;
    }

    public void setCommitmentDate(Map<String, Integer> commitmentDate) {
        this.commitmentDate = commitmentDate;
    }

    public Map<String, Integer> getExpirationDate() {
        return this.expirationDate;
    }

    public double getNutrient(String key) {
        return this.nutrients.get(key);
    }

}
