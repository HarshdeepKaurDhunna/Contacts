package com.example.contacts;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateContact extends AppCompatActivity {

    EditText nameVal, contactVal;
    Button updateButton, deleteButton;

    String id, name, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        nameVal = findViewById(R.id.nameVal);
        contactVal = findViewById(R.id.contactVal);

        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        intentInitializer();
        callButtonClick();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }
    }

    /**
     * Button call backs methods
     */
    public void callButtonClick() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateContact.this);
                name = nameVal.getText().toString().trim();
                contact = contactVal.getText().toString().trim();

                databaseHelper.updateContact(id, name, contact);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });

    }

    /**
     * Button call backs methods
     */
    void intentInitializer(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("contact")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            contact = getIntent().getStringExtra("contact");


            //Setting Intent Data
            nameVal.setText(name);
            contactVal.setText(contact);

            Log.d("stev", name+" "+contact);
        }else{
            Toast.makeText(this, "No Data Available.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to confirmDelete
     */
    void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateContact.this);
                databaseHelper.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
