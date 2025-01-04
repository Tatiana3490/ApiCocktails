package com.svalero.apicocktails.domain.dto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailInDto {
    @NotNull(message = "El campo name es obligatorio")
    private String name;
    @NotNull
    private String category;
    private LocalDate releaseDate;
    private LocalDate registrationDate;
    @Min(value = 0)
    private double alcoholic_content;
}