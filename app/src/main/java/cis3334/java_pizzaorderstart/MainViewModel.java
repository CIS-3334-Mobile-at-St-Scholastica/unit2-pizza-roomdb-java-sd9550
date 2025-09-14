package cis3334.java_pizzaorderstart;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    PizzaOrder myPizzaOrder = new PizzaOrder();
    PizzaRepository pizzaRepository;
    private LiveData<List<Pizza>> allPizzas;

    public MainViewModel(@NonNull Application application) {
        super(application);
        pizzaRepository = new PizzaRepository(application);
        allPizzas = pizzaRepository.getOrder();
    }
    public void placeOrder() {
        //orderStatus.postValue("Order Placed");
        //pizzaOrder.PlaceOrder();
        //startPizzaTimer();
    }
    public void placeOrder(String topping, Integer size) {
        Pizza newPizza = new Pizza(topping, size);
        pizzaRepository.OrderPizza(newPizza);
    }

    public void resetOrder() {
        pizzaRepository.ResetOrder();
    }

    public LiveData<List<Pizza>> getOrder() {
        return allPizzas;

    }


}