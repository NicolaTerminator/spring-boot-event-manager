package com.example.eventmanager.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindingException  extends Exception
{

    private String messaggio;
    public BindingException()
    {
        super();
    }
    public BindingException(String messaggio)
    {
        super(messaggio);
        this.messaggio = messaggio;
    }
}
