package org.example.grouping_model;

public class FoodFilter {
    private String category;
    private String name;
    private String topNutrients;
    private Integer dailyPercent;
    private Integer calories;
    private String quantity;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopNutrients() {
        return topNutrients;
    }

    public void setTopNutrients(String topNutrients) {
        this.topNutrients = topNutrients;
    }

    public Integer getDailyPercent() {
        return dailyPercent;
    }

    public void setDailyPercent(Integer dailyPercent) {
        this.dailyPercent = dailyPercent;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
