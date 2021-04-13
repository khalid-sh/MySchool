package com.khalid.projectaandroid.db.models;

public class Classe {
    private int  id_classe;
    private String classeName;
    private String description;
    private  int id_teacher;

    public Classe() {
    }

    public Classe(int id_classe, String classeName, String description, int id_teacher) {
        this.id_classe = id_classe;
        this.classeName = classeName;
        this.description = description;
        this.id_teacher = id_teacher;
    }

    public Classe(int id_classe, String classeName, String description) {
        this.id_classe = id_classe;
        this.classeName = classeName;
        this.description = description;
    }

    public Classe(String classeName, String description) {
        this.classeName = classeName;
        this.description = description;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public String getClasseName() {
        return classeName;
    }

    public void setClasseName(String classeName) {
        this.classeName = classeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }
}
