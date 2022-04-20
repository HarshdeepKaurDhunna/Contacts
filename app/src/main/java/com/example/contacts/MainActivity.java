/**
 * @author A00246003
 */
package com.example.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ImageView imageView;
    TextView noVal;

    DatabaseHelper databaseHelper;
    ArrayList<String> userId, userName, userContact;;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        imageView = findViewById(R.id.imageView);
        noVal = findViewById(R.id.noVal);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        userId = new ArrayList<>();
        userName = new ArrayList<>();
        userContact = new ArrayList<>();

        setClickListener();
        addListVal();
        setCustomAdapter();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    public  void setClickListener(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);

            }
        });
    }
    /**
     * GET CONTACT LIST VALUE
     */
    void addListVal(){
        Cursor cursor = databaseHelper.getContacts();
        if(cursor.getCount() == 0){
            imageView.setVisibility(View.VISIBLE);
            noVal.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                userId.add(cursor.getString(0));
                userName.add(cursor.getString(1));
                userContact.add(cursor.getString(2));

            }
            imageView.setVisibility(View.GONE);
            noVal.setVisibility(View.GONE);
        }
    }
    /**
     * SET layout for list
     */
    public void setCustomAdapter(){
        customAdapter = new CustomAdapter(MainActivity.this,this, userId, userName, userContact);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }


}