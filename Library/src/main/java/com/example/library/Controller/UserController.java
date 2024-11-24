package com.example.library.Controller;

import com.example.library.ApiResponse.ApiResponse;
import com.example.library.Model.Book;
import com.example.library.Model.User;
import com.example.library.Service.BookService;
import com.example.library.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/library-user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("user added"));
    }

    @PutMapping("/update/{ID}")
    public ResponseEntity updateUser(@PathVariable String ID,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = userService.updateUser(ID,user);
        if(isUpdated){return ResponseEntity.status(201).body(new ApiResponse("user updated"));
        }else {return ResponseEntity.status(400).body(new ApiResponse("user not updated"));}
    }

    @DeleteMapping("/delete/{ID}")
    public ResponseEntity deleteUser(@PathVariable String ID){

        boolean isDeleted = userService.removeUser(ID);
        if(isDeleted){return ResponseEntity.status(201).body(new ApiResponse("user deleted"));
        }else {return ResponseEntity.status(400).body(new ApiResponse("user not deleted"));}
    }

    @GetMapping("/getUsersabovebalance/{balance}")
    public ResponseEntity getUsersabovebalance(@PathVariable int balance){
        ArrayList<User> users = userService.getUsersabovebalance(balance);
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/getUsersaboveAge/{age}")
    public ResponseEntity getUsersaboveAge(@PathVariable int age){
        ArrayList<User> users = userService.getUsersaboveAge(age);
        return ResponseEntity.status(200).body(users);
    }



}
