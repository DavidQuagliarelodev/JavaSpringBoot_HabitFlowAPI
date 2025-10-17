package com.habitFlowApi.app.repository;

import com.habitFlowApi.app.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findAllByUserId(long userId);
}
