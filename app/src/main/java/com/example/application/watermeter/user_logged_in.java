package com.example.application.watermeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class user_logged_in extends AppCompatActivity {

    private TextView user_info;
    private String username;
    private String password;
    private String admin;

    private Button user_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged_in);

        user_info = (TextView)findViewById(R.id.user_info);
        user_password = (Button)findViewById(R.id.user_password);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        String society = intent.getStringExtra("society");
        String username_password = intent.getStringExtra("username_password");
        String flat = intent.getStringExtra("flat");
        String cost = intent.getStringExtra("cost");
        String final_amount = intent.getStringExtra("final_amount");
        String reading0 = intent.getStringExtra("reading0");
        String reading1 = intent.getStringExtra("reading1");
        String reading2 = intent.getStringExtra("reading2");
        String reading3 = intent.getStringExtra("reading3");
        String reading4 = intent.getStringExtra("reading4");
        String reading5 = intent.getStringExtra("reading5");
        String reading6 = intent.getStringExtra("reading6");
        String reading7 = intent.getStringExtra("reading7");
        String reading8 = intent.getStringExtra("reading8");
        String reading9 = intent.getStringExtra("reading9");
        String reading10 = intent.getStringExtra("reading10");
        String reading11 = intent.getStringExtra("reading11");
        String reading12 = intent.getStringExtra("reading12");
        String date0 = intent.getStringExtra("date0");
        String date1 = intent.getStringExtra("date1");
        String date2 = intent.getStringExtra("date2");
        String date3 = intent.getStringExtra("date3");
        String date4 = intent.getStringExtra("date4");
        String date5 = intent.getStringExtra("date5");
        String date6 = intent.getStringExtra("date6");
        String date7 = intent.getStringExtra("date7");
        String date8 = intent.getStringExtra("date8");
        String date9 = intent.getStringExtra("date9");
        String date10 = intent.getStringExtra("date10");
        String date11 = intent.getStringExtra("date11");
        String date12 = intent.getStringExtra("date12");
        admin = intent.getStringExtra("admin");

        String info = "Hello " + username + ", \n\n" ;
        info = info + "Society Name :\t \t \t " + society + "\n\n";
        info = info + "Flat Number :\t \t \t " + flat + "\n\n";
        info = info + "Cost per unit :\t \t \t " + cost + "\n\n";
        info = info + "Date \t \t \t \t \t \t Reading \n\n";

        String date = "";
        String month = "";
        String year = "";

        int a = 0;

        if(!reading0.equals("0")){
            a++;
           info = info + convert(a,date0,reading0);
        }
        if(!reading1.equals("0")){
            a++;
            info = info + convert(a,date1,reading1);
        }
        if(!reading2.equals("0")){
            a++;
            info = info + convert(a,date2,reading2);
        }
        if(!reading3.equals("0")){
            a++;
            info = info + convert(a,date3,reading3);
        }
        if(!reading4.equals("0")){
            a++;
            info = info + convert(a,date4,reading4);
        }
        if(!reading5.equals("0")){
            a++;
            info = info + convert(a,date5,reading5);
        }
        if(!reading6.equals("0")){
            a++;
            info = info + convert(a,date6,reading6);
        }
        if(!reading7.equals("0")){
            a++;
            info = info + convert(a,date7,reading7);
        }
        if(!reading8.equals("0")){
            a++;
            info = info + convert(a,date8,reading8);
        }
        if(!reading9.equals("0")){
            a++;
            info = info + convert(a,date9,reading9);
        }
        if(!reading10.equals("0")){
            a++;
            info = info + convert(a,date10,reading10);
        }
        if(!reading11.equals("0")){
            a++;
            info = info + convert(a,date11,reading11);
        }
        if(!reading12.equals("0")){
            a++;
            info = info + convert(a,date12,reading12);
        }

        info = info + "Final Amount : \t \t \t" + final_amount + "\n\n";
        user_info.setText(info);
    }

    public String convert(int a, String day,String reading){
        String date = "" + day.charAt(0) + day.charAt(1);
        String month = "" + day.charAt(2) + day.charAt(3);
        String year = "" + day.charAt(4) + day.charAt(5) + day.charAt(6) + day.charAt(7);

        if(date.equals("01")){
            date = "1";
        }else if(date.equals("02")){
            date = "2";
        }else if(date.equals("03")){
            date = "3";
        }else if(date.equals("04")){
            date = "4";
        }else if(date.equals("05")){
            date = "5";
        }else if(date.equals("06")){
            date = "6";
        }else if(date.equals("07")){
            date = "7";
        }else if(date.equals("08")){
            date = "8";
        }else if(date.equals("09")){
            date = "9";
        }

        if(month.equals("01")){
            month = "January";
        }else if(month.equals("02")){
            month = "February";
        }else if(month.equals("03")){
            month = "March";
        }else if(month.equals("04")){
            month = "April";
        }else if(month.equals("05")){
            month = "May";
        }else if(month.equals("06")){
            month = "June";
        }else if(month.equals("07")){
            month = "July";
        }else if(month.equals("08")){
            month = "August";
        }else if(month.equals("09")){
            month = "September";
        }else if(month.equals("10")){
            month = "August";
        }else if(month.equals("11")){
            month = "November";
        }else if(month.equals("12")){
            month = "December";
        }

        String info = "";

        info = info + String.valueOf(a) + ") " + date + " " + month + " " + year + "\t \t \t" +reading + "\n\n";
        return info;
    }

    public void choosen_change_password(View view){
        Intent i = new Intent(getApplicationContext(), user_change_password.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        i.putExtra("admin",admin);
        startActivity(i);
        //startActivity(new Intent(this, user_change_password.class));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
