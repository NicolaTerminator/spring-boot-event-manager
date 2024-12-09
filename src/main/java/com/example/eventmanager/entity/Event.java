package com.example.eventmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "EVENTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title"),
        @UniqueConstraint(columnNames = "location"),
        @UniqueConstraint(columnNames = "date")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Schema(description = "Event id", example = "")
    Long id;

    @Schema(description = "Event title", example = "iBee party")
    @Size(min = 5, max = 50, message = "The title should contain a minimum of 5 characters and a maximum of 50 characters")
    private String title;

    @Schema(description = "Event description", example = "iBee party is consist of ...")
    @Size(max = 120, message = "The title should contain a maximum of 120 characters")
    private String description;

    @Schema(description = "Event date", example = "")
    private Date date;

    @Schema(description = "Event location", example = "")
    private String location;

    @Column(name = "maximum_capacity")
    //@Min(value = 2, message = "A minimum of two people")
    @Max(value = 999, message = "A maximum of two people")
    @Schema(description = "Event maximum capacity", example = "", defaultValue = "2")
    private int maximumCapacity;

    @Schema(description = "Event price", example = "")
    private double price;

}
