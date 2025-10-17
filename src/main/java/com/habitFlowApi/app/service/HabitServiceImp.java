package com.habitFlowApi.app.service;

import com.habitFlowApi.app.model.Habit;
import com.habitFlowApi.app.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitServiceImp implements HabitService{
    @Autowired
    HabitRepository habitRepository;

    @Override
    public Habit save(Habit habit) {
        return habitRepository.save(habit);
    }

    @Override
    public List<Habit> findAll() {
        return habitRepository.findAll();
    }

    @Override
    public Optional<Habit> findById(long id) {
        return habitRepository.findById(id);
    }

    @Override
    public List<Habit> findAllByUserId(long userId) {
        return habitRepository.findAllByUserId(userId);
    }


    @Override
    public Habit update(Habit habit) {
        return habitRepository.save(habit);
    }

    @Override
    public void deleteById(long id) {

        habitRepository.deleteById(id);
    }
}
