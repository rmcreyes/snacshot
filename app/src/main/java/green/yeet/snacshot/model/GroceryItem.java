package green.yeet.snacshot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GroceryItem {
    private String name;
    private Date commitmentDate;
    private Date purchasedDate;
    private Date expirationDate;
    private Map<String, Float> nutrients;
    private int quantity;


    public GroceryItem(String name,
                       Date commitmentDate, Date purchasedDate, Date expirationDate,
                       int quantity, float calories, float totalFat, float cholesterol,
                       float sodium, float potassium, float totalCarbohydrates, float sugars, float protein) {
        this.name = name;
        this.commitmentDate = commitmentDate;
        this.purchasedDate = purchasedDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.nutrients = new HashMap<String, Float>();
        this.nutrients.put("calories", calories);
        this.nutrients.put("totalFat", totalFat);
        this.nutrients.put("cholesterol", cholesterol);
        this.nutrients.put("sodium", sodium);
        this.nutrients.put("potassium", potassium);
        this.nutrients.put("totalCarbohydrates", totalCarbohydrates);
        this.nutrients.put("sugars", sugars);
        this.nutrients.put("protein", protein);


    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCommitmentDateDate() {
        return this.commitmentDate;
    }

    public void setCommitmentDate(Date commitmentDate) {
        this.commitmentDate = commitmentDate;
    }

    public Date getPurchasedDateDate() {
        return this.purchasedDate;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public float getNutrient(String key) {
        return this.nutrients.get(key);
    }

}
