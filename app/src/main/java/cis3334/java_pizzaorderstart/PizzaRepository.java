package cis3334.java_pizzaorderstart;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class PizzaRepository {
    private PizzaDao pizzaDao;
    private LiveData<List<Pizza>> allPizzas;

    PizzaRepository(Application application) {
        PizzaDatabase db = PizzaDatabase.getDatabase(application);
        Log.d("CIS 3334", "PizzaRepository Setting up the Dao and  database");
        pizzaDao = db.pizzaDao();
        Log.d("CIS 3334", "PizzaRepository Dao created");
        allPizzas = pizzaDao.getAll();

    }

    public void OrderPizza(Pizza newPizza){
        Log.d("CIS 3334", "PizzaRepository OrderPizza creating pizzza");
        //Pizza newPizza = new Pizza(topping, size);
        Log.d ("CIS 3334", newPizza.toString());
        // ROOM -- Add insert into database
        PizzaDatabase.databaseWriteExecutor.execute(() -> {
            Log.d("CIS 3334", "PizzaRepository - OrderPizza:databaseWriteExecutor - starting up heartrateDao");
            pizzaDao.insert(newPizza);
            Log.d("CIS 3334", "PizzaRepository - OrderPizza:databaseWriteExecutor -Inserting pizza into database");
            //Log.d ("CIS 3334", newPizza.toString());
        });
    }


    /***
     * Retrieve the full order
     * @return a list of pizzas, each one describe by a single string
     */
//    public LiveData<List<Pizza>> getOrder() {
//        PizzaDatabase.databaseWriteExecutor.execute(() -> {
//            Log.d("CIS 3334", "PizzaRepository - getOrder:databaseWriteExecutor - starting up");
//            allPizzas = pizzaDao.getAll();              // ROOM - add this
//            Log.d("CIS 3334", "PizzaRepository - getOrder:databaseWriteExecutor - got order");
//            //Log.d ("CIS 3334", newPizza.toString());
//        });
//        Log.d("CIS 3334", "PizzaRepository - getOrder: pizzaOrder size = "+ allPizzas.size());
//
//        ArrayList<String> strOrder = new ArrayList<String>();
//        for (Pizza p:allPizzas ){
//            strOrder.add(p.toString());
//        }
//        return strOrder;
//    }

    public LiveData<List<Pizza>> getOrder() { //
        return allPizzas;
    }

    /***
     * Calculate the total bill for this order
     * @return total bill as Double
     *
    public Double getTotalBill() {
        Double total = 0.0;
        for (Pizza p:allPizzas ){
            total += p.getPrice();
        }
        if (delivery) {
            total += DELIVERY_PRICE;
        }
        return total;
    }

    public void setDelivery(boolean deliver) {
        this.delivery = deliver;
    }

    public boolean getDelivery() {
        return delivery;
    } */
}
