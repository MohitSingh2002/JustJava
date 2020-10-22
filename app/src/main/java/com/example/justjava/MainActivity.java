package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.justjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean isCheck = false;
    boolean isCheckChocolate = false;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void submitOrder(View view) {
        if(isCheck == true || isCheckChocolate == true) {
            if(isCheck == true && isCheckChocolate == false) {
                displayPrice(quantity * 6);
            } else if(isCheck == false && isCheckChocolate == true) {
                displayPrice(quantity * 7);
            } else if(isCheck == true && isCheckChocolate == true) {
                displayPrice(quantity * 8);
            }
        } else {
            displayPrice(quantity * 5);
        }
    }

    public void display(int number) {
        binding.quantityTextView.setText("" + number);
    }

    public void displayPrice(int number) {
        String text = "Name: " + binding.nameEditText.getText() + "\nAdd Whipped Cream ? " + isCheck + "\nAdd Chocolate ? " + isCheckChocolate + "\nQuantity: " + quantity + "\n" + "Price: ₹" + number;
        sendEmail(String.valueOf(binding.nameEditText.getText()), text);
        binding.priceTextView.setText(text);
    }

    public void sendEmail(String sentBy, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order placed by " + sentBy);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if(quantity < 0) {
            Toast toast = Toast.makeText(this, "Quantity can not be less than zero", Toast.LENGTH_LONG);
            toast.show();
            quantity = 0;
        }
        display(quantity);
    }

    public void onCheckBoxClick(View view) {
        isCheck = !isCheck;
    }

    public void onCheckBoxClickChocolate(View view) {
        isCheckChocolate = !isCheckChocolate;
    }

}
