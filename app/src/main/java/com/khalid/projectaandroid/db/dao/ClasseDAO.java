package com.khalid.projectaandroid.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.db.models.Classe;

public class ClasseDAO {

    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public void insertClasse(Classe classe, FirebaseUser currentUser) {
        DocumentReference documentReference = fStore.collection("Classes").document(classe.getClasseName());
        documentReference.set(classe);
    }
    public Query getAllClasses(){
        Query query = fStore.collection("Classes");
        return query;
    }
    public  Query searchClasseByName(String name){
        Query query = fStore.collection("Classes").orderBy("name").whereEqualTo("name",name);
        return query;
    }
    public DocumentReference getClassById(String uid) {
        DocumentReference documentReference = fStore.collection("Classes").document(uid);
        return documentReference;
    }
    public void userLogout(){
        FirebaseAuth.getInstance().signOut();
    }
}
