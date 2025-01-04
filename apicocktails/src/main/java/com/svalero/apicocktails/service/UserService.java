package com.svalero.apicocktails.service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    public User add(User user){
        return userRepository.save(user);
    }
}