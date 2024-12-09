package com.example.eventmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventDto {
    Long id;
    private String title;
    private String description;
    private Date date;
    private String location;
    private int maximumCapacity;
    private double price;
}
