package com.example.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;

    @NotBlank
    @Email
    private String emailId;

    @Size(min = 2,message = "Name should have atleast 2 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank
    private Long mobilenum;

    @Column(name = "state")
    @StateValidation
    private String state;

    @Column(name = "skills")
    private String skills;

    @NotBlank
    private String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String photos;
}
