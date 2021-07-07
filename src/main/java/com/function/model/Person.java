package com.function.model;

import com.microsoft.azure.servicebus.Message;

public class Person {


    private String name;
    private String id;

    private Message message;

   
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
       this.name = name;
        
    }

    public String getId() {        
        return this.id;
    }

    public void setId(String id) {
       this.id = id;
        
    }

    public Message getMessage() {        
        return message;
    }

    public void setMessage(Message message) {       
        this.message = message;        
    }


    
}
