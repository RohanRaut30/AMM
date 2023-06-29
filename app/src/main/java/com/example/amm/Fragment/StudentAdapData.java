package com.example.amm.Fragment;

public class StudentAdapData {

    private String fullName;
    private String address;
    private String contactNumber;
    private int age;
    private float tenthMarks;
    private float twelfthMarks;
    private float graduationMarks;
    private float amountForEducation;

    public StudentAdapData(String fullName, String address, String contactNumber, int age, float tenthMarks, float twelfthMarks, float graduationMarks, float amountForEducation, String comments) {
        this.fullName = fullName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.age = age;
        this.tenthMarks = tenthMarks;
        this.twelfthMarks = twelfthMarks;
        this.graduationMarks = graduationMarks;
        this.amountForEducation = amountForEducation;
        this.comments = comments;
    }

    private String comments;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getTenthMarks() {
        return tenthMarks;
    }

    public void setTenthMarks(float tenthMarks) {
        this.tenthMarks = tenthMarks;
    }

    public float getTwelfthMarks() {
        return twelfthMarks;
    }

    public void setTwelfthMarks(float twelfthMarks) {
        this.twelfthMarks = twelfthMarks;
    }

    public float getGraduationMarks() {
        return graduationMarks;
    }

    public void setGraduationMarks(float graduationMarks) {
        this.graduationMarks = graduationMarks;
    }

    public float getAmountForEducation() {
        return amountForEducation;
    }

    public void setAmountForEducation(float amountForEducation) {
        this.amountForEducation = amountForEducation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public StudentAdapData() {
        this.fullName = fullName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.age = age;
        this.tenthMarks = tenthMarks;
        this.twelfthMarks = twelfthMarks;
        this.graduationMarks = graduationMarks;
        this.amountForEducation = amountForEducation;
        this.comments = comments;


    }
}
