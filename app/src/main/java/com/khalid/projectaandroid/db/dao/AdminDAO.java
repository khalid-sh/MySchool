package com.khalid.projectaandroid.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.khalid.projectaandroid.db.models.Admin;

public class AdminDAO {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public void insertAdmin(Admin admin, FirebaseUser currentUser) {
        DocumentReference documentReference = fStore.collection("Admins").document(currentUser.getUid());
        documentReference.set(admin);
    }

    public DocumentReference getAdminById(String uid) {
        DocumentReference documentReference = fStore.collection("Admins").document(uid);
        return documentReference;
    }
    public void userLogout(){
        FirebaseAuth.getInstance().signOut();
    }
}
