package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    EditText title,date,time,description;
    Spinner event;
    Button save_btn;

    MyDBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        title = findViewById(R.id.title_event);
        time = findViewById(R.id.time_event);
        date = findViewById(R.id.date_event);
        description = findViewById(R.id.editText2);
        event = findViewById(R.id.event_text);
        save_btn = findViewById(R.id.save_btn);

        myDBHandler = new MyDBHandler(this);


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean val = myDBHandler.add_events(title.getText().toString(),event.getSelectedItem().toString(),date.getText().toString(),time.getText().toString(),description.getText().toString());

                if(val)
                {
                    finish();
                }




            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.event_types,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event.setAdapter(adapter);
    }
}