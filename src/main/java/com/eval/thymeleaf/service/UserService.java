package com.eval.thymeleaf.service;

import com.eval.thymeleaf.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private long currentUserId = 1;
    private final List<User> users = new ArrayList<>();


    //Instanciation d'utilisateur dans la memoire

        {
            users.add(new User(1L, "alice"));
            users.add(new User(2L, "bob"));
            users.add(new User(3L, "charlie"));
        }


    /**Méthode qui permet d'ajouter un user*/
    public User addUser(String username) {
        User user = new User();
        user.setId(currentUserId++);
        user.setUsername(username);
        users.add(user);
        return user;
    }

    /**Méthode qui retourne tous les users*/
    public List<User> getAllUsers() {
        return users;
    }

    /**Méthode qui recupère un user via son id*/
    public User getUserById(long id) {
       for (User user : users) {
           if (user.getId() == id) {
               return user;
           }
       }
       return null;
    }

    /**Méthodde qui récupère un user via son usernam*/
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
