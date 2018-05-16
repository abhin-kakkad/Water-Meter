package com.example.application.watermeter;

import android.content.Intent;
import android.sax.EndElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EnterSocietyDetails extends AppCompatActivity {

    private EditText cost;
    private EditText discount;
    private Button done;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_society_details);

        cost = (EditText)findViewById(R.id.editText);
        discount = (EditText)findViewById(R.id.editText2);
        done = (Button)findViewById(R.id.done);

        Intent intent = getIntent();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");
        final String society = intent.getStringExtra("society");
        //final String costs = intent.getStringExtra("cost");
        final String y = intent.getStringExtra("username_password");
        //final String discounts = intent.getStringExtra("discount");
        final String method = intent.getStringExtra("method");

        if(method.equals("Method 1")){
            discount.setText("0");
            discount.setFocusable(false);
            discount.setEnabled(false);
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String costs = cost.getText().toString().trim();
                final String discounts = discount.getText().toString().trim();

                if(TextUtils.isEmpty(costs)){
                    Toast.makeText(EnterSocietyDetails.this,"Please enter cost per unit",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(discounts)){
                    Toast.makeText(EnterSocietyDetails.this,"Please enter maximum amount till which service is free of cost",Toast.LENGTH_SHORT).show();
                    return;
                }

                Query q = mDatabase
                        .child("Admin").child(username)
                        .orderByChild("username_password").equalTo(y);

                q.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getValue()!=null) {

                            Log.d("dataSnapshot ", dataSnapshot.toString());

                            HashMap<String, Object> studentdata = (HashMap<String, Object>) dataSnapshot.getValue();
                            Log.d("dataSnapshot ", studentdata.toString());

                            for (String key : studentdata.keySet()) {
                                HashMap<String, Object> userData = new HashMap<String, Object>();

                                userData.put("Username", username);
                                userData.put("Password", password);
                                userData.put("Society", society);
                                userData.put("username_password", y);
                                if(method.equals("Method 1")){
                                    userData.put("Method",String.valueOf(1));
                                }else{
                                    userData.put("Method",String.valueOf(2));
                                }
                                userData.put("Cost",costs);
                                userData.put("Discount",discounts);

                                mDatabase.child("Admin").child(username).push().updateChildren(userData);

                                for(DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                                    areaSnapshot.getRef().setValue(null);
                                }

                                Toast.makeText(getApplicationContext(), "Admin added", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), admin_login.class));
                               finish();

                            }

                        }else{

                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }

    });}
}
