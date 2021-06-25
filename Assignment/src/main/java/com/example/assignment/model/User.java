package com.example.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "full_name")
    private String name;
    @Column(name = "mobile_number")
    private Long mobilenum;
    @Column(name = "state")
    private String state;

    public Long getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(Long mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
    @Column(name = "skills")
    private String skills;

    @Column(name = "gender")
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(long id, String emailId, String name, Long mobilenum, String state, String skills, String gender) {
        this.id = id;
        this.emailId = emailId;
        this.name = name;
        this.mobilenum = mobilenum;
        this.state = state;
        this.skills = skills;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {

    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}