package com.example.kiel.androidui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class Kiosk extends AppCompatActivity implements View.OnClickListener {
    private Button startTransaction;
    private EditText customerID;
    private ListView menuItems;
    private ListView totalItems;
    private Button addItem;
    private Button removeItem;
    private EditText paymentField;
    private Button payButton;
    private CheckBox deliveryCheckbox;
    private EditText addressField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_kiosk);

        findViews();
    }

    private void findViews() {
        startTransaction = (Button)findViewById( R.id.startTransaction );
        customerID = (EditText)findViewById( R.id.customerID );
        menuItems = (ListView)findViewById( R.id.menuItems );
        totalItems = (ListView)findViewById( R.id.totalItems );
        addItem = (Button)findViewById( R.id.addItem );
        removeItem = (Button)findViewById( R.id.removeItem );
        paymentField = (EditText)findViewById( R.id.paymentField );
        payButton = (Button)findViewById( R.id.payButton );
        deliveryCheckbox = (CheckBox)findViewById( R.id.deliveryCheckbox );
        addressField = (EditText)findViewById( R.id.addressField );

        startTransaction.setOnClickListener( this );
        addItem.setOnClickListener( this );
        removeItem.setOnClickListener( this );
        payButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == startTransaction ) {
            // Handle clicks for startTransaction
            System.out.println("startTransaction");
        } else if ( v == addItem ) {
            // Handle clicks for addItem
            System.out.println("addItem");
        } else if ( v == removeItem ) {
            // Handle clicks for removeItem
            System.out.println("removeItem");
        } else if ( v == payButton ) {
            // Handle clicks for payButton
            System.out.println("payButton");
        }
    }

}
