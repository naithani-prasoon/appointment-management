package com.example.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    //This might interfere with tests
    //@Null
    private String id;

    @NotBlank(message = "first name cannot be blank")
    private String firstName;

    @NotBlank(message = "last name cannot be blank")
    private String lastName;

    private GenderEnum gender;

    @PositiveOrZero
    private Integer age;

    private String emailAddress;
    private String phoneNumber;

}
