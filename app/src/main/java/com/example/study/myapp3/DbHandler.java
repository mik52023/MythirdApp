package com.example.study.myapp3;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MyDB";
    // Contacts table name
    private static final String TABLE_BIRTHDAY = "Birthdays";
    // Shops Table Columns names
    private static final String KEY_ID = "name";
    private static final String KEY_birthday = "birthday";
    private static final String KEY_NEXT_BIRTHDAY = "comming birthdays";
    private static final String KEY_COMMENT = "comments";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE"+ TABLE_BIRTHDAY + "("
        + KEY_ID + "TEXT PRIMARY KEY," + KEY_birthday + "INTEGER,"
        + KEY_NEXT_BIRTHDAY + " INTEGER"+KEY_COMMENT+ "Text"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIRTHDAY);
// Creating tables again
        onCreate(db);

    }
    // Adding new shop
    public void addName(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, person.getName()); // Shop Name
        values.put(KEY_birthday, person.getBirthday().toString()); // Shop Phone Number
        values.put(KEY_COMMENT, person.getComments());
        values.put(KEY_NEXT_BIRTHDAY, person.getNextBirthday().toString()); // Shop Name// Shop Name
// Inserting Row
        db.insert(TABLE_BIRTHDAY, null, values);
        db.close(); // Closing database connection
    }
    // Getting one shop
    public Person getPerson(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BIRTHDAY, new String[] { KEY_ID,
                        KEY_birthday, KEY_NEXT_BIRTHDAY }, KEY_ID + "=?",
                new String[]{name}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Person contact = new Person( cursor.getString(0), Date.valueOf(cursor.getString(1)),
                Date.valueOf(cursor.getString(2)), cursor.getString(3));
// return shop

        return contact;
    }
    // Deleting a shop
    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIRTHDAY, KEY_ID + " = ?",
                new String[] { String.valueOf(person.getName()) });
        db.close();
    }
    // Getting All Shops
    public List<Person> getAllpersons() {
        List<Person> personList = new ArrayList<Person>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BIRTHDAY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(cursor.getString(0),Date.valueOf(cursor.getColumnName(1)),Date.valueOf(cursor.getColumnName(2)),cursor.getColumnName(3));
// Adding contact to list
                personList.add(person);
            } while (cursor.moveToNext());
        }

// return contact list
        return personList;
    }
}
