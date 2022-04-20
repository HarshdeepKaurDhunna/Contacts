/**
 * @author A00246003
 */
package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "contact_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_CONTACT = "contact_number";

    /**
     * Create Database
     */
    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Create Database Tables
     */
    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CONTACT + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Create Database if not exists
     */
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insert data to database call on add method
     */
    void addContact(String name, String contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CONTACT, contact);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Log.d("Added", "This is Added message");
            Toast.makeText(context, "Contact Not Added Successfully!", Toast.LENGTH_SHORT).show();
        }else {
            Log.d("Not Added", "This is Not Added message");
            Toast.makeText(context, "Contact Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * get contact list
     */
    Cursor getContacts(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * update data to database call on update method
     */
    void updateContact(String row_id, String name, String contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CONTACT, contact);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Contact Not Updated Successfully!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Contact Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * delete data to database call on delete method
     */
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed To Delete Contact", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
