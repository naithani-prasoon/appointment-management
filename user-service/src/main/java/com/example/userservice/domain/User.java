package com.example.userservice.domain;

import com.example.userservice.model.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("user")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;

    @PositiveOrZero
    private Integer age;

    @Indexed(unique = true)
    private String emailAddress;
    private String phoneNumber;
    private Boolean isDeleted = Boolean.FALSE;
}
