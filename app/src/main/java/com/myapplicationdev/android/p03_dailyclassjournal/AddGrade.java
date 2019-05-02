package com.myapplicationdev.android.p03_dailyclassjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddGrade extends AppCompatActivity {

    TextView tvWeek;
    RadioGroup rg;
    RadioButton rb;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        Intent i = getIntent();

        Class classWeek = (Class) i.getSerializableExtra("class");

        tvWeek = findViewById(R.id.textViewWeek);
        tvWeek.setText(classWeek.getWeek());

        rg = findViewById(R.id.rg1);

        int selectedButtonId = rg.getCheckedRadioButtonId();

        rb = findViewById(selectedButtonId);
//        final String theGrade = rb.getText().toString();

        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();

                //For debugging purposes, I have set the value to "" because the radiobutton when selected returns null
                i.putExtra("newDG", "");
                i.putExtra("newWeekNumber", tvWeek.getText().toString());

                setResult(RESULT_OK, i);
                finish();

            }
        });
    }
}
