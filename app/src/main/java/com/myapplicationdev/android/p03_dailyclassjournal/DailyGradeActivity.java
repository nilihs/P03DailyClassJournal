package com.myapplicationdev.android.p03_dailyclassjournal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DailyGradeActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Class> classDG;
    Button btnInfo, btnAdd, btnEmail;

    int requestCodeForAdd = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_grade);

        lv = findViewById(R.id.lvWeek);

        classDG = new ArrayList<Class>();
        classDG.add(new Class("1", "B"));
        classDG.add(new Class("2", "C"));
        classDG.add(new Class("3", "A"));

        aa = new ClassAdapter(this, R.layout.row, classDG);
        lv.setAdapter(aa);


        btnInfo = findViewById(R.id.buttonInfo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnEmail = findViewById(R.id.buttonEmail);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Intent to display data
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);

                // Set the URL to be used.
                rpIntent.setData(Uri.parse("http://www.rp.edu.sg"));
                startActivity(rpIntent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weekAdd = "Week: ";
                weekAdd += Integer.toString(classDG.size()+1);
                Class newWeek = new Class(weekAdd, "X");

                Intent i = new Intent(DailyGradeActivity.this, AddGrade.class);

                i.putExtra("class", newWeek);
                startActivityForResult(i, requestCodeForAdd);

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // The action you want this intent to do;
                // ACTION_SEND is used to indicate sending text
                Intent email = new Intent(Intent.ACTION_SEND);

                // Put essentials like email address, subject & body text
                email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"jason_lim@rp.edu.sg"});

                email.putExtra(Intent.EXTRA_SUBJECT,
                        "");

                String text = "";
                for (int i = 0; i < classDG.size(); i++) {

                    text += "Week " + classDG.get(i).getWeek() + ": DG: " + classDG.get(i).getDailyGrade() +
                            "\n";

                }

                email.putExtra(Intent.EXTRA_TEXT, text);



                // This MIME type indicates email
                email.setType("message/rfc822");

                // createChooser shows user a list of app that can handle
                // this MIME type, which is, email
                startActivity(Intent.createChooser(email,
                        "Choose an Email client :"));

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK){
            if (data != null) {

                String newDailyGrade = data.getStringExtra("newDG");
                String newWeekNo = data.getStringExtra("newWeekNumber");


                if(requestCode == requestCodeForAdd){
                    classDG.add(new Class(newWeekNo, newDailyGrade));
                    aa.notifyDataSetChanged();
                }


            }
        }
    }

}

