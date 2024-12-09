package com.example.eventmanager.service;

import com.example.eventmanager.dto.EventDto;
import com.example.eventmanager.entity.Event;
import com.example.eventmanager.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;

@Service
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EventDto saveEvent(Event event) {
        LOGGER.info("event: " + event);
        return ConvertToDto(Optional.of(eventRepository.save(event)));
    }

    @Override
    public void remove(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> searchAll() {
        return ConvertToDtos(eventRepository.findAll());
    }

    @Override
    public EventDto searchById(Long id) {
        return ConvertToDto(eventRepository.findById(id));
    }

    @Override
    public List<EventDto> searchByDate(Date date) {
        return ConvertToDtos(eventRepository.findByDate(date));
    }

    @Override
    public List<EventDto> searchByLocation(String location) {
        return ConvertToDtos(eventRepository.findByLocation(location));
    }

    @Override
    public EventDto modifyEvent(Event event) {
        return ConvertToDto(Optional.of(eventRepository.save(event)));
    }


    private EventDto ConvertToDto(Optional<Event> event) {
        EventDto eventDto = null;
        if (event != null) {
            eventDto = modelMapper.map(event, EventDto.class);
        }
        return eventDto;
    }

    private List<EventDto> ConvertToDtos(List<Event> events) {
        List<EventDto> eventsDto = events
                .stream()
                .map(source -> modelMapper.map(source, EventDto.class))
                .collect(Collectors.toList());
        return eventsDto;
    }


}