package com.example.application.watermeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class add_user_data extends AppCompatActivity {

    private EditText add_user_data_username;
    private EditText add_user_data_reading;
    private Button add_data;
    private Spinner add_user_data_date;
    private Spinner add_user_data_month;
    private Spinner add_user_data_year;
    private ArrayAdapter<CharSequence> dates;
    private ArrayAdapter<CharSequence> months;
    private ArrayAdapter<CharSequence> years;

    FirebaseDatabase fbDatabase;
    DatabaseReference fbDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_data);

        add_user_data_username =(EditText)findViewById(R.id.add_user_data_username);
        add_user_data_reading = (EditText)findViewById(R.id.add_user_data_reading);
        add_data = (Button)findViewById(R.id.add_data);

        add_user_data_date = (Spinner)findViewById(R.id.add_user_data_date);
        dates = ArrayAdapter.createFromResource(this,R.array.Dates,android.R.layout.simple_spinner_item);
        dates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_user_data_date.setAdapter(dates);

        add_user_data_month = (Spinner)findViewById(R.id.add_user_data_month);
        months = ArrayAdapter.createFromResource(this,R.array.Months,android.R.layout.simple_spinner_item);
        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_user_data_month.setAdapter(months);

        add_user_data_year = (Spinner)findViewById(R.id.add_user_data_year);
        years = ArrayAdapter.createFromResource(this,R.array.Years,android.R.layout.simple_spinner_item);
        years.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_user_data_year.setAdapter(years);


        fbDatabase = FirebaseDatabase.getInstance();
        fbDatabaseReference = fbDatabase.getReference();

        add_data.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String username = add_user_data_username.getText().toString();
                String reading = add_user_data_reading.getText().toString();

                String month = add_user_data_month.getSelectedItem().toString();

                String date = add_user_data_date.getSelectedItem().toString();


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

                final String initial_date = date + month + add_user_data_year.getSelectedItem().toString();

                Calendar calendar = Calendar.getInstance();

                int thisYear = calendar.get(Calendar.YEAR);
                int thisMonth = calendar.get(Calendar.MONTH);
                int thisDate = calendar.get(Calendar.DAY_OF_MONTH);

                int given_date = add_user_data_date.getSelectedItemPosition() + 1;
                int given_month = add_user_data_month.getSelectedItemPosition();
                int given_year = add_user_data_year.getSelectedItemPosition() + 2017 - 1900;

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
                }

                if(username!=null && reading!=null){
                    fetchData(username,reading,initial_date);
                }else if(username==null){
                    Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_LONG).show();
                }else if(reading==null){
                    Toast.makeText(getApplicationContext(),"Enter water meter reading",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    public void fetchData(final String username, final String reading, final String date ){

        Intent intent = getIntent();

        String username1 = intent.getStringExtra("username");
        String password1 = intent.getStringExtra("password");
        String society1 = intent.getStringExtra("society");

        Query query = fbDatabaseReference
                .child("Admin").child(username1)
                .orderByChild("Username")
                .equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue()!=null) {

                    Log.d("dataSnapshot ", dataSnapshot.toString());

                    HashMap<String, Object> studentdata = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.d("dataSnapshot ", studentdata.toString());


                    for (String key : studentdata.keySet()) {

                        Object mObject = studentdata.get(key);
                        HashMap<String, Object> map = (HashMap<String, Object>) mObject;

                        String flat = map.get("Flat").toString();
                        String cost = map.get("Cost").toString();
                        String final_amount = map.get("Final Amount").toString();
                        String society = map.get("Society").toString();
                        String password = map.get("Password").toString();
                        String username_password = map.get("username_password").toString();

                        String reading0 = map.get("Reading0").toString();
                        String reading1 = map.get("Reading1").toString();
                        String reading2 = map.get("Reading2").toString();
                        String reading3 = map.get("Reading3").toString();
                        String reading4 = map.get("Reading4").toString();
                        String reading5 = map.get("Reading5").toString();
                        String reading6 = map.get("Reading6").toString();
                        String reading7 = map.get("Reading7").toString();
                        String reading8 = map.get("Reading8").toString();
                        String reading9 = map.get("Reading9").toString();
                        String reading10 = map.get("Reading10").toString();
                        String reading11 = map.get("Reading11").toString();
                        String reading12 = map.get("Reading12").toString();

                        //Toast.makeText(getApplicationContext(), reading0, Toast.LENGTH_LONG).show();

                        String date0 = map.get("Date0").toString();
                        String date1 = map.get("Date1").toString();
                        String date2 = map.get("Date2").toString();
                        String date3 = map.get("Date3").toString();
                        String date4 = map.get("Date4").toString();
                        String date5 = map.get("Date5").toString();
                        String date6 = map.get("Date6").toString();
                        String date7 = map.get("Date7").toString();
                        String date8 = map.get("Date8").toString();
                        String date9 = map.get("Date9").toString();
                        String date10 = map.get("Date10").toString();
                        String date11 = map.get("Date11").toString();
                        String date12 = map.get("Date12").toString();

                        Intent intent = getIntent();

                        String username1 = intent.getStringExtra("username");
                        String password1 = intent.getStringExtra("password");
                        String society1 = intent.getStringExtra("society");

                        String difference;

                        if(reading0.equals("0")) {
                            reading0 = reading;
                            date0 = date;
                            difference = "0";
                        }else if(reading1.equals("0")){
                            reading1 = reading;
                            date1 = date;
                            Float a = Float.parseFloat(reading0);
                            Float b = Float.parseFloat(reading1);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading2.equals("0")){
                            reading2 = reading;
                            date2 = date;
                            Float a = Float.parseFloat(reading1);
                            Float b = Float.parseFloat(reading2);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading3.equals("0")){
                            reading3 = reading;
                            date3 = date;
                            Float a = Float.parseFloat(reading2);
                            Float b = Float.parseFloat(reading3);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading4.equals("0")){
                            reading4 = reading;
                            date4 = date;
                            Float a = Float.parseFloat(reading3);
                            Float b = Float.parseFloat(reading4);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading5.equals("0")){
                            reading5 = reading;
                            date5 = date;
                            Float a = Float.parseFloat(reading4);
                            Float b = Float.parseFloat(reading5);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading6.equals("0")){
                            reading6 = reading;
                            date6 = date;
                            Float a = Float.parseFloat(reading5);
                            Float b = Float.parseFloat(reading6);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading7.equals("0")){
                            reading7 = reading;
                            date7 = date;
                            Float a = Float.parseFloat(reading6);
                            Float b = Float.parseFloat(reading7);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading8.equals("0")){
                            reading8 = reading;
                            date8 = date;
                            Float a = Float.parseFloat(reading7);
                            Float b = Float.parseFloat(reading8);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading9.equals("0")){
                            reading9 = reading;
                            date9 = date;
                            Float a = Float.parseFloat(reading8);
                            Float b = Float.parseFloat(reading9);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading10.equals("0")){
                            reading10 = reading;
                            date10 = date;
                            Float a = Float.parseFloat(reading9);
                            Float b = Float.parseFloat(reading10);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading11.equals("0")){
                            reading11 = reading;
                            date11 = date;
                            Float a = Float.parseFloat(reading10);
                            Float b = Float.parseFloat(reading11);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else if(reading12.equals("0")){
                            reading12 = reading;
                            date12 = date;
                            Float a = Float.parseFloat(reading11);
                            Float b = Float.parseFloat(reading12);
                            Float ans = b-a;
                            if(ans>0) {
                                difference = String.valueOf(ans);
                            }else{
                                difference = "0";
                            }
                        }else{
                            reading0 = reading1;
                            reading1 = reading2;
                            reading2 = reading3;
                            reading3 = reading4;
                            reading4 = reading5;
                            reading5 = reading6;
                            reading6 = reading7;
                            reading7 = reading8;
                            reading8 = reading9;
                            reading9 = reading10;
                            reading10 = reading11;
                            reading11 = reading12;
                            reading12 = reading;
                            date0 = date1;
                            date1 = date2;
                            date2 = date3;
                            date3 = date4;
                            date4 = date5;
                            date5 = date6;
                            date6 = date7;
                            date7 = date8;
                            date8 = date9;
                            date9 = date10;
                            date10 = date11;
                            date11 = date12;
                            date12 = date;
                            Float a = Float.parseFloat(reading11);
                            Float b = Float.parseFloat(reading12);
                            Float ans = b-a;
                            difference = String.valueOf(ans);
                        }

                        Float float_final_amount = Float.parseFloat(final_amount);
                        Float c = Float.parseFloat(difference);
                        Float float_cost = Float.parseFloat(cost);
                        Float amount = float_cost*c;
                        float_final_amount = amount + float_final_amount;

                        final_amount = String.valueOf(float_final_amount);

                        HashMap<String, Object> userData = new HashMap<String, Object>();

                        userData.put("Username", username);
                        userData.put("Flat", flat);
                        userData.put("Reading0", reading0);
                        userData.put("Reading1", reading1);
                        userData.put("Reading2", reading2);
                        userData.put("Reading3", reading3);
                        userData.put("Reading4", reading4);
                        userData.put("Reading5", reading5);
                        userData.put("Reading6", reading6);
                        userData.put("Reading7", reading7);
                        userData.put("Reading8", reading8);
                        userData.put("Reading9", reading9);
                        userData.put("Reading10", reading10);
                        userData.put("Reading11", reading11);
                        userData.put("Reading12", reading12);
                        userData.put("Date0",date0);
                        userData.put("Date1",date1);
                        userData.put("Date2",date2);
                        userData.put("Date3",date3);
                        userData.put("Date4",date4);
                        userData.put("Date5",date5);
                        userData.put("Date6",date6);
                        userData.put("Date7",date7);
                        userData.put("Date8",date8);
                        userData.put("Date9",date9);
                        userData.put("Date10",date10);
                        userData.put("Date11",date11);
                        userData.put("Date12",date12);
                        userData.put("username_password",username_password);
                        userData.put("Password",password);

                        userData.put("Society",society);
                        userData.put("Cost",cost);
                        userData.put("Final Amount",final_amount);
                        //fbDatabaseReference.child(username1).orderByChild("Username").equalTo(username).removeValue();

                        fbDatabaseReference.child("Admin").child(username1).push().updateChildren(userData);


                        for(DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                            areaSnapshot.getRef().setValue(null);
                        }


                        //dataSnapshot.getRef().child(username1).orderByChild("Username").equalTo(username).removeValue();


                        //Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), admin_logged_in.class);
                        i.putExtra("username", username1);
                        i.putExtra("password", password1);
                        i.putExtra("society",society1);
                        startActivity(i);
                        finish();
                        break;

                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Not a valid username", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d( "error ",databaseError.toString());
            }
        });
    }
}
