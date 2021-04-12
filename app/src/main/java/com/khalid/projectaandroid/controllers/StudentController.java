package com.khalid.projectaandroid.controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.db.dao.StudentDAO;
import com.khalid.projectaandroid.db.dao.UserDAO;
import com.khalid.projectaandroid.db.models.Student;

public class StudentController {

    StudentDAO studentDAO;
    public StudentController(StudentDAO studentDAO){
        this.studentDAO=studentDAO;
    }
    public  void insertStudent(Student student, FirebaseUser currentUser){
        this.studentDAO.insertStudent(student,currentUser);
    }
    public Query getAllStudents(){
        return this.studentDAO.getAllStudents();
    }
    public Query searchStudentByName(String name){
       return this.studentDAO.searchStudentByName(name);
    }
}
