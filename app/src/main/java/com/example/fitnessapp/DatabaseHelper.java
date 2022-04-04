package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Initial table setup
    private static String TABLE_NAME = "food_table";
    private String COL_1 = "ID";
    private String COL_2 = "Name";
    private String COL_3 = "Calories";

    /** Default Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    /** Table creation
     *
     * @param db SQLite database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INT PRIMARY KEY, " +
                COL_2 + " VARCHAR(255) NOT NULL, " +
                COL_3 + " INT)";
        db.execSQL(createTable);
    }

    /** Delete table if it exists
     *
     * @param db SQLite database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    /** Add data do database
     *
     * @param name The name of the entry
     * @param cal The calories of the entry
     */
    public void addData(String name, int cal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3,cal);

        db.insert(TABLE_NAME, null, cv);
    }

    /** Get all data present in the database
     *
     * @return all data present in database
     */
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /** Delete entry from database
     *
     * @param name The name of the entry
     */
    public void deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME +
                        " WHERE " + COL_2 + " = '" + name + "'";
        db.execSQL(query);
    }

    /** Extract entry from database
     *
     * @param name The name of the entry
     * @return matching entry
     */
    public Cursor getDataByID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *" + " FROM " + TABLE_NAME +
                " WHERE " + COL_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
