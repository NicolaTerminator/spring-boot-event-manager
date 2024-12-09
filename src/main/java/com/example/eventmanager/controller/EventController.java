package com.example.eventmanager.controller;

import com.example.eventmanager.dto.EventDto;
import com.example.eventmanager.dto.InfoMsg;
import com.example.eventmanager.entity.Event;
import com.example.eventmanager.exception.BindingException;
import com.example.eventmanager.exception.NotFoundException;
import com.example.eventmanager.service.IEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@Tag(name = "Event", description = "GET methods of Event APIs")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    private IEventService iEventService;

    public EventController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    @PostMapping(value = "/new", produces = "application/json")
    @Operation(summary = "Save new event", description = "Description save new event", responses = {@ApiResponse(responseCode = "400", ref = "badRequest"), @ApiResponse(responseCode = "500", ref = "internalServerError"), @ApiResponse(responseCode = "200", ref = "successfulResponse")})
    public ResponseEntity<InfoMsg> createEvent(@Valid @RequestBody Event event, BindingResult bindingResult) throws BindingException {
        LOGGER.info("RequestBody event: " + event);
        iEventService.saveEvent(event);
        return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), "Event created successfully!"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/modify/{id}", produces = "application/json")
    @Operation(summary = "Modify event", description = "Description modify  event", responses = {@ApiResponse(responseCode = "400", ref = "badRequest"), @ApiResponse(responseCode = "500", ref = "internalServerError"), @ApiResponse(responseCode = "200", ref = "successfulResponse")})
    public ResponseEntity<InfoMsg> modifyEvent(@Parameter(description = "ID of event to be retrieved", required = true) @PathVariable Long id, @Valid @RequestBody Event event, BindingResult bindingResult) throws BindingException {
        LOGGER.info("RequestBody event: " + event + " id: " + id);
        iEventService.modifyEvent(event);
        return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(), "Event updated successfully!"), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @Operation(summary = "Delete event by Id", description = "Description delete event by Id")
    public ResponseEntity<?> remove(@Parameter(description = "ID of event to be retrieved", required = true) @PathVariable Long id) {
        LOGGER.info("event id: " + id);
        iEventService.remove(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Event deleted " + id + " successfully ");
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all events", description = "Description get all events")
    public List<EventDto> findAll() {
        LOGGER.info("findAll");
        List<EventDto> events =  iEventService.searchAll();
        return new ResponseEntity<List<EventDto>>(events, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by Id", description = "Description get event by Id")
    public EventDto findById(@Parameter(description = "ID of event to be retrieved", required = true) @PathVariable Long id) {
        LOGGER.info("findById : " + id);
        EventDto event = iEventService.searchById(id);
        return new ResponseEntity<EventDto>(event, HttpStatus.OK).getBody();
    }

    @GetMapping("/search/date/{date}")
    @Operation(summary = "Get event by date", description = "Description get events by date")
    public ResponseEntity<List<EventDto>> findByDate(@Parameter(description = "Date of event to be retrieved", required = true) @DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable Date date) throws NotFoundException {
        LOGGER.info("findByDate : " + date);
        List<EventDto> events = iEventService.searchByDate(date);
        if (events.isEmpty()) {
            String ErrMsg = String.format("Events in date %s not found!", date);
            LOGGER.warn(ErrMsg);
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<EventDto>>(events, HttpStatus.OK);
    }

    @GetMapping("/search/location/{location}")
    @Operation(summary = "Get event by location", description = "Description get events by location")
    public ResponseEntity<List<EventDto>> findByLocation(@Parameter(description = "Location of event to be retrieved", required = true) @PathVariable String location) throws NotFoundException {
        LOGGER.info("findByLocation : " + location);
        List<EventDto> events = iEventService.searchByLocation(location);
        if (events.isEmpty()) {
            String ErrMsg = String.format("Events in location %s not found!", location);
            LOGGER.warn(ErrMsg);
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<EventDto>>(events, HttpStatus.OK);
    }

}
