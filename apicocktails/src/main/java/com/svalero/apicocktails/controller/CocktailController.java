package com.svalero.apicocktails.controller;

@RestController
public  class CocktailController {

    @Autowired
    private CocktailService cocktailService;
    private final Logger logger = LoggerFactory.getLogger(CocktailController.class);

    @GetMapping("/cocktails")
    public ResponseEntity<List<CocktailOutDto>> getAll(@RequestParam(value = "name", defaulyValue ="") String name,
                                                       @RequestParam(value = "category", defaultValue = "")String category){
        logger.info("BEGIN getAll");
        List<CocktailOutDto> cocktails = cocktailService.getAll(name, category);
        logger.info("END getAll");
        return  new ResponseEntity<>(cocktails,HttpStastus.OK);
    }

    @GetMapping("/cocktails/{cocktailId}")
    public ResponseEntity<Cocktail> getCocktail(@PathVariable long cocktailId) throws CocktailNotFoundException {
        logger.info("BEGIN getCocktail");
        Cocktail cocktail = cocktailService.get(cocktailId);
        logger.info("END getCocktail");
        return  new ResponseEntity<>(cocktail, httpStatus.OK);
    }

    @PostMapping("/users/{userId}/cocktails")
    public ResponseEntity<CocktailOutDto> addCocktail(@PathVariable long userId, @Valid @RequestBody CocktailRegistrationDto cocktail) throws UserNotFoundException {
        logger.info("BEGIN addCocktail");
        CocktailOutDto newCocktail = cocktailService.add(userId, cocktail);
        logger.info("END addCocktail");
        return new ResponseEntity<>(newCoctail, HttpStatus.CREATED);
    }

    @PutMapping("/cocktails/{cocktailId}")
    public ResponseEntity<CocktailOutDto> modifyCocktail(@PathVariable long cocktailId, @Valid @RequestBody CocktailsInDto cocktail) throws CocktailNotFoundException {
        logger.info("BEGIN modifyCocktail");
        CocktailOutDto modifiedCocktail = cocktailService.modify(cocktailId, cocktail);
        logger.info("END modifyCocktail");
        return new ResponseEntity<>(modifiedCocktail, HttpStatus.OK);
    }

    @DeleteMapping("/cocktails/{cocktailId}")
    public ResponseEntity<Void> removeCocktail(@PathVariable long cocktailId) throws CocktailNotFoundException {
        logger.info("BEGIN removeCocktail");
        cocktailService.remove(cocktailId);
        logger.info("END removeCocktail");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CocktailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCocktailNotFoundException(CocktailNotFoundException exception) {
        ErrorResponse error = ErrorResponse.generalError(404, exception.getMessage());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse error = ErrorResponse.generalError(404, exception.getMessage());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        logger.error(exception.getMessage(), exception);

        return new ResponseEntity<>(ErrorResponse.validationError(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}