package com.example.prayag.forecastsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class details extends AppCompatActivity {

    JSONObject data1=results.weatherData;
    String urlParams,city,state,degree,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    String t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24;
    String tempe1,tempe2,tempe3,tempe4,tempe5,tempe6,tempe7,tempe8,tempe9,tempe10,tempe11,tempe12;
    String tempe13,tempe14,tempe15,tempe16,tempe17,tempe18,tempe19,tempe20,tempe21,tempe22,tempe23,tempe24;
    private TextView current,time1,temperature1;
    private TextView time2,time3,time4,time5,time6,time7,time8,time9,time10,time11,time12;
    private TextView time13,time14,time15,time16,time17,time18,time19,time20,time21,time22,time23,time24;
    private TextView temperature2,temperature3,temperature4,temperature5,temperature6,temperature7,temperature8,temperature9,temperature10,temperature11,temperature12;
    private TextView temperature13,temperature14,temperature15,temperature16,temperature17,temperature18,temperature19,temperature20,temperature21,temperature22,temperature23,temperature24;
    Button b,days,weeks;
    ScrollView s1,s2;
    /* variables for next 7 days */
    String d1,d2,d3,d4,d5,d6,d7,tmi1,tmi2,tmi3,tmi4,tmi5,tmi6,tmi7,tma1,tma2,tma3,tma4,tma5,tma6,tma7;
    private TextView divas1,divas2,divas3,divas4,divas5,divas6,divas7;
    private TextView oops1,oops2,oops3,oops4,oops5,oops6,oops7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        addListernerOnButton();

        s1 = (ScrollView) findViewById(R.id.scrollView2);
        s2 = (ScrollView) findViewById(R.id.scrollView3);

        current = (TextView) findViewById(R.id.textView20);
        time1 = (TextView) findViewById(R.id.t1);
        temperature1 = (TextView) findViewById(R.id.temp1);
        time2 = (TextView) findViewById(R.id.t2);
        temperature2 = (TextView) findViewById(R.id.temp2);
        time3 = (TextView) findViewById(R.id.t3);
        temperature3 = (TextView) findViewById(R.id.temp3);
        time4 = (TextView) findViewById(R.id.t4);
        temperature4 = (TextView) findViewById(R.id.temp4);
        time5 = (TextView) findViewById(R.id.t5);
        temperature5 = (TextView) findViewById(R.id.temp5);
        time6 = (TextView) findViewById(R.id.t6);
        temperature6 = (TextView) findViewById(R.id.temp6);
        time7 = (TextView) findViewById(R.id.t7);
        temperature7 = (TextView) findViewById(R.id.temp7);
        time8 = (TextView) findViewById(R.id.t8);
        temperature8 = (TextView) findViewById(R.id.temp8);
        time9 = (TextView) findViewById(R.id.t9);
        temperature9 = (TextView) findViewById(R.id.temp9);
        time10 = (TextView) findViewById(R.id.t10);
        temperature10 = (TextView) findViewById(R.id.temp10);
        time11 = (TextView) findViewById(R.id.t11);
        temperature11 = (TextView) findViewById(R.id.temp11);
        time12 = (TextView) findViewById(R.id.t12);
        temperature12 = (TextView) findViewById(R.id.temp12);
        time13 = (TextView) findViewById(R.id.t13);
        temperature13 = (TextView) findViewById(R.id.temp13);
        time14 = (TextView) findViewById(R.id.t14);
        temperature14 = (TextView) findViewById(R.id.temp14);
        time15 = (TextView) findViewById(R.id.t15);
        temperature15 = (TextView) findViewById(R.id.temp15);
        time16 = (TextView) findViewById(R.id.t16);
        temperature16 = (TextView) findViewById(R.id.temp16);
        time17 = (TextView) findViewById(R.id.t17);
        temperature17 = (TextView) findViewById(R.id.temp17);
        time18 = (TextView) findViewById(R.id.t18);
        temperature18 = (TextView) findViewById(R.id.temp18);
        time19 = (TextView) findViewById(R.id.t19);
        temperature19 = (TextView) findViewById(R.id.temp19);
        time20 = (TextView) findViewById(R.id.t20);
        temperature20 = (TextView) findViewById(R.id.temp20);
        time21 = (TextView) findViewById(R.id.t21);
        temperature21 = (TextView) findViewById(R.id.temp21);
        time22 = (TextView) findViewById(R.id.t22);
        temperature22 = (TextView) findViewById(R.id.temp22);
        time23 = (TextView) findViewById(R.id.t23);
        temperature23 = (TextView) findViewById(R.id.temp23);
        time24 = (TextView) findViewById(R.id.t24);
        temperature24 = (TextView) findViewById(R.id.temp24);


        /* Next 7 days */
        divas1 = (TextView) findViewById(R.id.day1);
        oops1 = (TextView) findViewById(R.id.tempo1);

        divas2 = (TextView) findViewById(R.id.day2);
        oops2 = (TextView) findViewById(R.id.tempo2);

        divas3 = (TextView) findViewById(R.id.day3);
        oops3 = (TextView) findViewById(R.id.tempo3);

        divas4 = (TextView) findViewById(R.id.day4);
        oops4 = (TextView) findViewById(R.id.tempo4);

        divas5 = (TextView) findViewById(R.id.day5);
        oops5 = (TextView) findViewById(R.id.tempo5);

        divas6 = (TextView) findViewById(R.id.day6);
        oops6 = (TextView) findViewById(R.id.tempo6);

        divas7 = (TextView) findViewById(R.id.day7);
        oops7 = (TextView) findViewById(R.id.tempo7);

        try {
            JSONObject daily1 = data1.getJSONObject("daily");
            JSONObject hourly = data1.getJSONObject("hourly");
            JSONArray data2 = daily1.getJSONArray("data");
            JSONArray data3 = hourly.getJSONArray("data");

            current.setText("More Details for " + city + " , " + state);
            t1 = hourly.getJSONArray("data").getJSONObject(0).getString("time");
            long tindi1 = Long.parseLong(t1) * 1000;
            time1.setText(get1(tindi1));
            tempe1 = hourly.getJSONArray("data").getJSONObject(0).getString("temperature");
            temperature1.setText(tempe1);

            t2 = hourly.getJSONArray("data").getJSONObject(1).getString("time");
            long tindi2 = Long.parseLong(t2) * 1000;
            time2.setText(get2(tindi2));
            tempe2 = hourly.getJSONArray("data").getJSONObject(1).getString("temperature");
            temperature2.setText(tempe2);

            t3 = hourly.getJSONArray("data").getJSONObject(2).getString("time");
            long tindi3 = Long.parseLong(t3) * 1000;
            time3.setText(get3(tindi3));
            tempe3 = hourly.getJSONArray("data").getJSONObject(2).getString("temperature");
            temperature3.setText(tempe3);

            t4 = hourly.getJSONArray("data").getJSONObject(3).getString("time");
            long tindi4 = Long.parseLong(t4) * 1000;
            time4.setText(get4(tindi4));
            tempe4 = hourly.getJSONArray("data").getJSONObject(3).getString("temperature");
            temperature4.setText(tempe4);

            t5 = hourly.getJSONArray("data").getJSONObject(4).getString("time");
            long tindi5 = Long.parseLong(t5) * 1000;
            time5.setText(get5(tindi5));
            tempe5 = hourly.getJSONArray("data").getJSONObject(4).getString("temperature");
            temperature5.setText(tempe5);

            t6 = hourly.getJSONArray("data").getJSONObject(5).getString("time");
            long tindi6 = Long.parseLong(t6) * 1000;
            time6.setText(get6(tindi6));
            tempe6 = hourly.getJSONArray("data").getJSONObject(5).getString("temperature");
            temperature6.setText(tempe6);

            t7 = hourly.getJSONArray("data").getJSONObject(6).getString("time");
            long tindi7 = Long.parseLong(t7) * 1000;
            time7.setText(get7(tindi7));
            tempe7 = hourly.getJSONArray("data").getJSONObject(6).getString("temperature");
            temperature7.setText(tempe7);

            t8 = hourly.getJSONArray("data").getJSONObject(7).getString("time");
            long tindi8 = Long.parseLong(t8) * 1000;
            time8.setText(get8(tindi8));
            tempe8 = hourly.getJSONArray("data").getJSONObject(7).getString("temperature");
            temperature8.setText(tempe8);

            t9 = hourly.getJSONArray("data").getJSONObject(8).getString("time");
            long tindi9 = Long.parseLong(t9) * 1000;
            time9.setText(get9(tindi9));
            tempe9 = hourly.getJSONArray("data").getJSONObject(8).getString("temperature");
            temperature9.setText(tempe9);

            t10 = hourly.getJSONArray("data").getJSONObject(9).getString("time");
            long tindi10 = Long.parseLong(t10) * 1000;
            time10.setText(get10(tindi10));
            tempe10 = hourly.getJSONArray("data").getJSONObject(9).getString("temperature");
            temperature10.setText(tempe10);

            t11 = hourly.getJSONArray("data").getJSONObject(10).getString("time");
            long tindi11 = Long.parseLong(t11) * 1000;
            time11.setText(get11(tindi11));
            tempe11 = hourly.getJSONArray("data").getJSONObject(10).getString("temperature");
            temperature11.setText(tempe11);

            t12 = hourly.getJSONArray("data").getJSONObject(11).getString("time");
            long tindi12 = Long.parseLong(t12) * 1000;
            time12.setText(get12(tindi12));
            tempe12 = hourly.getJSONArray("data").getJSONObject(11).getString("temperature");
            temperature12.setText(tempe12);


            /* after button click(next 12 hours)  */

            t13 = hourly.getJSONArray("data").getJSONObject(12).getString("time");
            long tindi13 = Long.parseLong(t13) * 1000;
            time13.setText(get13(tindi13));
            tempe13 = hourly.getJSONArray("data").getJSONObject(12).getString("temperature");
            temperature13.setText(tempe13);

            t14 = hourly.getJSONArray("data").getJSONObject(13).getString("time");
            long tindi14 = Long.parseLong(t14) * 1000;
            time14.setText(get14(tindi14));
            tempe14 = hourly.getJSONArray("data").getJSONObject(13).getString("temperature");
            temperature14.setText(tempe14);

            t15 = hourly.getJSONArray("data").getJSONObject(14).getString("time");
            long tindi15 = Long.parseLong(t15) * 1000;
            time15.setText(get15(tindi15));
            tempe15 = hourly.getJSONArray("data").getJSONObject(14).getString("temperature");
            temperature15.setText(tempe15);

            t16 = hourly.getJSONArray("data").getJSONObject(15).getString("time");
            long tindi16 = Long.parseLong(t16) * 1000;
            time16.setText(get16(tindi16));
            tempe16 = hourly.getJSONArray("data").getJSONObject(15).getString("temperature");
            temperature16.setText(tempe16);

            t17 = hourly.getJSONArray("data").getJSONObject(16).getString("time");
            long tindi17 = Long.parseLong(t17) * 1000;
            time17.setText(get17(tindi17));
            tempe17 = hourly.getJSONArray("data").getJSONObject(16).getString("temperature");
            temperature17.setText(tempe17);

            t18 = hourly.getJSONArray("data").getJSONObject(17).getString("time");
            long tindi18 = Long.parseLong(t18) * 1000;
            time18.setText(get18(tindi18));
            tempe18 = hourly.getJSONArray("data").getJSONObject(17).getString("temperature");
            temperature18.setText(tempe18);

            t19 = hourly.getJSONArray("data").getJSONObject(18).getString("time");
            long tindi19 = Long.parseLong(t19) * 1000;
            time19.setText(get19(tindi19));
            tempe19 = hourly.getJSONArray("data").getJSONObject(18).getString("temperature");
            temperature19.setText(tempe19);

            t20 = hourly.getJSONArray("data").getJSONObject(19).getString("time");
            long tindi20 = Long.parseLong(t20) * 1000;
            time20.setText(get20(tindi20));
            tempe20 = hourly.getJSONArray("data").getJSONObject(19).getString("temperature");
            temperature20.setText(tempe20);

            t21 = hourly.getJSONArray("data").getJSONObject(20).getString("time");
            long tindi21 = Long.parseLong(t21) * 1000;
            time21.setText(get21(tindi21));
            tempe21 = hourly.getJSONArray("data").getJSONObject(20).getString("temperature");
            temperature21.setText(tempe21);

            t22 = hourly.getJSONArray("data").getJSONObject(21).getString("time");
            long tindi22 = Long.parseLong(t22) * 1000;
            time22.setText(get22(tindi22));
            tempe22 = hourly.getJSONArray("data").getJSONObject(21).getString("temperature");
            temperature22.setText(tempe22);

            t23 = hourly.getJSONArray("data").getJSONObject(22).getString("time");
            long tindi23 = Long.parseLong(t23) * 1000;
            time23.setText(get1(tindi23));
            tempe23 = hourly.getJSONArray("data").getJSONObject(22).getString("temperature");
            temperature23.setText(tempe23);

            t24 = hourly.getJSONArray("data").getJSONObject(23).getString("time");
            tempe24 = hourly.getJSONArray("data").getJSONObject(23).getString("temperature");
            long tindi24 = Long.parseLong(t24) * 1000;
            time24.setText(get24(tindi24));
            temperature24.setText(tempe24);



            /* Weekly data to be displayed  */

            d1 = daily1.getJSONArray("data").getJSONObject(1).getString("time");

            long timestamp = Long.parseLong(d1) *1000;
            divas1.setText(getDate(timestamp));

            tmi1 = daily1.getJSONArray("data").getJSONObject(1).getString("temperatureMin");
            tma1 = daily1.getJSONArray("data").getJSONObject(1).getString("temperatureMax");

            oops1.setText(" L: " + tmi1 + "°" + " | H : " + tma1 + "°");

            /* Day 2 */
            d2 = daily1.getJSONArray("data").getJSONObject(2).getString("time");

            long timestamp2 = Long.parseLong(d2) *1000;
            divas2.setText(getDate2(timestamp2));

            tmi2 = daily1.getJSONArray("data").getJSONObject(2).getString("temperatureMin");
            tma2 = daily1.getJSONArray("data").getJSONObject(2).getString("temperatureMax");

            oops2.setText(" L: " + tmi2 + "°" + " | H : " + tma2 + "°");

            /* Day 3 */

            d3 = daily1.getJSONArray("data").getJSONObject(3).getString("time");

            long timestamp3 = Long.parseLong(d3) *1000;
            divas3.setText(getDate3(timestamp3));

            tmi3 = daily1.getJSONArray("data").getJSONObject(3).getString("temperatureMin");
            tma3 = daily1.getJSONArray("data").getJSONObject(3).getString("temperatureMax");

            oops3.setText(" L: " + tmi3 + "°" + " | H : " + tma3 + "°");


            /* Day 4*/

            d4 = daily1.getJSONArray("data").getJSONObject(4).getString("time");

            long timestamp4 = Long.parseLong(d4) *1000;
            divas4.setText(getDate4(timestamp4));

            tmi4 = daily1.getJSONArray("data").getJSONObject(4).getString("temperatureMin");
            tma4 = daily1.getJSONArray("data").getJSONObject(4).getString("temperatureMax");

            oops4.setText(" L: " + tmi4 + "°" + " | H : " + tma4 + "°");

            /*Day 5 */

            d5 = daily1.getJSONArray("data").getJSONObject(5).getString("time");

            long timestamp5 = Long.parseLong(d5) *1000;
            divas5.setText(getDate5(timestamp5));

            tmi5 = daily1.getJSONArray("data").getJSONObject(5).getString("temperatureMin");
            tma5 = daily1.getJSONArray("data").getJSONObject(5).getString("temperatureMax");

            oops5.setText(" L: " + tmi5 + "°" + " | H : " + tma5 + "°");


            /* Day 6 */
            d6 = daily1.getJSONArray("data").getJSONObject(6).getString("time");

            long timestamp6 = Long.parseLong(d6) *1000;
            divas6.setText(getDate6(timestamp6));

            tmi6 = daily1.getJSONArray("data").getJSONObject(6).getString("temperatureMin");
            tma6 = daily1.getJSONArray("data").getJSONObject(6).getString("temperatureMax");

            oops6.setText(" L: " + tmi6 + "°" + " | H : " + tma6 + "°");


            /* Day 7 */
            d7 = daily1.getJSONArray("data").getJSONObject(7).getString("time");

            long timestamp7 = Long.parseLong(d7) *1000;
            divas7.setText(getDate7(timestamp7));

            tmi7 = daily1.getJSONArray("data").getJSONObject(7).getString("temperatureMin");
            tma7 = daily1.getJSONArray("data").getJSONObject(7).getString("temperatureMax");

            oops7.setText(" L: " + tmi7 + "°" + " | H : " + tma7 + "°");




        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void addListernerOnButton(){

        b = (Button) findViewById(R.id.button3);
        days = (Button) findViewById(R.id.button);
        weeks = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button3){
                    s1.fullScroll(View.FOCUS_DOWN);
                }
            }
        });

        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button){
                    s1.setVisibility(View.VISIBLE);
                    s2.setVisibility(View.INVISIBLE);
                }
            }
        });

        weeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.button2){
                    s1.setVisibility(View.INVISIBLE);
                    s2.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    private String getDate(long timeStamp){

        try{
            DateFormat sdf = new SimpleDateFormat("EEEE MMM dd ");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate2(long timeStamp2){

        try{
            DateFormat sdf2 = new SimpleDateFormat("EEEE MMM dd ");
            Date netDate2 = (new Date(timeStamp2));
            return sdf2.format(netDate2);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate3(long timeStamp3){

        try{
            DateFormat sdf3 = new SimpleDateFormat("EEEE MMM dd ");
            Date netDate3 = (new Date(timeStamp3));
            return sdf3.format(netDate3);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate4(long timeStamp4){

        try{
            DateFormat sdf4 = new SimpleDateFormat("EEEE MMM dd ");
            Date netDate4 = (new Date(timeStamp4));
            return sdf4.format(netDate4);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate5(long timeStamp5){

        try{
            DateFormat sdf5 = new SimpleDateFormat("EEEE MMM dd ");
            Date netDate5 = (new Date(timeStamp5));
            return sdf5.format(netDate5);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate6(long timeStamp6){

        try{
            DateFormat sdf6 = new SimpleDateFormat("EEEE MMM dd ");
            Date netDate6 = (new Date(timeStamp6));
            return sdf6.format(netDate6);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate7(long timeStamp7){

        try{
            DateFormat sdf7 = new SimpleDateFormat(" EEEE MMM dd ");
            Date netDate7 = (new Date(timeStamp7));
            return sdf7.format(netDate7);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String get1(long tindi1){

        try{
            DateFormat s1 = new SimpleDateFormat(" hh mm a ");
            Date n1 = (new Date(tindi1));
            return s1.format(n1);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get2(long tindi2){

        try{
            DateFormat s2 = new SimpleDateFormat(" hh mm a ");
            Date n2 = (new Date(tindi2));
            return s2.format(n2);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get3(long tindi3){

        try{
            DateFormat s3 = new SimpleDateFormat(" hh mm a ");
            Date n3 = (new Date(tindi3));
            return s3.format(n3);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get4(long tindi4){

        try{
            DateFormat s4 = new SimpleDateFormat(" hh mm a ");
            Date n4 = (new Date(tindi4));
            return s4.format(n4);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get5(long tindi5){

        try{
            DateFormat s5 = new SimpleDateFormat(" hh mm a ");
            Date n5 = (new Date(tindi5));
            return s5.format(n5);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get6(long tindi6){

        try{
            DateFormat s6 = new SimpleDateFormat(" hh mm a ");
            Date n6 = (new Date(tindi6));
            return s6.format(n6);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get7(long tindi7){

        try{
            DateFormat s7 = new SimpleDateFormat(" hh mm a ");
            Date n7 = (new Date(tindi7));
            return s7.format(n7);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get8(long tindi8){

        try{
            DateFormat s8 = new SimpleDateFormat(" hh mm a ");
            Date n8 = (new Date(tindi8));
            return s8.format(n8);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get9(long tindi9){

        try{
            DateFormat s9 = new SimpleDateFormat(" hh mm a ");
            Date n9 = (new Date(tindi9));
            return s9.format(n9);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get10(long tindi10){

        try{
            DateFormat s10 = new SimpleDateFormat(" hh mm a ");
            Date n10 = (new Date(tindi10));
            return s10.format(n10);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get11(long tindi11){

        try{
            DateFormat s11 = new SimpleDateFormat(" hh mm a ");
            Date n11 = (new Date(tindi11));
            return s11.format(n11);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get12(long tindi12){

        try{
            DateFormat s12 = new SimpleDateFormat(" hh mm a ");
            Date n12 = (new Date(tindi12));
            return s12.format(n12);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get13(long tindi13){

        try{
            DateFormat s13 = new SimpleDateFormat(" hh mm a ");
            Date n13 = (new Date(tindi13));
            return s13.format(n13);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get14(long tindi14){

        try{
            DateFormat s14 = new SimpleDateFormat(" hh mm a ");
            Date n14 = (new Date(tindi14));
            return s14.format(n14);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get15(long tindi15){

        try{
            DateFormat s15 = new SimpleDateFormat(" hh mm a ");
            Date n15 = (new Date(tindi15));
            return s15.format(n15);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get16(long tindi16){

        try{
            DateFormat s16 = new SimpleDateFormat(" hh mm a ");
            Date n16 = (new Date(tindi16));
            return s16.format(n16);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get17(long tindi17){

        try{
            DateFormat s17 = new SimpleDateFormat(" hh mm a ");
            Date n17 = (new Date(tindi17));
            return s17.format(n17);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get18(long tindi18){

        try{
            DateFormat s18 = new SimpleDateFormat(" hh mm a ");
            Date n18 = (new Date(tindi18));
            return s18.format(n18);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get19(long tindi19){

        try{
            DateFormat s19 = new SimpleDateFormat(" hh mm a ");
            Date n19 = (new Date(tindi19));
            return s19.format(n19);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get20(long tindi20){

        try{
            DateFormat s20 = new SimpleDateFormat(" hh mm a ");
            Date n20 = (new Date(tindi20));
            return s20.format(n20);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get21(long tindi21){

        try{
            DateFormat s21 = new SimpleDateFormat(" hh mm a ");
            Date n21 = (new Date(tindi21));
            return s21.format(n21);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get22(long tindi22){

        try{
            DateFormat s22 = new SimpleDateFormat(" hh mm a ");
            Date n22 = (new Date(tindi22));
            return s22.format(n22);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get23(long tindi23){

        try{
            DateFormat s23 = new SimpleDateFormat(" hh mm a ");
            Date n23 = (new Date(tindi23));
            return s23.format(n23);
        }
        catch(Exception ex){
            return "xx";
        }
    }
    private String get24(long tindi24){

        try{
            DateFormat s24 = new SimpleDateFormat(" hh mm a ");
            Date n24 = (new Date(tindi24));
            return s24.format(n24);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}
