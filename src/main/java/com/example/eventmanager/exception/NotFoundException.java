package com.example.eventmanager.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends Exception
{
    private String message = "Item not found!";
    public NotFoundException()
    {
        super();
    }
    public NotFoundException(String message)
    {
        super(message);
        this.message = message;
    }
}
