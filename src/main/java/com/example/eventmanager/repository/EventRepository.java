package com.example.eventmanager.repository;

import com.example.eventmanager.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    //Query Method
    List<Event> findAll();

    //Query Method
    List<Event> findByDate(Date date);

    //Query Method
    List<Event> findByLocation(String location);
}