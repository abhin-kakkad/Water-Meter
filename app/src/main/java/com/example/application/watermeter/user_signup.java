package com.example.application.watermeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class user_signup extends AppCompatActivity {

    private EditText user_flat;
    private EditText user_initial_reading;
    //private EditText user_cost;
    private EditText user_signup_mobile_number;
    private Button user_signed_up;
//    private Spinner user_signup_date;
//    private Spinner user_signup_month;
//    private Spinner user_signup_year;
//    private ArrayAdapter<CharSequence> dates;
//    private ArrayAdapter<CharSequence> months;
//    private ArrayAdapter<CharSequence> years;
    private TextView txtView;

    private DatabaseReference mDatabase;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        txtView = (TextView) findViewById(R.id.date);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c.getTime());

        txtView.setText(formattedDate);

        user_flat = (EditText)findViewById(R.id.user_flat);
        user_initial_reading = (EditText)findViewById(R.id.user_initial_reading);
        user_signed_up = (Button)findViewById(R.id.user_signed_up);
        //user_cost = (EditText)findViewById(R.id.user_cost);
        user_signup_mobile_number = (EditText)findViewById(R.id.user_signup_mobile_number);

//        user_signup_date = (Spinner)findViewById(R.id.user_signup_date);
//        dates = ArrayAdapter.createFromResource(this,R.array.Dates,android.R.layout.simple_spinner_item);
//        dates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        user_signup_date.setAdapter(dates);
//
//        user_signup_month = (Spinner)findViewById(R.id.user_signup_month);
//        months = ArrayAdapter.createFromResource(this,R.array.Months,android.R.layout.simple_spinner_item);
//        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        user_signup_month.setAdapter(months);
//
//        user_signup_year = (Spinner)findViewById(R.id.user_signup_year);
//        years = ArrayAdapter.createFromResource(this,R.array.Years,android.R.layout.simple_spinner_item);
//        years.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        user_signup_year.setAdapter(years);

        /*final String[] date = {""};

        user_signup_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),adapterView.getItemIdAtPosition(i+1) + " is selected",Toast.LENGTH_LONG).show();
                date[0] = date[0] + adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                date[0] = "1";

            }
        });

        user_signup_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),adapterView.getItemIdAtPosition(i+1) + " is selected",Toast.LENGTH_LONG).show();
                date[1] = date[1] + adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                date[1] = "January";
            }
        });

        user_signup_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),adapterView.getItemIdAtPosition(i+1) + " is selected",Toast.LENGTH_LONG).show();
                date[2] = date[2] + adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                date[2] = "2017";
            }
        });*/

        mDatabase = FirebaseDatabase.getInstance().getReference();

        user_signed_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = user_flat.getText().toString().trim();
                /*final String flat = user_flat.getText().toString().trim();
                final String initial_reading = user_initial_reading.getText().toString().trim();
                final String cost = user_cost.getText().toString().trim();
                final String password = user_signup_password.getText().toString().trim();
*/
                if(TextUtils.isEmpty(username)) {
                    Toast.makeText(user_signup.this,"Please enter your username",Toast.LENGTH_SHORT).show();
                    return;
                }

                /*if(TextUtils.isEmpty(password)) {
                    Toast.makeText(user_signup.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(flat)){
                    Toast.makeText(user_signup.this,"Please enter your flat",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(initial_reading)){
                    Toast.makeText(user_signup.this,"Please enter your initial water meter reading",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(cost)){
                    Toast.makeText(user_signup.this,"Please enter cost for one month",Toast.LENGTH_SHORT).show();
                    return;
                }*/

                /*final String[] date = {""};

                user_signup_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getApplicationContext(),adapterView.getItemIdAtPosition(i+1) + " is selected",Toast.LENGTH_LONG).show();
                        date[0] = date[0] + adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                user_signup_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getApplicationContext(),adapterView.getItemIdAtPosition(i+1) + " is selected",Toast.LENGTH_LONG).show();
                        date[1] = date[1] + adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                user_signup_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getApplicationContext(),adapterView.getItemIdAtPosition(i+1) + " is selected",Toast.LENGTH_LONG).show();
                        date[2] = date[2] + adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/

                Intent intent = getIntent();

                final String username1 = intent.getStringExtra("username");
                final String password1 = intent.getStringExtra("password");
                final String society1 = intent.getStringExtra("society");
                final String cost = intent.getStringExtra("cost");

                Query query = mDatabase
                        .child("Admin").child(username1)
                        .orderByChild("username")
                        .equalTo(username);

                /*String month = user_signup_month.getSelectedItem().toString();

                String date = user_signup_date.getSelectedItem().toString();


                if(date.equals("1")){
                    date = "01";
                }else if(date.equals("2")){
                    date = "02";
                }else if(date.equals("3")){
                    date = "03";
                }else if(date.equals("4")){
                    date = "04";
                }else if(date.equals("5")){
                    date = "05";
                }else if(date.equals("6")){
                    date = "06";
                }else if(date.equals("7")){
                    date = "07";
                }else if(date.equals("8")){
                    date = "08";
                }else if(date.equals("9")){
                    date = "09";
                }

                if(month.equals("January")){
                    month = "01";
                }else if(month.equals("February")){
                    if(date.equals("29") || date.equals("30") || date.equals("31")){
                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                        return ;
                    }
                    month = "02";
                }else if(month.equals("March")){
                    month = "03";
                }else if(month.equals("April")){
                    if(date.equals("31")){
                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                        return ;
                    }
                    month = "04";
                }else if(month.equals("May")){
                    month = "05";
                }else if(month.equals("June")){
                    if(date.equals("31")){
                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                        return ;
                    }
                    month = "06";
                }else if(month.equals("July")){
                    month = "07";
                }else if(month.equals("August")){
                    month = "08";
                }else if(month.equals("September")){
                    if(date.equals("31")){
                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                        return ;
                    }
                    month = "09";
                }else if(month.equals("October")){
                    month = "10";
                }else if(month.equals("November")){
                    if(date.equals("31")){
                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                        return ;
                    }
                    month = "11";
                }else if(month.equals("December")) {
                    month = "12";
                }

                final String initial_date = date + month + user_signup_year.getSelectedItem().toString();


                Calendar calendar = Calendar.getInstance();

                int thisYear = calendar.get(Calendar.YEAR);
                int thisMonth = calendar.get(Calendar.MONTH);
                int thisDate = calendar.get(Calendar.DAY_OF_MONTH);

                int given_date = user_signup_date.getSelectedItemPosition() + 1;
                int given_month = user_signup_month.getSelectedItemPosition();
                int given_year = user_signup_year.getSelectedItemPosition() + 2017;

                //Toast.makeText(getApplicationContext(),thisYear + " " + given_year,Toast.LENGTH_LONG).show();

                if(given_year == thisYear){
                    if(given_month > thisMonth){
                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                        return ;
                    }else if(given_month == thisMonth){
                        if(given_date > thisDate){
                            Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
                            return ;
                        }
                    }
                }*/

                query.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final String flat = user_flat.getText().toString().trim();
                        final String initial_reading = user_initial_reading.getText().toString().trim();
                        //final String cost = user_cost.getText().toString().trim();
                        final String password = user_signup_mobile_number.getText().toString().trim();

                        if(dataSnapshot.getValue()!=null) {
                            Toast.makeText(getApplicationContext(),"Username already taken",Toast.LENGTH_LONG).show();
                            return;
                        }else{

                            if(TextUtils.isEmpty(password)) {
                                Toast.makeText(user_signup.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if(TextUtils.isEmpty(flat)){
                                Toast.makeText(user_signup.this,"Please enter your flat",Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if(TextUtils.isEmpty(initial_reading)){
                                Toast.makeText(user_signup.this,"Please enter your initial water meter reading",Toast.LENGTH_SHORT).show();
                                return;
                            }

//                            if(TextUtils.isEmpty(cost)){
//                                Toast.makeText(user_signup.this,"Please enter cost for one month",Toast.LENGTH_SHORT).show();
//                                return;
//                            }

                            String year = "";
                            year = year+ formattedDate.charAt(0) + formattedDate.charAt(1) + formattedDate.charAt(2) + formattedDate.charAt(3);//user_signup_month.getSelectedItem().toString();

                            String date = "";//user_signup_date.getSelectedItem().toString();
                            date = date + formattedDate.charAt(8) + formattedDate.charAt(9);


                            String month = "";
                            month = month + formattedDate.charAt(5)+formattedDate.charAt(6);

                            String initial_date = date + month +year;


//                            if(date.equals("1")){
//                                date = "01";
//                            }else if(date.equals("2")){
//                                date = "02";
//                            }else if(date.equals("3")){
//                                date = "03";
//                            }else if(date.equals("4")){
//                                date = "04";
//                            }else if(date.equals("5")){
//                                date = "05";
//                            }else if(date.equals("6")){
//                                date = "06";
//                            }else if(date.equals("7")){
//                                date = "07";
//                            }else if(date.equals("8")){
//                                date = "08";
//                            }else if(date.equals("9")){
//                                date = "09";
//                            }
//
//                            if(month.equals("January")){
//                                month = "01";
//                            }else if(month.equals("February")){
//                                if(date.equals("29") || date.equals("30") || date.equals("31")){
//                                    Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                    return ;
//                                }
//                                month = "02";
//                            }else if(month.equals("March")){
//                                month = "03";
//                            }else if(month.equals("April")){
//                                if(date.equals("31")){
//                                    Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                    return ;
//                                }
//                                month = "04";
//                            }else if(month.equals("May")){
//                                month = "05";
//                            }else if(month.equals("June")){
//                                if(date.equals("31")){
//                                    Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                    return ;
//                                }
//                                month = "06";
//                            }else if(month.equals("July")){
//                                month = "07";
//                            }else if(month.equals("August")){
//                                month = "08";
//                            }else if(month.equals("September")){
//                                if(date.equals("31")){
//                                    Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                    return ;
//                                }
//                                month = "09";
//                            }else if(month.equals("October")){
//                                month = "10";
//                            }else if(month.equals("November")){
//                                if(date.equals("31")){
//                                    Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                    return ;
//                                }
//                                month = "11";
//                            }else if(month.equals("December")) {
//                                month = "12";
//                            }
//
//                            final String initial_date = date + month + user_signup_year.getSelectedItem().toString();
//
//
//                            Calendar calendar = Calendar.getInstance();
//
//                            int thisYear = calendar.get(Calendar.YEAR);
//                            int thisMonth = calendar.get(Calendar.MONTH);
//                            int thisDate = calendar.get(Calendar.DAY_OF_MONTH);
//
//                            int given_date = user_signup_date.getSelectedItemPosition() + 1;
//                            int given_month = user_signup_month.getSelectedItemPosition();
//                            int given_year = user_signup_year.getSelectedItemPosition() + 2017;
//
//                            //Toast.makeText(getApplicationContext(),thisYear + " " + given_year,Toast.LENGTH_LONG).show();
//
//                            if(given_year == thisYear){
//                                if(given_month > thisMonth){
//                                    Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                    return ;
//                                }else if(given_month == thisMonth){
//                                    if(given_date > thisDate){
//                                        Toast.makeText(getApplicationContext(),"Invalid Date Entered",Toast.LENGTH_LONG).show();
//                                        return ;
//                                    }
//                                }
//                            }

                            HashMap<String, String> userData = new HashMap<String, String>();

                            final String y = username + "_" + password;

                            userData.put("Username", username);
                            userData.put("Flat", flat);
                            userData.put("Password",password);
                            userData.put("Mobile Number",password);
                            userData.put("username_password",y);
                            userData.put("Reading0", initial_reading);
                            userData.put("Reading1", "0");
                            userData.put("Reading2", "0");
                            userData.put("Reading3", "0");
                            userData.put("Reading4", "0");
                            userData.put("Reading5", "0");
                            userData.put("Reading6", "0");
                            userData.put("Reading7", "0");
                            userData.put("Reading8", "0");
                            userData.put("Reading9", "0");
                            userData.put("Reading10", "0");
                            userData.put("Reading11", "0");
                            userData.put("Reading12", "0");
                            userData.put("Date0",initial_date);
                            userData.put("Date1","0");
                            userData.put("Date2","0");
                            userData.put("Date3","0");
                            userData.put("Date4","0");
                            userData.put("Date5","0");
                            userData.put("Date6","0");
                            userData.put("Date7","0");
                            userData.put("Date8","0");
                            userData.put("Date9","0");
                            userData.put("Date10","0");
                            userData.put("Date11","0");
                            userData.put("Date12","0");

                            //Log.d("hello","how");
                            Intent i = new Intent(getApplicationContext(), admin_logged_in.class);
                            i.putExtra("username", username1);
                            i.putExtra("password", password1);
                            i.putExtra("society",society1);

                            userData.put("Society",society1);
                            userData.put("Cost",cost);
                            userData.put("Final Amount","0");




                            mDatabase.child("Admin").child(username1.toString()).push().setValue(userData);

            //              mDatabase.child("Hello").setValue(userData);


                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                //Log.d("hello1","how1");

            }
        });

    }
}
