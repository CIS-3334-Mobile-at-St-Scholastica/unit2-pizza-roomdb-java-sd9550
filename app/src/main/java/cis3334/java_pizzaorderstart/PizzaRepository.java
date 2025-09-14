package cis3334.java_pizzaorderstart;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PizzaRepository {
    private final PizzaDao pizzaDao;
    private final LiveData<List<Pizza>> allPizzas;

    PizzaRepository(Application application) {
        PizzaDatabase db = PizzaDatabase.getDatabase(application);
        Log.d("CIS 3334", "PizzaRepository Setting up the Dao and  database");
        pizzaDao = db.pizzaDao();
        Log.d("CIS 3334", "PizzaRepository Dao created");
        allPizzas = pizzaDao.getAll();

    }

    public void OrderPizza(Pizza newPizza){
        Log.d("CIS 3334", "PizzaRepository OrderPizza creating pizza");
        Log.d ("CIS 3334", newPizza.toString());
        PizzaDatabase.databaseWriteExecutor.execute(() -> {
            Log.d("CIS 3334", "PizzaRepository - OrderPizza:databaseWriteExecutor - starting up pizzaDao");
            pizzaDao.insert(newPizza);
            Log.d("CIS 3334", "PizzaRepository - OrderPizza:databaseWriteExecutor -Inserting pizza into database");
        });
    }

    public void ResetOrder(){
        Log.d("CIS 3334", "PizzaRepository ResetOrder creating pizza");
        PizzaDatabase.databaseWriteExecutor.execute(() -> {
            Log.d("CIS 3334", "PizzaRepository - ResetOrder:databaseWriteExecutor - starting up pizzaDao");
            pizzaDao.deleteAll();
            Log.d("CIS 3334", "PizzaRepository - ResetOrder:databaseWriteExecutor - Deleting pizza from database");
        });
    }

    public LiveData<List<Pizza>> getOrder() { //
        return allPizzas;
    }

}
