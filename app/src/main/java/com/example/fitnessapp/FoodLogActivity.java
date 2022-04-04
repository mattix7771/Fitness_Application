package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class FoodLogActivity extends AppCompatActivity {

    //Instance of databaseHelper class
    DatabaseHelper dbh = new DatabaseHelper(this);
    //Initiation of layout elements
    EditText TextName;
    EditText TextCal;
    Button btn_addFood;
    ListView lv_food;
    TextView tv_caloriesToday;
    TextView tv_caloriesNum;
    //Initiation of calorie variable
    int cal;

    /** Upon creation of activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_log);

        //Initialization of layout elements
        TextName = (EditText) findViewById(R.id.TextName);
        TextCal = (EditText) findViewById(R.id.TextCal);
        btn_addFood = (Button) findViewById(R.id.btn_addFood);
        lv_food = (ListView) findViewById(R.id.lv_food);
        tv_caloriesToday = (TextView) findViewById(R.id.tv_caloriesToday);
        tv_caloriesNum = (TextView) findViewById(R.id.tv_caloriesNum);

        //Display data on list view
        getAllData();

        //Get and set calorie value from main activity
        Intent i = getIntent();
        int received_cal = i.getIntExtra("cal", 0);
        cal = received_cal;
        tv_caloriesNum.setText(String.valueOf(cal));

        //Button initialization - on ADD button click - add data to database
        btn_addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get food name from user input
                String name = TextName.getText().toString();

                //check whether there is user input
                if(name.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No food name was entered", Toast.LENGTH_SHORT).show();
                }
                else if(TextCal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "No calories were entered", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Get calories from user inout
                    int textCal = Integer.parseInt(TextCal.getText().toString());

                    //Add data to database
                    dbh.addData(name, textCal);

                    //Reset text boxes
                    TextName.setText("");
                    TextCal.setText("");
                }

                //Display updated data on list view
                getAllData();
            }
        });

        //Set listener upon clicking entry - add calories
        lv_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get name of entry clicked
                String name = adapterView.getItemAtPosition(i).toString();
                //Retrieve info of entry from database
                Cursor entry = dbh.getDataByID(name);

                //Go through all entries found and add to list
                ArrayList<String> data = new ArrayList<>();
                while(entry.moveToNext()){
                    data.add(entry.getString(2));
                }

                //Since there's only one entry, get the calories of the first entry and add it to the sum
                //of calories
                cal = Integer.parseInt(tv_caloriesNum.getText().toString()) + Integer.parseInt(data.get(0));
                tv_caloriesNum.setText(String.valueOf(cal));
            }
        });

        //Set listener upon long clicking entry - delete entry
        lv_food.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
                //Get name of entry clicked
                String name = adapterView.getItemAtPosition(i).toString();
                //Delete entry from database
                dbh.deleteData(name);
                //Tell the user the entry has been deleted
                Toast.makeText(getApplicationContext(), name + " DELETED", Toast.LENGTH_SHORT).show();

                //Update list view
                Cursor entry = dbh.getAllData();
                ArrayList<String> data = new ArrayList<>();
                while(entry.moveToNext()){
                    data.add(entry.getString(1));
                }
                lv_food.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, data));
                return true;
            }
        });

        //Set listener upon long click of calories
        tv_caloriesNum.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Set calories to 0 and reset calorie value
                tv_caloriesNum.setText("0");
                cal = 0;
                return true;
            }
        });
    }

    /** Get all data from database
     */
    protected void getAllData(){
        //Database instance
        Cursor entry = dbh.getAllData();
        //List to hold querie result
        ArrayList<String> listData = new ArrayList<>();
        while(entry.moveToNext()){
            listData.add(entry.getString(1));
        }
        //list data
        lv_food.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listData));
    }

    /** Return data to main activity when exiting activity
     */
    @Override
    public void onBackPressed() {

        //Create intent
        Intent i = new Intent();
        //Set variable to send back
        i.putExtra("cal", cal);
        setResult(RESULT_OK, i);
        //End activity
        finish();
    }
}