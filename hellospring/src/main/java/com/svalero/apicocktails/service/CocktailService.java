package com.svalero.apicocktails.service;

@Service
public  class CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CocktailOutDto> getAll(String cocktail_name, String category) {
        List<Cocktail> cocktailList;

        if (cocktail_name.isEmpty() && category.isEmpty()) {
            cocktailList = cocktailRepository.findAll();
        } else if (cocktail_name.isEmpty()) {
            cocktailList = cocktailRepository.findByCocktail_name(cocktail_name);
        } else if (cocktail_name.isEmpty()) {
            cocktailList = cocktailRepository.findByCategory(category);
        } else {
            cocktailList = cocktailRepository.findByCocktail_nameAndCategory(cocktail_name, category);
        }

        return modelMapper.map(cocktailList, new TypeToken<List<CocktailOutDto>>() {}.getType());
    }

    public Cocktail get(long id) throws CocktailNotFoundException {
        return cocktailRepository.findById(id)
                .orElseThrow(CocktailNotFoundException::new);
    }

    public CocktailOutDto add(long userId, CocktailRegistrationDto cocktailInDto) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Cocktail cocktail = modelMapper.map(cocktailInDto, Cocktail.class);
        cocktail.setRegistrationDate(LocalDate.now());
        cocktail.setUser(user);
        Cocktail newCocktail = cocktailRepository.save(cocktail);

        return modelMapper.map(newCocktail, CocktailOutDto.class);
    }

    public CocktailOutDto modify(long cocktailId, CocktailInDto cocktailInDto) throws CocktailNotFoundException {
        Cocktail cocktail = cocktailRepository.findById(cocktailId)
                .orElseThrow(CocktailNotFoundException::new);

        modelMapper.map(cocktailInDto, cocktail);
        cocktailRepository.save(cocktail);

        return modelMapper.map(car, CarOutDto.class);
    }

    public void remove(long id) throws CocktailNotFoundException{
        cocktailRepository.findById(id)
                .orElseThrow(CocktailNotFoundException::new);
        cocktailRepository.deleteById(id);
    }
}