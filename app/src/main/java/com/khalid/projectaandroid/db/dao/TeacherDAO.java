package com.khalid.projectaandroid.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.db.models.Teacher;
import com.khalid.projectaandroid.db.models.User;

public class TeacherDAO {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public  void insertTeacher(Teacher teacher, FirebaseUser currentUser){
        DocumentReference documentReference = fStore.collection("Teachers").document(currentUser.getUid());
        documentReference.set(teacher);
    }
    public  DocumentReference getUserById(String uid){
        DocumentReference documentReference = fStore.collection("Teachers").document(uid);
        return documentReference;
    }
    public Query getAllTeachers(){
        Query query = fStore.collection("Teachers");
        return query;
    }
    public  Query searchTeacherByName(String name){
        Query query = fStore.collection("Teachers").orderBy("name").whereEqualTo("name",name);
        return query;
    }
    public void userLogout(){
        FirebaseAuth.getInstance().signOut();
    }
}
