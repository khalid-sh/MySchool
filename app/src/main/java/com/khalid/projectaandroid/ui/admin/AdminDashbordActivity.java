package com.khalid.projectaandroid.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.khalid.projectaandroid.R;
import com.khalid.projectaandroid.controllers.UserController;
import com.khalid.projectaandroid.db.dao.UserDAO;
import com.khalid.projectaandroid.ui.login.Login;

public class AdminDashbordActivity extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_admin);
        CardView cardViewLoguot = (CardView) findViewById(R.id.logout);
        cardViewLoguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserController userController = new UserController(new UserDAO());
                userController.userLogout();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        CardView cardViewTeachers = (CardView) findViewById(R.id.teachers);
        cardViewTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListTeachersActivity.class));
            }
        });

        CardView cardViewStudents = (CardView) findViewById(R.id.students);
        cardViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListStudentsActivity.class));
            }
        });
        CardView cardViewClasses = (CardView) findViewById(R.id.classes);
        cardViewClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListClassesActivity.class));
            }
        });
        CardView cardViewSetting = (CardView) findViewById(R.id.setting);
        FirebaseUser user = fAuth.getCurrentUser();
        name = (TextView)findViewById(R.id.dashboard_adminName);
        name.setText(user.getEmail());

    }
}