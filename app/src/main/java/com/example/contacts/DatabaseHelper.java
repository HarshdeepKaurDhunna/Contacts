package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DatabaseNAME = "Contacts.db";
    private static final int DatabaseVersion = 1;

    private static final String TableName = "contactLibrary";
    private static final String ColumnId = "_id";
    private static final String ColumnName = "userNameVal";
    private static final String ColumnContact = "userContactVal";


    DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseNAME, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TableName +
                " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnName + " TEXT, " +
                ColumnContact + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }
    void addContact(String name, String contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ColumnName, name);
        contentValues.put(ColumnContact, contact);

        long result = sqLiteDatabase.insert(TableName,null, contentValues);
        if(result == -1){
            Toast.makeText(context, "Contact Not Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Contact Not Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor getContacts(){
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = null;
        if(sqLiteDatabase != null){
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }
    void updateContact(String row_id, String name, String contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnName, name);
        contentValues.put(ColumnContact, contact);


        long result = sqLiteDatabase.update(TableName, contentValues, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Contact Not Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Contact Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TableName, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Contact Not Deleted.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
