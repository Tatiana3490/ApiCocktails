package com.svalero.apicocktails.repository;

@Repository
public interface CocktailRepository extends CrudRepository<Cocktail, Long> {

    List<Cocktail> findAll();
    List<Cocktail> findByName(String name);
    List<Cocktail> findByCategory(String category);
    List<Cocktail> findByNameAndCategory(String name, String category);
    List<Cocktail> findByUser(User user);

    // Ejemplo con JPQL
    @Query("select c FROM Cocktail c WHERE c.user.email = :email")
    List<Cocktail> getAllCocktailsByUserEmail(String email);

    // Ejemplo con SQL nativo
    @Query(value = "SELECT c.* FROM cocktails c, users u WHERE c.id_user = u.id", nativeQuery = true)
    List<Cocktail> getCocktailsByUserEmail(String email);
}