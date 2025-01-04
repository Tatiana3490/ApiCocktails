package com.svalero.apicocktails.domain.dto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutDto {
    private long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;

}