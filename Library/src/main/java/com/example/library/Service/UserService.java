package com.example.library.Service;

import com.example.library.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean removeUser(String id) {
        for (User user : users) {
            if (user.getID().equals(id)) {
                users.remove(user);
                return true;}
        }
        return false;
    }

    public boolean updateUser(String id,User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }
    //Create an endpoint that takes a  balance then returns all users have this balance or above .
    public ArrayList getUsersabovebalance(int balance){
        ArrayList<User> usersabovebalance = new ArrayList<>();
        for (User user : users) {
            if(user.getBalance()>=balance){usersabovebalance.add(user);}
        }
        return usersabovebalance;
    }
    //Create an endpoint that takes an age then return all User who have this age  or above .
    public ArrayList getUsersaboveAge(int age){
        ArrayList<User> usersaboveAge = new ArrayList<>();
        for (User user : users) {
            if(user.getAge()>=age){usersaboveAge.add(user);}
        }
        return usersaboveAge;
    }

    public boolean isLibrarian(String id) {
        for (User user : users) {
            if(user.getID().equals(id)){
                if (user.getRole().equals("libraian")) {
                    return true;
                }
            }
        }
        return false;
    }
}
