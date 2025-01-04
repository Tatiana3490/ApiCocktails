package com.svalero.apicocktails.domain.dto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailRegistrationDto {
    @NotNull(message = "El campo name es obligatorio")
    private String name;
    @NotNull
    private String category;
    private LocalDate releaseDate;
    @Min(value = 0)
    private double alcoholicContent;
    private double latitude;
    private double longitude;
}