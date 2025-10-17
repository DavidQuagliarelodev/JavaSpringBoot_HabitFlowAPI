package com.habitFlowApi.app.config;
import com.habitFlowApi.app.model.User;
import com.habitFlowApi.app.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;
import java.util.Random;

@Data
@Configuration
public class Autenticator {

    @Autowired
    UserRepository userRepository;

    private String name;
    private String password;
    private long userId;
    private long token;


    public void tokenGenerate(String name, String password, long userId){
        if(validateUser(name, password, userId)){
            Random random = new Random();
            long token = random.nextInt(20000) + 10000;
            this.token = token;
        }else{
            this.token = 0;
        }
    }

    public boolean validateUser(String name, String password, Long userId){
        Optional<User> findByUserId = userRepository.findById(userId);
        Optional<User> findByNameUser = userRepository.findByName(name);
        Optional<User> findByPasswordUser = userRepository.findByPassword(password);
        if(findByNameUser.isEmpty() && findByPasswordUser.isEmpty() && findByUserId.isEmpty()){
            return false;
        }else{
            this.name = name;
            this.password = password;
            this.userId = userId;
            return true;
        }
    }
}
