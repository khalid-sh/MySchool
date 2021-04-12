package com.khalid.projectaandroid.ui.students;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.khalid.projectaandroid.R;
import com.khalid.projectaandroid.controllers.StudentController;
import com.khalid.projectaandroid.controllers.UserController;
import com.khalid.projectaandroid.db.dao.StudentDAO;
import com.khalid.projectaandroid.db.dao.UserDAO;
import com.khalid.projectaandroid.ui.admin.ListStudentsActivity;
import com.khalid.projectaandroid.ui.login.Login;

public class StudentDashbordActivity extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    FirebaseFirestore  fStore = FirebaseFirestore.getInstance();
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_student);
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

        CardView cardViewStudents = (CardView) findViewById(R.id.exams);
        cardViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListStudentsActivity.class));
            }
        });
        CardView cardViewSubjects = (CardView) findViewById(R.id.homeworks);
        CardView cardViewClasses = (CardView) findViewById(R.id.classes);
        CardView cardViewSetting = (CardView) findViewById(R.id.setting);
        CardView cardViewSchedule = (CardView) findViewById(R.id.schedule);
        StudentController studentController = new StudentController(new StudentDAO());
        FirebaseUser user = fAuth.getCurrentUser();
        name = (TextView)findViewById(R.id.dashboard_adminName);
            name.setText(user.getEmail());



    }
}