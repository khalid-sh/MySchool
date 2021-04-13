package com.khalid.projectaandroid.AjoutForm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.khalid.projectaandroid.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AjoutActivity extends AppCompatActivity {

    TextInputEditText date_time_in;
     TextInputEditText date_birth;
    TextView text_role;
    Dialog dialog;
    TextView text_state;
    ArrayList<String> list_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.formualire_ajout);
        changeStatusBarColor();
       // date_time_in = findViewById(R.id.editTextDateTime);
date_birth=findViewById(R.id.edittextBirth);
    text_role = findViewById(R.id.Textrole);

    ArrayList<String> liste_role;

        liste_role = new ArrayList<>();
        liste_role.add("Admin");
        liste_role.add("Elève");
        liste_role.add("Professeur");


        text_role.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                dialog = new Dialog(AjoutActivity.this);

                dialog.setContentView(R.layout.search_spinner);

                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


                EditText editrole = dialog.findViewById(R.id.edit_role);

                ListView listerole = dialog.findViewById(R.id.list_der_role);


                ArrayAdapter<String> adapter = new ArrayAdapter<>(AjoutActivity.this, android.R.layout.simple_list_item_1, liste_role);


                listerole.setAdapter(adapter);

                editrole.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listerole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        text_role.setText(adapter.getItem(position));

                        dialog.dismiss();
                    }
                });
            }
        });

        text_state = findViewById(R.id.state);

        list_state = new ArrayList<>();
        list_state.add("Sale");
        list_state.add("Rabat");
        list_state.add("Casablanca");
        list_state.add("kenitra");
        list_state.add("Fes");
        list_state.add("Meknes");
        list_state.add("El Jadida");
        list_state.add("Asfi");


        text_state.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                dialog = new Dialog(AjoutActivity.this);

                dialog.setContentView(R.layout.search_spinner_state);

                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


                EditText editstate = dialog.findViewById(R.id.edit_state);

                ListView listestate = dialog.findViewById(R.id.list_der_state);


                ArrayAdapter<String> adapter = new ArrayAdapter<>(AjoutActivity.this, android.R.layout.simple_list_item_1, list_state);


               listestate .setAdapter(adapter);

                editstate.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listestate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        text_state.setText(adapter.getItem(position));

                        dialog.dismiss();
                    }
                });
            }
        });


         date_time_in.setInputType(InputType.TYPE_NULL);
        date_birth.setInputType(InputType.TYPE_NULL);


       date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(date_time_in);
            }
        });

        date_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_birth);
            }
        });

    }

 private void showDateTimeDialog(final TextInputEditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(AjoutActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

        new DatePickerDialog(AjoutActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

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

        new DatePickerDialog(AjoutActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


        private void changeStatusBarColor () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
              //  window.setStatusBarColor(Color.TRANSPARENT);
                window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
            }
        }

}




