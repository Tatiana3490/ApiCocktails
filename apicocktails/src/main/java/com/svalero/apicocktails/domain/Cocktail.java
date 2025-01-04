package com.svalero.apicocktails.domain;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Cocktail")
@Table(name="cocktails")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String category;
    @Column(name="release_date")
    private LocalDate releaseDate;
    @Column(name="registration_date")
    private LocalDate registrationDate;
    @Column(name="alcoholic_content")
    private double alcoholicContent;
    @ColumnDefault("0")
    private double latitude;
    @ColumnDefault("0")
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}