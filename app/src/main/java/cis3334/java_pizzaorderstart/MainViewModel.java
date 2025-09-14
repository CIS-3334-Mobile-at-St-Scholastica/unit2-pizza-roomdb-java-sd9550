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

    // Get the order as a string
//    public String getOrder() {
//        StringBuilder orderDescription = new StringBuilder();
//        List<String> pizzaList = myPizzaOrder.getOrder();
//        if (pizzaList != null) {
//            for (String strPizza:pizzaList ) {
//                orderDescription.append(strPizza).append("\n");
//            }
//        }
//
//        return orderDescription.toString();
//    }

    public LiveData<List<Pizza>> getOrder() {
        return allPizzas;

    }


}