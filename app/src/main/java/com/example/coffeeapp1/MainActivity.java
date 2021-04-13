package com.example.coffeeapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int noOfcoffee = 0;
    int priceOfCoffee = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method to submitOrder
    public void submitOrder(View view){
//        display(noOfcoffee);
//        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        int totalPrice = noOfcoffee*priceOfCoffee;
//        priceTextView.setText("Total:$"+totalPrice+"\n"+"Thank you for visiting us!!");


//      01  Get the user name
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();

//        02 Figure out if user need whipped cream
        CheckBox wcCheckBox = (CheckBox)findViewById(R.id.whippedChekBox);
        boolean hasWhippedCream = wcCheckBox.isChecked();

//        03 figure out if user needs chocolate
        CheckBox cCheckBox = (CheckBox)findViewById(R.id.chocolateChekBox);
        boolean hasChocolate = cCheckBox.isChecked();
//       04 calculate the price
           int price = calculatePrice(hasWhippedCream,hasChocolate);

//        05 create a summary of the order
        String priceMessage = "Name:"+name+"\n"+
                "Add whipped Cream?"+hasWhippedCream+"\n"+
                "AddChocolate?"+hasChocolate+"\n"+
                "Quantitiy:"+noOfcoffee+"\n"+
                "Total:$"+price+"\n"+
                "Thank you!";

//        06 display the order deails
        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        priceTextView.setText(priceMessage);

//        create a new inten
        Intent intent = new Intent(this,DisplayOrderDetails.class);
        intent.putExtra("name",name);
        intent.putExtra("message",priceMessage);
        intent.putExtra("price",Integer.toString(price));
        startActivity(intent);
    }
//    method that calculates the price
    public int calculatePrice(boolean addWhippedCream,boolean addChocoloate){
        int basePrice = 4;
//        if user wants whipped cream - add $1
        if (addWhippedCream)
            basePrice = basePrice+1;
//          if user wants chocolate cream - add $1
        if (addChocoloate)
            basePrice = basePrice+2;
//        calculate the total price
         int totalPrice = basePrice * noOfcoffee;
        return  totalPrice;
    }

        //create the display() method
    public void display(int number){
        TextView quantityTextView = (TextView)findViewById(R.id.quantity_text_view);
        quantityTextView.setText((""+number));

    }
    //code to increase coffee by 1
    public void increment(View view){
        noOfcoffee = noOfcoffee + 1;
        if (noOfcoffee >= 10){
            noOfcoffee = 10;
        }
        display(noOfcoffee);
    }

    //code for decreasing

    public  void decrement(View view){
        noOfcoffee = noOfcoffee-1;
        if (noOfcoffee <=0){
            noOfcoffee = 0;
        }
        display(noOfcoffee);

    }

}