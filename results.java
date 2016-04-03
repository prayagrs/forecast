package com.example.prayag.forecastsearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class results extends AppCompatActivity {

    String urlParams,city,state,degree,summary,tempe,mint,maxt,w,d,v,h,ic,chance,suns,sunr;
    float precip;
    Button moredetails;
    static JSONObject weatherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        moredetails = (Button) findViewById(R.id.more_details);
        Bundle bundle = getIntent().getExtras();
        urlParams = bundle.getString("urlParams");
        city=bundle.getString("city");
        state=bundle.getString("state");
        degree=bundle.getString("degree");

        new HttpGetter().execute();
        addListenerOnButton();

    }

    public void addListenerOnButton(){
        moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(results.this, details.class);

                startActivity(intent);

            }
        });

    }

    public class HttpGetter extends AsyncTask<Void,String,String>{

        private TextView current;
        private TextView temperature;
        private TextView temeperatureMin;
        private TextView temperatureMax;
        private TextView windSpeed,dewPoint,visibility,humidity,precipIntensity,precipProbability,sunriseTime,sunsetTime;
        private ImageView icon;


        @Override
        protected void onPreExecute(){
            current = (TextView) findViewById(R.id.textView14);
//            city = (TextView) findViewById(R.id.textView13);
//            state = (TextView) findViewById(R.id.textView13);
            temperature = (TextView) findViewById(R.id.textView15);
            temeperatureMin = (TextView) findViewById(R.id.textView16);
            temperatureMax = (TextView) findViewById(R.id.textView16);
            windSpeed = (TextView) findViewById(R.id.textView);
            dewPoint = (TextView) findViewById(R.id.textView21);
            humidity = (TextView) findViewById(R.id.textView22);
            visibility = (TextView) findViewById(R.id.textView23);
            icon = (ImageView) findViewById(R.id.img1);
            precipIntensity = (TextView) findViewById(R.id.textView25);
            precipProbability = (TextView) findViewById(R.id.textView26);
            sunriseTime = (TextView) findViewById(R.id.textView27);
            sunsetTime = (TextView) findViewById(R.id.textView28);


        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                String x = com.example.prayag.forecastsearch.HttpGetter.down(urlParams);
                return x;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(final String x){
            try {
                 JSONObject j = new JSONObject(x);
                weatherData=j;
                JSONObject currently = j.getJSONObject("currently");
                JSONObject daily = j.getJSONObject("daily");
                JSONArray data = daily.getJSONArray("data");
//                for (int i = 0; i < data.length(); i++) {
//                    System.out.println(data);
//                    JSONObject obj = data.getJSONObject(i);
//
//                }







                summary = currently.getString("summary");
                tempe = currently.getString("temperature");
                w = currently.getString("windSpeed");
                d = currently.getString("dewPoint");
                v = currently.getString("humidity");
                h = currently.getString("visibility");
                ic = currently.getString("icon");
                precip = currently.getLong("precipIntensity");
                chance = currently.getString("precipProbability");
                sunr = daily.getJSONArray("data").getJSONObject(0).getString("sunriseTime");
                long timestamp = Long.parseLong(sunr) *1000;
                sunriseTime.setText(getDate(timestamp));
                suns = daily.getJSONArray("data").getJSONObject(0).getString("sunsetTime");
                long timestamp1 = Long.parseLong(suns) *1000;
                sunsetTime.setText(getDate1(timestamp1));
                mint = daily.getJSONArray("data").getJSONObject(0).getString("temperatureMin");
                maxt = daily.getJSONArray("data").getJSONObject(0).getString("temperatureMax");


                if(ic == "clear-day") {
                    icon.setImageResource(R.drawable.clear);
                }
                if(ic == "clear-night") {
                    icon.setImageResource(R.drawable.clear_night);
                }
                if(ic == "rain") {
                    icon.setImageResource(R.drawable.rain);
                }
                if(ic == "snow") {
                    icon.setImageResource(R.drawable.snow);
                }
                if(ic == "sleet") {
                    icon.setImageResource(R.drawable.sleet);
                }
                if(ic == "wind") {
                    icon.setImageResource(R.drawable.wind);
                }
                if(ic == "fog") {
                    icon.setImageResource(R.drawable.fog);
                }
                if(ic == "cloudy") {
                    icon.setImageResource(R.drawable.cloudy);
                }
                if(ic == "partly-cloudy-day") {
                    icon.setImageResource(R.drawable.cloud_day);
                }
                if(ic == "partly-cloudy-night") {
                    icon.setImageResource(R.drawable.clear_night);
                }

                if(precip >= 0 && precip<0.002){
                    precipIntensity.setText("None");
                }
                if(precip >= 0.002 && precip<0.017){
                    precipIntensity.setText("Very Light");
                }
                if(precip >= 0.017 && precip<0.01){
                    precipIntensity.setText("Light");
                }
                if(precip >= 0.01 && precip<0.4){
                    precipIntensity.setText("Moderate");
                }
                if(precip >= 4){
                    precipIntensity.setText("High");
                }
                //mint = data.getJSONArray();
                //maxt = daily.getString("temperatureMax");
                //maxt = daily.get(data[0]).getString("temperatureMax");


                precipProbability.setText(chance + " % ");
                current.setText(summary + " in " + city + " , " + state);
                temperature.setText(tempe + degree );
                temeperatureMin.setText(" L: " + mint + "°"+" | H : " +maxt + "°");

                windSpeed.setText(w + " mph");
                dewPoint.setText(d + degree);
                humidity.setText(h + " % ");
                visibility.setText(v + " mi ");







            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String getDate(long timeStamp){

        try{
            DateFormat sdf = new SimpleDateFormat(" hh:mm a ");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    private String getDate1(long timeStamp1){

        try{
            DateFormat sdf1 = new SimpleDateFormat(" hh:mm a ");
            Date netDate1 = (new Date(timeStamp1));
            return sdf1.format(netDate1);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}
