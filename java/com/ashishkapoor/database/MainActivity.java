package com.ashishkapoor.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button insert_=(Button) findViewById(R.id.insert);
        final Button select_all=(Button) findViewById(R.id.selectall);
        final Button select_=(Button) findViewById(R.id.select);
        final Button update_=(Button) findViewById(R.id.update);
        final Button delete_=(Button) findViewById(R.id.delete);
        final EditText number_=(EditText)findViewById(R.id.number);
        final EditText name_=(EditText)findViewById(R.id.name);
        final EditText email_=(EditText)findViewById(R.id.email);


        // Initialize the database
        final DBAdapter db = new DBAdapter(MainActivity.this);

        //---Insert Contact---
        insert_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                db.insertContact(name_.getText().toString(), email_.getText().toString());
                db.close();
                Toast.makeText(getBaseContext(), "Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        //---Select All contacts---
        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                Cursor c = db.getAllContacts();
                if (c.moveToFirst())
                {
                    do {
                        DisplayContact(c);
                    } while (c.moveToNext());
                }
                db.close();
            }

            private void DisplayContact(Cursor c)
            {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(),"id: " + c.getString(0) + "\n" +"Name: " + c.getString(1) + "\n" +
                "Email: " + c.getString(2), Toast.LENGTH_LONG).show();
            }
        });

        //---Select a contact---
        select_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                Cursor c = db.getContact(Integer.parseInt
                        (number_.getText().toString()));
                if (c.moveToFirst())
                    DisplayContact(c);
                else
                    Toast.makeText(getBaseContext(), "No contact found",
                            Toast.LENGTH_LONG).show();
                db.close();
            }

            private void DisplayContact(Cursor c) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(),"id: " + c.getString(0) +
                                "\n" +"Name: " + c.getString(1) + "\n" +
                                "Email: " + c.getString(2),
                        Toast.LENGTH_LONG).show();
            }
        }) ;

        //---updates a contact---
        update_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                if (db.updateContact
                        (Integer.parseInt(number_.getText().toString()),
                                name_.getText().toString(), email_.getText().toString()))

                    Toast.makeText(getBaseContext(), "Update successful.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(), "Update failed.",
                            Toast.LENGTH_LONG).show();
                db.close();
            }
        });
        //---delete a contact---
        delete_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                db.deleteContact(Integer.parseInt(number_.getText().toString()));
                db.close();
            }
        });

    }

}


