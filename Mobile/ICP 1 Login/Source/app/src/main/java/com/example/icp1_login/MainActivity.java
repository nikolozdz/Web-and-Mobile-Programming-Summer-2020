package com.example.icp1_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Username,Password;
    private TextView TriesLeft;
    private Button LogIn;
    private int countTries=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.etName);
        Password =(EditText) findViewById(R.id.etPassword);
        TriesLeft = (TextView) findViewById(R.id.tvinfo);
        LogIn = (Button) findViewById(R.id.btnLogin);

        TriesLeft.setText("3 attempts left");
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(),Password.getText().toString());
            }
        });

    }
    private void validate(String Uname, String Pass){
        if ((Uname.equals("Nikoloz")) && (Pass.equals("Password"))){
            Intent intent = new Intent(MainActivity.this, Success.class);
            startActivity(intent);
        }else{
            countTries--;

            if (countTries==0){
                TriesLeft.setText("Log In Disabled");
                LogIn.setEnabled(false);
            }else{
                TriesLeft.setText(countTries+" attempts left");
            }

        }
    }
}