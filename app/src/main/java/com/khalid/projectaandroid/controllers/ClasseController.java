package com.khalid.projectaandroid.controllers;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.db.dao.ClasseDAO;
import com.khalid.projectaandroid.db.models.Classe;

public class ClasseController {
    ClasseDAO classeDAO;
    public ClasseController(ClasseDAO classeDAO){
        this.classeDAO = classeDAO;
    }
    public  void insertClasse(Classe classe , FirebaseUser currentUser){
        this.classeDAO.insertClasse(classe,currentUser);
    }
    public Query getAllClasses(){
        return this.classeDAO.getAllClasses();
    }
    public Query searchClasseByName(String name){
        return this.classeDAO.searchClasseByName(name);
    }
}
