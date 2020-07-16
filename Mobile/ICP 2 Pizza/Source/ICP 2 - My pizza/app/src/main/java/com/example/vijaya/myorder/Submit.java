package com.example.vijaya.myorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Submit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        TextView nm = (TextView) findViewById(R.id.txtName);
        TextView sum = (TextView) findViewById(R.id.msg);
        final String summary = getIntent().getStringExtra("SUMMARY");
        final String Name = getIntent().getStringExtra("NAME");


        String subject = "Order details for: " + Name;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pizza51@gpizza.pi"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose Email Client"));

    }
}