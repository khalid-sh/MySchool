package com.khalid.projectaandroid.ui.admin;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.R;
import com.khalid.projectaandroid.controllers.StudentController;
import com.khalid.projectaandroid.controllers.TeacherController;
import com.khalid.projectaandroid.db.dao.StudentDAO;
import com.khalid.projectaandroid.db.dao.TeacherDAO;
import com.khalid.projectaandroid.db.models.Teacher;
import com.khalid.projectaandroid.db.models.User;
import com.khalid.projectaandroid.holders.TeachersViewHolder;
import com.khalid.projectaandroid.holders.UsersViewHolder;

public class ListTeachersActivity extends AppCompatActivity {
    private EditText textSerched;
    private ImageButton search_btn;
    private RecyclerView myFireStoreList;
    private FirestoreRecyclerAdapter adapter;
    TeacherController teacherController = new TeacherController(new TeacherDAO());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        myFireStoreList = (RecyclerView)findViewById(R.id.myrecycleView);
        Query query = teacherController.getAllTeachers();
        FirestoreRecyclerOptions<Teacher> options = new FirestoreRecyclerOptions.Builder<Teacher>()
                .setQuery(query, Teacher.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Teacher, TeachersViewHolder>(options) {
            @NonNull
            @Override
            public TeachersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_teacher, parent, false);
                return new TeachersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull TeachersViewHolder holder, int position, @NonNull Teacher model) {
                holder.nom.setText(model.getlName());
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
                        Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_LONG).show();
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
                    Query query = teacherController.searchTeacherByName(searchText);
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
}
