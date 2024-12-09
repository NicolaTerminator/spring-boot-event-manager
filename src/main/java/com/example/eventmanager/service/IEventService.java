package com.example.eventmanager.service;

import com.example.eventmanager.dto.EventDto;
import com.example.eventmanager.entity.Event;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

public interface IEventService {
    public EventDto saveEvent(Event event);
    public void remove(Long id);
    public List<EventDto> searchAll();
    public EventDto searchById(Long id);
    public List<EventDto> searchByDate(Date date);
    public List<EventDto> searchByLocation(String location);
    public EventDto modifyEvent(Event event);
}