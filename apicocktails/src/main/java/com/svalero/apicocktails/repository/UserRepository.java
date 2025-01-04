package com.svalero.apicocktails.repository;

@Repository
public interface UserRepository  extends CrudRepository<User,Long> {

    List<User> findAll();
}