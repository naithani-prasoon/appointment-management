package com.example.userservice.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("user")
//@Entity
public class User {
    //todo maybe implement DTO design
    //todo maybe use UUID

    @Id
    private String id;

    @NotNull
    @NotBlank(message = "please enter your first name")
    private String firstName;

    @NotNull
    @NotBlank(message = "please enter your last name")
    private String lastName;

    @NotNull
    private GenderEnum gender;

    @PositiveOrZero
    private Integer age;

    private String emailAdress;
    private String phoneNumber;

}
