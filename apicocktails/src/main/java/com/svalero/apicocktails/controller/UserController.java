package com.svalero.apicocktails.controller;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {

        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);

    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.add(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}