package com.khalid.projectaandroid.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.khalid.projectaandroid.db.models.User;

public class UserDAO {
    FirebaseAuth  fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public  void insertUser(User user, FirebaseUser currentUser){
        DocumentReference documentReference = fStore.collection("Users").document(currentUser.getUid());
        documentReference.set(user);
    }
    public  DocumentReference getUserById(String uid){
        DocumentReference documentReference = fStore.collection("Users").document(uid);
        return documentReference;
    }
    public void userLogout(){
        FirebaseAuth.getInstance().signOut();
    }
}
