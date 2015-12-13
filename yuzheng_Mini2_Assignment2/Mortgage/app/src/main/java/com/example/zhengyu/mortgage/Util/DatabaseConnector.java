package com.example.zhengyu.mortgage.Util;
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
 * Created by zhengyu on 15/11/2.
 * This class can help develop handle the Database, Reference from Prof's example code
 *
 */

public class DatabaseConnector {
    private static final String DATABASE_NAME = "Mortgage";
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

    public void insertContact(String Name, String TotalMonthlyPayment, String TotalPayment,
                               String PayOffDate) {
        ContentValues newContact = new ContentValues();
        newContact.put("Name", Name);
        newContact.put("TotalMonthlyPayment", TotalMonthlyPayment);
        newContact.put("TotalPayment", TotalPayment);
        newContact.put("PayOffDate", PayOffDate);

        open(); // open the database
        database.insert("Mortgage", null, newContact);
        close(); // close the database
    } // end method insertContact

    public void updateContact(long id, String Name, String TotalMonthlyPayment, String TotalPayment,
                              String PayOffDate) {
        ContentValues editContact = new ContentValues();
        editContact.put("Name", Name);
        editContact.put("TotalMonthlyPayment", TotalMonthlyPayment);
        editContact.put("TotalPayment", TotalPayment);
        editContact.put("PayOffDate", PayOffDate);

        open(); // open the database
        database.update("Mortgage", editContact, "_id=" + id, null);
        close(); // close the database
    } // end method insertContact

    // return a Cursor with all Mortgage information in the database
    public Cursor getAllInformation()
    {
        return database.query("Mortgage", null, null, null, null, null, null);
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
            String createQuery = "CREATE TABLE Mortgage" +
                    " (_id integer primary key autoincrement,"
                    + "Name TEXT, " + "TotalMonthlyPayment TEXT, TotalPayment TEXT, "
                    + "PayOffDate TEXT)";
            System.out.println("Database Create Successful");

            Log.d("Createtable", "Create");

            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        } // end method onUpgrade



    } // end class DatabaseOpenHelper

}