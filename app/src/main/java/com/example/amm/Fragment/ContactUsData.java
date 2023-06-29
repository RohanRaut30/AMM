package com.example.amm.Fragment;

public class ContactUsData {

    private String Name;
    private String Email;
    private String Mobile;
    private String Subject;
    private String Comments;

    public ContactUsData(String name, String email, String mobile, String subject, String comments) {
        Name = name;
        Email = email;
        Mobile = mobile;
        Subject = subject;
        Comments = comments;
    }

    public ContactUsData() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}
