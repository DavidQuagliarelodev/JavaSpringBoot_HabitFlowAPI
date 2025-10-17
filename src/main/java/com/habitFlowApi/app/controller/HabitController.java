package com.habitFlowApi.app.controller;
import com.habitFlowApi.app.config.Autenticator;
import com.habitFlowApi.app.dto.request.HabitRequestDTO;
import com.habitFlowApi.app.dto.request.HabitResponseDTO;
import com.habitFlowApi.app.model.Habit;
import com.habitFlowApi.app.model.User;
import com.habitFlowApi.app.service.HabitServiceImp;
import com.habitFlowApi.app.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habit")
public class HabitController {
    @Autowired
    HabitServiceImp habitServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    Autenticator autenticator;


//    @GetMapping
//    public ResponseEntity<?> findAllHabits(){
//        List<Habit> habits =  habitServiceImp.findAll();
//        List<HabitResponseDTO> habitResponseDTOS = new ArrayList<>();
//        for(Habit habit : habits){
//            HabitResponseDTO habitResponseDTO = new HabitResponseDTO();
//            habitResponseDTO.setId(habit.getId());
//            habitResponseDTO.setName(habit.getName());
//            habitResponseDTO.setDescription(habit.getDescription());
//            habitResponseDTO.setFrequency(habit.isFrequency());
//            habitResponseDTO.setSuggestedTime(habit.getSuggestedTime());
//            habitResponseDTO.setActve(habit.isActve());
//            habitResponseDTO.setComplete(habit.isComplete());
//            habitResponseDTO.setUserId(habit.getUser().getId());
//            habitResponseDTOS.add(habitResponseDTO);
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(habitResponseDTOS);
//    }

    @GetMapping("/{token}")
    public ResponseEntity<?> findAllByUserId(@PathVariable long token){
        System.out.println("UserID::::: " + autenticator.getUserId());
        List<Habit> habits = habitServiceImp.findAllByUserId(autenticator.getUserId());
        System.out.println("UserID::::: " + autenticator.getUserId());
        if(token != autenticator.getToken()){
            return ResponseEntity.badRequest().body("User not found or token invalid");
        }else{
            if(habits.isEmpty()){
                return ResponseEntity.badRequest().body("User not found or token invalid");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(habits);
            }
        }

    }

    @PostMapping("{token}/new")
    public ResponseEntity<?> createHabit(@PathVariable long token, @RequestBody HabitRequestDTO habitRequestDTO){
         Optional<User> findUserById = userServiceImp.findById(autenticator.getUserId());
         if(token != autenticator.getToken()){
             return ResponseEntity.badRequest().body("User not found or token invalid");
         }else{
             if(findUserById.isEmpty()){
                 return ResponseEntity.badRequest().body("User not found or token invalid");
             }else{
                 Habit habit = new Habit();
                 habit.setName(habitRequestDTO.getName());
                 habit.setDescription(habitRequestDTO.getDescription());
                 habit.setFrequency(habitRequestDTO.isFrequency());
                 habit.setSuggestedTime(habitRequestDTO.getSuggestedTime());
                 habit.setActve(habitRequestDTO.isActve());
                 habit.setComplete(habitRequestDTO.isComplete());
                 habit.setUser(findUserById.get());

                 return ResponseEntity.status(HttpStatus.CREATED).body(habitServiceImp.save(habit));
             }

         }
    }

//    @PostMapping("/new")
//    public ResponseEntity<?> createHabit(@RequestBody HabitRequestDTO habitRequestDTO){
//        Optional<User> findUserById = userServiceImp.findById(habitRequestDTO.getUserId());
//        if(findUserById.isEmpty()){
//            return ResponseEntity.badRequest().body("User not found");
//        }else{
//            Habit habit = new Habit();
//            habit.setName(habitRequestDTO.getName());
//            habit.setDescription(habitRequestDTO.getDescription());
//            habit.setFrequency(habitRequestDTO.isFrequency());
//            habit.setSuggestedTime(habitRequestDTO.getSuggestedTime());
//            habit.setActve(habitRequestDTO.isActve());
//            habit.setComplete(habitRequestDTO.isComplete());
//            habit.setUser(findUserById.get());
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(habitServiceImp.save(habit));
//        }
//    }

    @PutMapping
    public ResponseEntity<Habit> updateHabit(@RequestBody Habit habit){
        return ResponseEntity.status(HttpStatus.CREATED).body(habitServiceImp.save(habit));
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> deleteHabitById(@PathVariable  long id){
            habitServiceImp.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).build();
    }
}
