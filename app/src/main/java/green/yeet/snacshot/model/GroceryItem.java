package green.yeet.snacshot.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GroceryItem {
    private String name;

    double calories;
    private Map<String, Integer> commitmentDate;
    private Map<String, Integer> expirationDate;
    private Map<String, Double> nutrients;
    private String uri_string;


    public GroceryItem(String name,
                       Map<String, Integer> commitmentDate, Map<String, Integer> expirationDate,
                       double calories, double totalFat, double cholesterol,
                       double sodium, double potassium, double totalCarbohydrates, double sugars, double protein, String uri_string) {

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
        this.uri_string = uri_string;
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
            this.nutrients = nutrients;

            this.uri_string = jsonGroceryItem.getString("uri_string");

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

            jsonGroceryItem.put("calories", this.calories);

            JSONObject jsonNutrients = new JSONObject();
            jsonNutrients.put("totalFat", this.nutrients.get("totalFat"));
            jsonNutrients.put("cholesterol", this.nutrients.get("cholesterol"));
            jsonNutrients.put("sodium", this.nutrients.get("sodium"));
            jsonNutrients.put("potassium", this.nutrients.get("potassium"));
            jsonNutrients.put("totalCarbohydrates", this.nutrients.get("totalCarbohydrates"));
            jsonNutrients.put("sugar", this.nutrients.get("sugar"));
            jsonNutrients.put("protein", this.nutrients.get("protein"));
            jsonGroceryItem.put("nutrients", jsonNutrients.toString());

            jsonGroceryItem.put("uri_string", this.uri_string);

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

    public double getCalories(){
        return this.calories;
    }

    public double getNutrient(String key) {
        return this.nutrients.get(key);
    }

    public String getUriString() {
        return this.uri_string;
    }

}
