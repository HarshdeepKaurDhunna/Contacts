/**
 * @author A00246003
 */
package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {

    EditText userNameVal, userContactVal;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        userNameVal = findViewById(R.id.userNameVal);
        userContactVal = findViewById(R.id.userContactVal);

        addButton = findViewById(R.id.addButton);
        setOnclickListner();
    }

    /**
     * Call On add button click
     */
    public void setOnclickListner(){
        addButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //instantiate database helper
            DatabaseHelper databaseHelper = new DatabaseHelper(AddContact.this);
            //database helper add method to add contact
            databaseHelper.addContact(userNameVal.getText().toString().trim(), userContactVal.getText().toString().trim());
            Intent intent = new Intent(AddContact.this, MainActivity.class);
            startActivity(intent);

        }
    });}

}

