package com.pucgoias.devmobileapps.trabalhofinal.services;

import com.pucgoias.devmobileapps.trabalhofinal.exceptions.ObjectNotFoundException;
import com.pucgoias.devmobileapps.trabalhofinal.models.User;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.UserRepository;
import com.pucgoias.devmobileapps.trabalhofinal.validations.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta classe acessa os métodos de acesso e manipulação dee banco de dados e realiza operações associadas.
 * Funciona como uma "ponte" entre as requisições realizadas nos endpoints na classe Controller e
 * os métodos de persistência.
 */

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
       return userRepository.findAll();
    }

    public User getUserByID(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    public boolean login(User user) {
        List<User> users = getAll();
        final Optional<User> firstUserFound = users.stream()
                                                .filter(u -> u.getLogin() == user.getLogin())
                                                .filter(u -> u.getPassword() == user.getPassword())
                                                .findFirst();

        if(!firstUserFound.equals(Optional.empty())) {
            if (Validations.validateEmail(firstUserFound.get().getLogin())
                    && Validations.validatePassword(firstUserFound.get().getPassword())){
                return true;
            }
        }
        return false;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
