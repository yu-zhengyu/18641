package com.example.zhengyu.parta.util;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
/**
 * Created by zhengyu on 15/11/8.
 * This class can help develop handle the Database, Reference from Prof's example code
 *
 */

public class DatabaseConnector {
    private static final String DATABASE_NAME = "Score";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

    public void insertContact(int Stud, float Q1, float Q2,
                              float Q3, float Q4, float Q5) {
        ContentValues newContact = new ContentValues();
        newContact.put("Stud", Stud);
        newContact.put("Q1", Q1);
        newContact.put("Q2", Q2);
        newContact.put("Q3", Q3);
        newContact.put("Q4", Q4);
        newContact.put("Q5", Q5);

        open(); // open the database
        database.insert("Score", null, newContact);
        close(); // close the database
    } // end method insertContact

    public void updateContact(int Stud, float Q1, float Q2,
                              float Q3, float Q4, float Q5) {
        ContentValues editContact = new ContentValues();
        editContact.put("Q1", Q1);
        editContact.put("Q2", Q2);
        editContact.put("Q3", Q3);
        editContact.put("Q4", Q4);
        editContact.put("Q5", Q5);

        open(); // open the database
        try {
            database.update("Score", editContact, "Stud" + Stud, null);
        } catch (Exception e) {
            Log.e("DATABASE", "Insert ERROR!!!!");
        }
        close(); // close the database
    } // end method insertContact

    // return a Cursor with all Mortgage information in the database
    public Cursor getAllInformation()
    {
        return database.query("Score", null, null, null, null, null, null);
    } // end method getAllInformation

    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE Score" +
                    " (Stud integer primary key,"
                    + "Q1 REAL, " + "Q2 REAL, Q3 REAL, "
                    + "Q4 REAL, Q5 REAL)";
            System.out.println("Database Create Successful");

            Log.d("Createtable", "Create");

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        public void deleteScore(int Stud) {
            open();
            try {
                database.delete("Score", "Stud" + " " + Stud, null);
            } catch (Exception e) {
                Log.e("DabaBase", "Delete ERROR");
            }
            close();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {

        } // end method onUpgrade



    } // end class DatabaseOpenHelper

}
