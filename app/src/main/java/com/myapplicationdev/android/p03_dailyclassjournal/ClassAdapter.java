package com.myapplicationdev.android.p03_dailyclassjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends ArrayAdapter<Class> {

    private ArrayList<Class> classDG;
    private Context context;
    private TextView tvWeek, tvDailyGrade;

    public ClassAdapter(Context context, int resource, ArrayList<Class> objects){
        super(context, resource, objects);

        classDG = objects;

        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvWeek = rowView.findViewById(R.id.textViewWeek);

        tvDailyGrade = rowView.findViewById(R.id.textViewDailyGrade);


        Class currentClass = classDG.get(position);

        tvWeek.setText("Week " + currentClass.getWeek());
        tvDailyGrade.setText(currentClass.getDailyGrade());

        return rowView;
    }

}
