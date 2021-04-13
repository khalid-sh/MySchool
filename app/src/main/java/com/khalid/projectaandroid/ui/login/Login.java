package com.khalid.projectaandroid.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.khalid.projectaandroid.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button  login_btn = (Button)findViewById(R.id.login_btn);
        TextInputEditText email_input = (TextInputEditText)findViewById(R.id.input_email);
        TextInputEditText password_input = (TextInputEditText)findViewById(R.id.input_password);
        login_btn.setOnClickListener(v -> {
            String email = email_input.getText().toString();
            String password = password_input.getText().toString();
            if (isValidEmail(email) && password.length() >= 6) {
                // TODO : VERIFIER DATA BASE  ET PUIS  REDIRIGER VERS PAGE SELON AUTHENTIFICATION
                Toast.makeText(getApplicationContext(),"heelo",Toast.LENGTH_LONG).show();
            } else {
                if (!isValidEmail(email)) {
                    email_input.setError("Email not valid");
                }else {
                    password_input.setError("Password too short");
                }
            }
        }
        );
    }
    public static boolean isValidEmail(String email){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }
    public static boolean isValidUSN(String usn){
        Pattern VALID_USN_REGEX = Pattern.compile("^[0-9][A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{3}$");
        Matcher matcher = VALID_USN_REGEX.matcher(usn);
        return matcher.find();
    }

    public static boolean isValidDOB(String usn){
        Pattern VALID_DOB_REGEX = Pattern.compile("^(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d$");
        Matcher matcher = VALID_DOB_REGEX.matcher(usn);
        return matcher.find();
    }
}
