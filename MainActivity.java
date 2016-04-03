package com.example.prayag.forecastsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    Spinner spinner1;
    private Button btnSearch,btnClear,btnAbout;
    private ImageView img;
    EditText street,city;
    TextView showerror,date;
    ArrayAdapter<CharSequence> adapter;
    private static RadioGroup radio_g;
    private static RadioButton radio_deg;
    String degree;
    String urlParams,city1,state;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String S = "1350574775";
        date = (TextView) findViewById(R.id.textView3);

        long timestamp = Long.parseLong(S) * 1000;
        date.setText(getDate(timestamp ));
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        adapter = ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, String.valueOf(spinner1.getSelectedItem()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addListenerOnButton();
        //addListenerOnSpinnerItemSelection();
        addListenerOnImage();

    }

    private String getDate(long timeStamp){

        try{
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a MMM dd");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }


    public void addListenerOnImage(){
        img = (ImageView)findViewById(R.id.forecast);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forecast.io"));
                startActivity(intent);
            }
        });
    }



    public void addListenerOnButton() {



        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnAbout = (Button) findViewById(R.id.about);
        street = (EditText) findViewById(R.id.street);
        city = (EditText) findViewById(R.id.city);
        showerror = (TextView) findViewById(R.id.textView10);

        radio_g = (RadioGroup) findViewById(R.id.temperature);



        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                street.setText("");
                city.setText("");
                spinner1.setSelection(0);
                showerror.setText(" ");
                radio_g.clearCheck();
                radio_g.check(R.id.far);

            }
        });

        btnSearch.setOnClickListener(new OnClickListener() {





            @Override
            public void onClick(View v) {



                if(street.getText().toString().isEmpty())
                    showerror.setText("Please Enter the Street");
                else if(city.getText().toString().isEmpty())
                    showerror.setText("Please Enter the city name");
                else if(spinner1.getSelectedItem().toString().equals("Select"))
                    showerror.setText("Please Select a State");
                else {
                    city1 = city.getText().toString() ;
//                    String street1 = street.getText().toString();
                    state = (String) spinner1.getSelectedItem();
                    degree = radio_g.getCheckedRadioButtonId() == R.id.far ? "° F ":"° C";

                    urlParams = "http://forecastweather-env.elasticbeanstalk.com/?street=" + street.getText().toString() + "&city=" + city.getText().toString() + "&spinner1=" + spinner1.getSelectedItem().toString() + "$degree=" + degree;
                    urlParams = urlParams.replaceAll(" ", "%20");
                    Intent intent = new Intent("com.example.prayag.forecastsearch.results");

                    Bundle prog = new Bundle();
                    prog.putString("urlParams", urlParams);
                    prog.putString("city",city1 );
                    prog.putString("state",state);
                    prog.putString("degree", degree);

                    intent.putExtras(prog);
                    startActivity(intent);






                }

            }

        });

        btnAbout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, about.class);
                startActivity(intent1);


            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
