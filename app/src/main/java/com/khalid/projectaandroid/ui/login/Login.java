package com.khalid.projectaandroid.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.khalid.projectaandroid.ui.admin.AdminDashbordActivity;
import com.khalid.projectaandroid.R;
import com.khalid.projectaandroid.controllers.UserController;
import com.khalid.projectaandroid.db.dao.UserDAO;
import com.khalid.projectaandroid.ui.students.StudentDashbordActivity;
import com.khalid.projectaandroid.ui.teachers.TeacherDashbordActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.khalid.projectaandroid.Validation.CheckEmail;
import static com.khalid.projectaandroid.Validation.CheckPassword;

public class Login extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        Button login_btn = (Button) findViewById(R.id.login_btn);
        TextInputEditText email_input = (TextInputEditText) findViewById(R.id.input_email);
        TextInputEditText password_input = (TextInputEditText) findViewById(R.id.input_password);
        login_btn.setOnClickListener(v -> {
                    if (CheckEmail(email_input) && CheckPassword(password_input)) {
                        fAuth.signInWithEmailAndPassword(email_input.getText().toString(), password_input.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = fAuth.getCurrentUser();
                                UserController userController = new UserController(new UserDAO());
                                Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_LONG).show();
                                checkUserAccessLevel(user.getUid());

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed login", Toast.LENGTH_LONG).show();

                            }
                        });
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


    public void checkUserAccessLevel(String uid) {
        UserController userController = new UserController(new UserDAO());
        DocumentReference documentReference = userController.getUserById(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.getString("role").equals("admin")) {
                    Toast.makeText(getApplicationContext(), "hello admin", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AdminDashbordActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("role").equals("teacher")) {
                    Toast.makeText(getApplicationContext(), "hello teacher", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), TeacherDashbordActivity.class));
                    finish();

                }
                if (documentSnapshot.getString("role").equals("student")) {
                    Toast.makeText(getApplicationContext(), "hello student", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), StudentDashbordActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (FirebaseAuth.getInstance().getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//    }
}
