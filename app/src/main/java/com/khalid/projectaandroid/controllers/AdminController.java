package com.khalid.projectaandroid.controllers;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.khalid.projectaandroid.db.dao.AdminDAO;
import com.khalid.projectaandroid.db.models.Admin;

public class AdminController {
    AdminDAO adminDAO;
    public AdminController(AdminDAO adminDAO){
        this.adminDAO=adminDAO;
    }
    public  void insertAdmin(Admin admin, FirebaseUser currentUser){
        this.adminDAO.insertAdmin(admin,currentUser);
    }
    public DocumentReference getUserById(String uid){
        DocumentReference documentReference = this.adminDAO.getAdminById(uid);
        return documentReference;
    }

}
