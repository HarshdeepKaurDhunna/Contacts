package com.example.contacts;

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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddContact.this);
                databaseHelper.addContact(userNameVal.getText().toString().trim(), userContactVal.getText().toString().trim());
            }
        });
    }
}

