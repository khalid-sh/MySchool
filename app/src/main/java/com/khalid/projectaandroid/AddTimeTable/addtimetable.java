package com.khalid.projectaandroid.AddTimeTable;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.khalid.projectaandroid.AddExam.addExam;
import com.khalid.projectaandroid.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addtimetable extends AppCompatActivity {

    TextView txtfile;
    Button btnfile;
    TextInputEditText date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exam);
        txtfile = findViewById(R.id.Textfile);
        btnfile = findViewById(R.id.buttselect);
        date = findViewById(R.id.edittextDate);



        btnfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent file=new Intent(Intent.ACTION_GET_CONTENT);
                file.setType("application/*");
                startActivityForResult(file,10);

            }
        });

        date.setInputType(InputType.TYPE_NULL);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 10:
                if (resultCode == RESULT_OK) {

                    String path = data.getData().getLastPathSegment();

                    File flt = new File(path);

                    String nomFile= flt.getName();
                    txtfile.setText(nomFile);
                }

                break;
        }

    }


    private void showDateDialog(final TextInputEditText date_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(addtimetable.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }





}
