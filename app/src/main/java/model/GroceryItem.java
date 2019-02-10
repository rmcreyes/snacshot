package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GroceryItem {
    String name;
    Date commitmentDate;
    Date purchasedDate;
    Date expirationDate;
    Map<String, Integer> nutrients;
    int quantity;


    public GroceryItem(String name,
                       Date commitmentDate, Date purchasedDate, Date expirationDate,
                       int quantity, int calories, int totalFat, int cholesterol,
                       int sodium, int potassium, int totalCarbohydrates, int sugars, int protein) {
        this.name = name;
        this.commitmentDate = commitmentDate;
        this.purchasedDate = purchasedDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.nutrients = new HashMap<String, Integer>();
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

}
