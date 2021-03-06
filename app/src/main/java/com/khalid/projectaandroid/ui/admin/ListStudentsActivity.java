package com.khalid.projectaandroid.ui.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.R;
import com.khalid.projectaandroid.controllers.StudentController;
import com.khalid.projectaandroid.db.dao.StudentDAO;
import com.khalid.projectaandroid.db.models.Student;
import com.khalid.projectaandroid.db.models.User;
import com.khalid.projectaandroid.holders.StudentsViewHolder;
import com.khalid.projectaandroid.holders.UsersViewHolder;


public class ListStudentsActivity extends AppCompatActivity {
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private EditText textSerched;
    private ImageButton search_btn;
    private RecyclerView myFireStoreList;
    private FirestoreRecyclerAdapter adapter;
    StudentController studentController = new StudentController(new StudentDAO());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        myFireStoreList = (RecyclerView)findViewById(R.id.myrecycleView);

        Query query = studentController.getAllStudents();
        FirestoreRecyclerOptions<Student> options = new FirestoreRecyclerOptions.Builder<Student>()
                .setQuery(query, Student.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Student, StudentsViewHolder>(options) {
            @NonNull
            @Override
            public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_student, parent, false);
                return new StudentsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull StudentsViewHolder holder, int position, @NonNull Student model) {
                holder.nom.setText(model.getfName());
                holder.mycard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), StudentProfileActivity.class));
                    }
                });
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog diaBox = AskOption();
                        diaBox.show();
                        //Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_LONG).show();
                        deleteStudent(model);
                    }
                });
            }
        };


        textSerched = (EditText) findViewById(R.id.search_field);
        search_btn = (ImageButton) findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = textSerched.getText().toString();
                if (!searchText.trim().equals("")) {
                    Query query = studentController.searchStudentByName(searchText);
                    FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                            .setQuery(query, User.class)
                            .build();

                    adapter.updateOptions(options);
                } else {
                    Toast.makeText(getApplicationContext(), "requete vide", Toast.LENGTH_LONG).show();
                }
            }

        });
        myFireStoreList.setHasFixedSize(true);
        myFireStoreList.setLayoutManager(new LinearLayoutManager(this));
        myFireStoreList.setAdapter(adapter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    private void deleteStudent(Student student) {

        fStore.collection("Students")
                        .document(student.getfName())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Student has been deleted from Databse.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),AdminDashbordActivity.class);
                            startActivity(i);
                        } else {

                            Toast.makeText(getApplicationContext(), "Fail to delete the student. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }
}
