package com.example.vijaya.myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Order_Summary extends AppCompatActivity {

    private Button Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary);

        TextView nm = (TextView)findViewById(R.id.txtName);
        TextView sum = (TextView)findViewById(R.id.msg);
        final String summary = getIntent().getStringExtra("SUMMARY");
        final String Name = getIntent().getStringExtra("NAME");
        nm.setText("Dear, "+Name);
        sum.setText(summary);

        Order = (Button) findViewById(R.id.btnOrder);

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order_Summary.this, Submit.class);

                intent.putExtra("NAME",Name);
                intent.putExtra("SUMMARY",summary);
                startActivity(intent);
            }
        });

    }

}