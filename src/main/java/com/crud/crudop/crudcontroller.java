package com.crud.crudop;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class crudcontroller {   
    
    @Autowired
    private UserRepository userRepository;

    //test
    @GetMapping("/test")
    public String test(){
        return "This Port works !";
    }

    @PostMapping
    public User creatUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //to get all the users.
    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    
    //to get the user by ID.
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }   

    //to  update the user by ID.
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: "+id));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    //to delete the user by a given id.
    @DeleteMapping()
    public String deleteUser(@RequestParam Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User deleted successfully";
        }else{
            return "User not found with id: "+id;
        }
    }
}