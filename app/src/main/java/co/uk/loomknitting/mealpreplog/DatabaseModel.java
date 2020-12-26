package co.uk.loomknitting.mealpreplog;

//This helps load the data into our RecyclerView

public class DatabaseModel {
    private int _id;
    private String name;
    private String type;
    private String description;
    private String ingredients;

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getLow_carb() {
        return low_carb;
    }

    public int getLow_fat() {
        return low_fat;
    }

    public int getVegetarian() {
        return vegetarian;
    }

    public int getCompleted() {
        return completed;
    }

    public int getStars() {
        return stars;
    }

    public int getFavourite() {
        return favourite;
    }

    public int getEasy() {
        return easy;
    }

    public int getWeeknumber() {
        return weeknumber;
    }

    private int low_carb;
    private int low_fat;
    private int vegetarian;
    private int completed;
    private int stars;
    private int favourite;
    private int easy;

    public void setWeeknumber(int weeknumber) {
        this.weeknumber = weeknumber;
    }

    private int weeknumber;

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setLow_carb(int low_carb) {
        this.low_carb = low_carb;
    }

    public void setLow_fat(int low_fat) {
        this.low_fat = low_fat;
    }

    public void setVegetarian(int vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public void setEasy(int easy) {
        this.easy = easy;
    }

}
