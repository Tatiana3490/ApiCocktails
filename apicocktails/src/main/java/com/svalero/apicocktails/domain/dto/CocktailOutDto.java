package com.svalero.apicocktails.domain.dto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailOutDto {
    private long id;
    private String name;
    private String category;
    private long userId;
    private double latitude;
    private double longitude;
}