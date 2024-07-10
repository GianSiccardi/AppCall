package com.example.chat_web.services;

import com.example.chat_web.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class UserServices {

    private static final List<User> USER_LIST = new ArrayList<>();

    public void register(User user) {
        user.setStatus("online");
        System.out.println("Se guardo en la lsita");
        USER_LIST.add(user);
    }

    public User login(User user) {
        System.out.println("Intentando login para: " + user.getEmail());
        System.out.println("LISRTA DE USUARIOS "+ USER_LIST);
        var userIndex = IntStream.range(0, USER_LIST.size())
                .filter(i -> USER_LIST.get(i).getEmail().equals(user.getEmail()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("USUARIO NO ECONTRANDO"));
        var userConnect = USER_LIST.get(userIndex);
        if (!userConnect.getPassword().equals(user.getPassword())) {
            throw new RuntimeException(("Clave incorrecta"));
        }
        userConnect.setStatus("online");
        return userConnect;
    }

    public void logout(String email){
        var userIndex = IntStream.range(0, USER_LIST.size())
                .filter(i -> USER_LIST.get(i).getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new RuntimeException("USUARIO NO ECONTRANDO"));

        USER_LIST.get(userIndex).setStatus("offline");
    }

   public List<User>findAll(){
        return  USER_LIST;
   }

}

