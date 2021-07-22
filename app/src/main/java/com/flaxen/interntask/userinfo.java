package com.flaxen.interntask;

public class userinfo {

    private String name;
    private String contactno;
    private String emailid;
    private String password;

    public userinfo(){}

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getContactno() {return contactno; }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
