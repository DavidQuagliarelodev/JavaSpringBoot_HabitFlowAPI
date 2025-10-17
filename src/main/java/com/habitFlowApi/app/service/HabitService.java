package com.habitFlowApi.app.service;

import com.habitFlowApi.app.model.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitService {
    Habit save(Habit habit);
    List<Habit> findAll();
    Optional<Habit> findById(long id);
    List<Habit> findAllByUserId(long id);
    Habit update(Habit habit);
    void deleteById(long id);
}
