package com.example.customer_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private view MyCustomerView;
    private Button MySwapColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCustomerView = (view) findViewById(R.id.view);
        MySwapColor = findViewById(R.id.swap_color);
        MySwapColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCustomerView.SwapColor();
            }
        });
    }

}