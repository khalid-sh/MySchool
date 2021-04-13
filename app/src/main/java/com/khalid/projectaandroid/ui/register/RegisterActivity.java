package com.khalid.projectaandroid.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.khalid.projectaandroid.R;
import com.khalid.projectaandroid.controllers.AdminController;
import com.khalid.projectaandroid.controllers.StudentController;
import com.khalid.projectaandroid.controllers.TeacherController;
import com.khalid.projectaandroid.controllers.UserController;
import com.khalid.projectaandroid.db.dao.AdminDAO;
import com.khalid.projectaandroid.db.dao.StudentDAO;
import com.khalid.projectaandroid.db.dao.TeacherDAO;
import com.khalid.projectaandroid.db.dao.UserDAO;
import com.khalid.projectaandroid.db.models.Admin;
import com.khalid.projectaandroid.db.models.Student;
import com.khalid.projectaandroid.db.models.Teacher;
import com.khalid.projectaandroid.db.models.User;
import com.khalid.projectaandroid.ui.login.Login;

import static com.khalid.projectaandroid.Validation.CheckEmail;
import static com.khalid.projectaandroid.Validation.CheckPassword;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        Button register_btn = (Button) findViewById(R.id.register_btn);
        EditText username_input = (EditText) findViewById(R.id.input_username);
        EditText role_input = (EditText) findViewById(R.id.input_role);
        EditText email_input = (EditText) findViewById(R.id.input_email);
        EditText password_input = (EditText) findViewById(R.id.input_password);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = username_input.getText().toString();
                String email = email_input.getText().toString();
                String password = password_input.getText().toString();
                String role = role_input.getText().toString();
                if (CheckEmail(email_input) && CheckPassword(password_input) && !userName.isEmpty()) {

                    fAuth.createUserWithEmailAndPassword(email_input.getText().toString(), password_input.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                            User userObj = new User("admin@gmail.com", 1, "admin", "admin123", role);
                            UserController userController = new UserController(new UserDAO());
                            userController.insertUser(userObj, user);
                            if (role.equals("admin")) {
                                Admin admin = new Admin(1, "admin1", "admin1", "01/02/1999", "062948794287", "rabat", "homme", "2021");
                                AdminController adminController = new AdminController(new AdminDAO());
                                adminController.insertAdmin(admin, user);
                            }
                            if (role.equals("teacher")) {
                                Teacher teacher = new  Teacher(3, "teacher2", "teacher2", "01/02/1999", "062948794287", "rabat", "homme", "2021");
                                TeacherController teacherController = new  TeacherController(new TeacherDAO());
                                teacherController.insertTeacher(teacher, user);
                            }
                            if (role.equals("student")) {
                                Student student = new Student( "student1", "student1", "01/02/1999", "062948794287", "rabat", "homme", "2021");
                                StudentController studentController = new StudentController(new StudentDAO());
                                studentController.insertStudent(student, user);
                            }
//                            UserDAO userDAO = new UserDAO();
//                            userDAO.insertUser(userObj,user);
//                            DocumentReference documentReference = fStore.collection("Users").document(user.getUid());
//                            documentReference.set(userObj);
//                            Map<String, Object> userInfo = new HashMap<>();
//                            userInfo.put("userName", "khalid1");
//                            userInfo.put("email", "khalid1@gmail.com");
//                            userInfo.put("password", "khalid123");
//                            userInfo.put("Rolr_id", "2");
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed To Create Account", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

}
