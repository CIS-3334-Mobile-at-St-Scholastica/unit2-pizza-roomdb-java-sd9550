package cis3334.java_pizzaorderstart;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/***
 *  Details for a single pizza
 *  Stefan D CIS 3334
 */
@Entity(tableName = "Pizza")
public class Pizza {
    @PrimaryKey(autoGenerate = true)
    long id;
    final static Double[] PIZZA_PRICES = {7.99, 9.99, 12.99, 14.99};
    final static String[] PIZZA_SIZES = {"Small","Medium","Large","X-Large"};
    private String topping;
    private Double price;
    private Integer size;        // size of the pizza as an integer -- sizes are 0=Small, 1=Medium, 2=Large, 3=X-large
    private String strSize;      // size of the pizza as a string
    private String description;  // string description of the pizza for display

    /***
     *  Constructor
     * @param topping - this is a string listing all the toppings
     * @param size - this is the size of the pizza -- sizes are 0=Small, 1=Medium, 2=Large, 3=X-large
     */
    public Pizza(String topping, Integer size) {
        this.topping = topping;
        this.size = size;
        price = PIZZA_PRICES[size];
        strSize = PIZZA_SIZES[size];
        description = strSize + " " + topping + " pizza";
    }

    public Double getPrice() {
        return price;
    }

    @NonNull
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStrSize() {
        return strSize;
    }

    public void setStrSize(String strSize) {
        this.strSize = strSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getPIZZA_SIZES() {
        return PIZZA_SIZES;
    }

    public Double[] getPIZZA_PRICES() {
        return PIZZA_PRICES;
    }
}
