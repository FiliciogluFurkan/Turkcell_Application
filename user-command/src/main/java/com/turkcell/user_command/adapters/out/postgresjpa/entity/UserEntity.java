package com.turkcell.user_command.adapters.out.postgresjpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_sequence",allocationSize = 1)
    @GeneratedValue(generator = "user_sequence",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 3,max = 20,message = "name should be minimum 3 character and maximum 20 character")
    @NotBlank
    private String name;

    @Size(min = 3,max = 20,message = "surname should be minimum 3 character and maximum 20 character")
    @NotBlank
    private String surname;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private Integer age;  // 'age' 18 ile 100 arasında olmalı

    @Pattern(regexp = "\\d{11}", message = "Identity number's length must be 11 digits")
    private String identityNumber;


    @NotBlank
    private String mail;

    @NotBlank
    @Pattern(regexp = "05\\d{9}", message = "Phone number must start with 05 and be followed by 9 digits")
    private String phoneNumber;


    private Boolean status;
    private Long packageId;
    private List<Long> extraPackageIds;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private Long shakeWinId;
    private Boolean isShakeWinActive;


}
