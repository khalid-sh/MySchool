package com.khalid.projectaandroid.controllers;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Query;
import com.khalid.projectaandroid.db.dao.TeacherDAO;
import com.khalid.projectaandroid.db.models.Teacher;

public class TeacherController {
    TeacherDAO teacherDAO;
    public TeacherController(TeacherDAO teacherDAO){
        this. teacherDAO=teacherDAO;
    }
    public  void insertTeacher(Teacher teacher, FirebaseUser currentUser){
        this.teacherDAO.insertTeacher(teacher,currentUser);
    }
    public Query getAllTeachers(){
        return this.teacherDAO.getAllTeachers();
    }
    public Query searchTeacherByName(String name){
        return this.teacherDAO.searchTeacherByName(name);
    }
}
