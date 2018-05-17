package com.example.application.watermeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class admin_logged_in extends AppCompatActivity {

    private TextView admin_username;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logged_in);

        change = (Button)findViewById(R.id.change);

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String society = intent.getStringExtra("society");
        String cost = intent.getStringExtra("cost");

<<<<<<< HEAD
        //admin_username = (TextView) findViewById(R.id.admin_username);
        //admin_username.setText("Hi " + username + " , \n Welcome to your dashboard\n");
=======
        admin_username = (TextView) findViewById(R.id.admin_username);
        admin_username.setText("Hi " + username + " , \n Welcome to your dashboard\n");

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
>>>>>>> master
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    public void choosen_add_user(View view) {

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String society = intent.getStringExtra("society");
        String cost = intent.getStringExtra("cost");

        Intent i = new Intent(getApplicationContext(), user_signup.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        i.putExtra("society",society);
        i.putExtra("cost",cost);
        startActivity(i);
        finish();
    }

    public void choosen_add_user_data(View view){

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String society = intent.getStringExtra("society");

        Intent i = new Intent(getApplicationContext(), add_user_data.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        i.putExtra("society",society);
        startActivity(i);
        finish();
    }
}

