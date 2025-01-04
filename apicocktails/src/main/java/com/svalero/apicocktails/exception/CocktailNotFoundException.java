package com.svalero.apicocktails.exception;

public class CocktailNotFoundException extends Exception {

    public CocktailNotFoundException(){
        super("The cocktail does not exist");
    }

    public CocktailNotFoundException(String message){
        super(message);
    }
}