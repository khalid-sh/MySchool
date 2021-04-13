package com.khalid.projectaandroid.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.db.models.Student;
import com.khalid.projectaandroid.db.models.User;

public class StudentDAO {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public  void insertStudent(Student student, FirebaseUser currentUser){
        DocumentReference documentReference = fStore.collection("Students").document(currentUser.getUid());
        documentReference.set(student);
    }
    public  DocumentReference getUserById(String uid){
        DocumentReference documentReference = fStore.collection("Students").document(uid);
        return documentReference;
    }
    public Query getAllStudents(){
        Query query = fStore.collection("Students");
        return query;
    }
    public  Query searchStudentByName(String name){
        Query query = fStore.collection("Students").orderBy("name").whereEqualTo("name",name);
            return query;
    }
    public void userLogout(){
        FirebaseAuth.getInstance().signOut();
    }
}
