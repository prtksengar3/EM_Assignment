package com.example.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Email
    private String emailId;

    @Column(name = "full_name",nullable = false)
    @Size(min = 2,message = "Name should have atleast 2 characters")
    @NotBlank
    private String name;

    @Column(name = "mobile_number")
    @NotBlank
    private Long mobilenum;

    @Column(name = "state")
    private String state;

    @Column(name = "skills")
    private String skills;

    @Column(name = "gender")
    @NotBlank
    private String gender;

    @Column(nullable = true, length = 64)
    private String photos;


}