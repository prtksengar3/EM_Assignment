package com.example.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "full_name",nullable = false)
    private String name;

    @Column(name = "mobile_number")
    private Long mobilenum;

    @Column(name = "state")
    private String state;

    @Column(name = "skills")
    private String skills;

    @Column(name = "gender")
    private String gender;

    @Column(name = "photos",nullable = true, length = 64)
    private String photos;


}