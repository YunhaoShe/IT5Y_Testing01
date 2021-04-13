package com.example.coffeeapp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayOrderDetails extends AppCompatActivity {
    String message;
    String name;
    String price;
    CoffeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
//        instastiate the coffeeDBHandler
        dbHandler = new CoffeeDBHandler(this, null, null, 1);

//        Get the Intent taht started this activity and extract the string
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        price = intent.getStringExtra("price");
        //Capture the layours Textview to display the message
        TextView textview = (TextView) findViewById(R.id.messageTextView);
        textview.setText(message);
    }

    //     to open Gmail and display the order details in it
    public void sendEmail(View view) {
        //deckare enauk addresses
        String[] addresses = {
                "john@coffeemaker.com",
                "manager@coffeemaker.com"
        };
        String subject = "Coffee Order";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
//        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
//        }
    }

    //    METHOD that will help to save the data in the database
    public void saveDate(View view) {
//    create an object of order
        Order order = new Order(name, Integer.parseInt(price));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(), "Hooray! Data Saved in DB", Toast.LENGTH_SHORT).show();
    }

    //    method that helps to generate a report in a new activety
    public void salesReport(View view) {
        String dbString = dbHandler.databaseToString();
//        start a new intent an ddisplay the output there
        Intent saleseIntent = new Intent(this, DisplaySalesDetails.class);
        saleseIntent.putExtra("db",dbString);
        startActivity(saleseIntent);
    }
}