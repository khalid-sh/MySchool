package com.khalid.projectaandroid.db.models;

public class Teacher {
    private int id;
    private String  fName;
    private String lName;
    private String DOB;
    private String  phone;
    private  String Address;
    private  String gender;
    private String DateOfJoin;
    public Teacher() {
    }

    public Teacher(int id, String fName, String lName, String DOB, String phone, String address, String gender, String dateOfJoin) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.phone = phone;
        Address = address;
        this.gender = gender;
        DateOfJoin = dateOfJoin;
    }

    public Teacher(String fName, String lName, String DOB, String phone, String address, String gender, String dateOfJoin) {
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.phone = phone;
        Address = address;
        this.gender = gender;
        DateOfJoin = dateOfJoin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfJoin() {
        return DateOfJoin;
    }

    public void setDateOfJoin(String dateOfJoin) {
        DateOfJoin = dateOfJoin;
    }


}
