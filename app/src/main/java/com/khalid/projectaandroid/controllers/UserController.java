package com.khalid.projectaandroid.controllers;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.khalid.projectaandroid.db.dao.UserDAO;
import com.khalid.projectaandroid.db.models.User;

public class UserController {
    UserDAO userDAO;
    public UserController(UserDAO userDAO){
        this.userDAO=userDAO;
    }
    public  void insertUser(User user ,FirebaseUser currentUser){
        this.userDAO.insertUser(user,currentUser);
    }
    public  DocumentReference getUserById(String uid){
        DocumentReference documentReference = this.userDAO.getUserById(uid);
        return documentReference;
    }
    public void userLogout(){
        this.userDAO.userLogout();
    }
}
