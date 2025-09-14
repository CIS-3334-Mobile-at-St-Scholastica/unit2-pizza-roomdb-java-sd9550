package cis3334.java_pizzaorderstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarSize;
    Button buttonAddToOrder;
    Button buttonPlaceOrder;
    Chip chipPepperoni, chipChicken, chipGreenPepper;
    TextView textViewSize;
    EditText textOrder;
    Integer pizzaSize = 1;     // Pizza sizes are 0=Small, 1=Medium, 2=Large, 3=X-large
    private boolean isInitialLoad = true;
    final String[] PIZZA_SIZES = {"Small","Medium","Large","X-Large"};
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        seekBarSize = findViewById(R.id.seekBarSize);
        buttonAddToOrder = findViewById(R.id.buttonAddToOrder);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
        textViewSize = findViewById(R.id.textViewSize);
        textOrder = findViewById(R.id.editTextOrder);
        chipPepperoni = findViewById(R.id.chipPepperoni);
        chipChicken = findViewById(R.id.chipChicken);
        chipGreenPepper = findViewById(R.id.chipGreenPeppers);

        mainViewModel.getOrder().observe(this, pizzas -> {
            Log.d("CIS 3334", "LiveData observer fired.");

            if (isInitialLoad) {
                // This is the first time the data is loaded.
                // Just update the text and flip the flag.
                StringBuilder orderDescription = new StringBuilder();
                for (Pizza p : pizzas) {
                    orderDescription.append(p.toString()).append("\n");
                }
                textOrder.setText(orderDescription.toString());

                isInitialLoad = false; // <-- The initial load is now complete.
                Log.d("CIS 3334", "Initial data loaded.");

            } else {
                // This is a subsequent update (e.g., a new pizza was added).
                // You can add special actions here if you want.
                StringBuilder orderDescription = new StringBuilder();
                for (Pizza p : pizzas) {
                    orderDescription.append(p.toString()).append("\n");
                }
                textOrder.setText(orderDescription.toString());

                // Example: Show a toast only for new updates
                // Toast.makeText(MainActivity.this, "Your order has been updated!", Toast.LENGTH_SHORT).show();
                Log.d("CIS 3334", "Order updated.");
            }
        });

        /***
         *   Handle SeekBar changes
         */
        seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pizzaSize = progress;      // progress will be seekBar position of 0, 1, 2, or 3
                textViewSize.setText(PIZZA_SIZES[pizzaSize]);
            }
            // Required for SeekBar, but not needed in this program
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {    }
            // Required for SeekBar, but not needed in this program
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {     }
        });

        /***
         *   Handle Place Order button clicks
         */
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CIS 3334", "Place order button clicked");   // log button click for debugging using "CIS 3334" tag
                mainViewModel.placeOrder();
            }
        });

        /***
         *   Handle Add To Order button clicks
         */
        buttonAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.placeOrder(getToppings(),pizzaSize);
                //String order = mainViewModel.getOrder();
                //textOrder.setText(order);
                Log.d("CIS 3334", "Add To Order button clicked");   // log button click for debugging using "CIS 3334" tag
            }
        });

    }

    /***
     *   Check what topping chips have been checked and create a string containing them
     */
    private String getToppings() {
        String toppings = "";
        if (chipChicken.isChecked()) {
            toppings += chipChicken.getText() +" - ";
        }
        if (chipGreenPepper.isChecked()) {
            toppings += chipGreenPepper.getText() +" - ";
        }
        if (chipPepperoni.isChecked()) {
            toppings += chipPepperoni.getText() +" - ";
        }
        return toppings;
    }
}