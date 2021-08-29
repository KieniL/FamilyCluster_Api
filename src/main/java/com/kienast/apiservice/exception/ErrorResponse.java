package com.kienast.apiservice.exception;

public class ErrorResponse
{
    public ErrorResponse(String messages) {
        super();
        this.messages = messages;
    }
  
    private String messages;

    
    public String getMessages() {
      return messages;
    }
    public void setMessages(String messages) {
      this.messages = messages;
    }


    

  }
