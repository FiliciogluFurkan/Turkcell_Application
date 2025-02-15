package com.example.shakewin_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class ShakeWinEntity {

    @Id
    @SequenceGenerator(name = "shakewin_id", allocationSize = 1)
    @GeneratedValue(generator = "shakewin_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    @NotNull
    private Integer amount;

    @NotNull
    @PositiveOrZero
    private Integer durationTime;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        GB, MINUTES, SMS
    }


}
