package com.khalid.projectaandroid.AddProf;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.khalid.projectaandroid.R;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class addProf extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");

    private static final Pattern NOM_PRENOM_PATTERN =
            Pattern.compile("[A-Z]+[a-z]*");

    private TextInputLayout Email_INPUT;
    private TextInputLayout Nom_INPUT;
    private TextInputLayout Prenom_INPUT;
    private TextInputLayout Password_INPUT;

    TextInputEditText date_time_in;
    TextInputEditText date_birth;
    TextView text_role;
    Dialog dialog;
    TextView text_state;
    ArrayList<String> list_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        Email_INPUT = findViewById(R.id.text_email);
        Nom_INPUT = findViewById(R.id.text_nom);
        Prenom_INPUT = findViewById(R.id.text_prenom);
        Password_INPUT = findViewById(R.id.text_password);


        date_birth = findViewById(R.id.edittextBirth);
        text_role = findViewById(R.id.Textrole);

        ArrayList<String> liste_role;

        liste_role = new ArrayList<>();
        liste_role.add("Admin");
        liste_role.add("Elève");
        liste_role.add("Professeur");


        text_role.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                dialog = new Dialog(addProf.this);

                dialog.setContentView(R.layout.search_spinner);

                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


                EditText editrole = dialog.findViewById(R.id.edit_role);

                ListView listerole = dialog.findViewById(R.id.list_der_role);


                ArrayAdapter<String> adapter = new ArrayAdapter<>(addProf.this, android.R.layout.simple_list_item_1, liste_role);


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

                dialog = new Dialog(addProf.this);

                dialog.setContentView(R.layout.search_spinner_state);

                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


                EditText editstate = dialog.findViewById(R.id.edit_state);

                ListView listestate = dialog.findViewById(R.id.list_der_state);


                ArrayAdapter<String> adapter = new ArrayAdapter<>(addProf.this, android.R.layout.simple_list_item_1, list_state);


                listestate.setAdapter(adapter);

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



        date_birth.setInputType(InputType.TYPE_NULL);



        date_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_birth);
            }
        });


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

        new DatePickerDialog(addProf.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private boolean emailConfirm() {

        String emailinput = Email_INPUT.getEditText().getText().toString().trim();

        if (emailinput.isEmpty()) {
            Email_INPUT.setError("Le champ est vide");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
            Email_INPUT.setError("La forme du email n'est pas respecter");
            return false;

        } else {

            Email_INPUT.setError(null);
            return true;

        }


    }

    private boolean passwordConfirm() {


        String passwordinput = Password_INPUT.getEditText().getText().toString().trim();

        if (passwordinput.isEmpty()) {
            Password_INPUT.setError("Le champ est vide");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordinput).matches()) {
            Password_INPUT.setError("Password too weak");
            return false;

        } else {

            Password_INPUT.setError(null);
            return true;

        }

    }

    private boolean nomConfirm() {

        String nominput = Nom_INPUT.getEditText().getText().toString().trim();

        if (nominput.isEmpty()) {
            Nom_INPUT.setError("Le champ est vide");
            return false;
        } else if (!NOM_PRENOM_PATTERN.matcher(nominput).matches()) {

            Nom_INPUT.setError("enter the correct format");
            return false;
        } else {

            Nom_INPUT.setError(null);
            return true;
        }

    }

    private boolean prenomConfirm() {
        String prenominput = Prenom_INPUT.getEditText().getText().toString().trim();

        if (prenominput.isEmpty()) {
            Prenom_INPUT.setError("Le champ est vide");
            return false;
        } else if (!NOM_PRENOM_PATTERN.matcher(prenominput).matches()) {

            Prenom_INPUT.setError("enter the correct format");
            return false;
        } else {

            Prenom_INPUT.setError(null);
            return true;
        }


    }


    public void clickMis(View v) {

        if (!emailConfirm() | !prenomConfirm() | !passwordConfirm() | !nomConfirm())
            return;

    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


}
