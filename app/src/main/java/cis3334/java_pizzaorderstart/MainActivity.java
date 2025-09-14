// Stefan D. CIS 3334
// Unit 2
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
    TextView textViewPlaceOrder;
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
        textViewPlaceOrder = findViewById(R.id.textViewPlaceOrder);
        buttonPlaceOrder.setVisibility(View.INVISIBLE);

        mainViewModel.getOrder().observe(this, pizzas -> {
            Log.d("CIS 3334", "LiveData observer fired.");

            StringBuilder orderDescription = new StringBuilder();
            if (isInitialLoad) {
                for (Pizza p : pizzas) {
                    orderDescription.append(p.toString()).append("\n");
                }
                textOrder.setText(orderDescription.toString());

                isInitialLoad = false; // initial load
                Log.d("CIS 3334", "Initial data loaded.");

            } else {
                // subsequent update
                for (Pizza p : pizzas) {
                    orderDescription.append(p.toString()).append("\n");
                }
                textOrder.setText(orderDescription.toString());
                Log.d("CIS 3334", "Order updated.");
            }
        });

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

        buttonPlaceOrder.setOnClickListener(v -> {
            Log.d("CIS 3334", "Place order button clicked");   // log button click for debugging
            mainViewModel.resetOrder();
            textViewPlaceOrder.setText(String.format("%s", "Order Placed!"));
            buttonPlaceOrder.setVisibility(View.INVISIBLE);
        });

        buttonAddToOrder.setOnClickListener(v -> {
            textViewPlaceOrder.setText(String.format("%s", ""));
            mainViewModel.placeOrder(getToppings(),pizzaSize);
            Log.d("CIS 3334", "Add To Order button clicked");   // log button click for debugging
            buttonPlaceOrder.setVisibility(View.VISIBLE);
        });

    }

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