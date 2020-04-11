package com.ServisKlinickihCentara.dto;

public class MessageDTO {
    private String message;
    private boolean success;

    public MessageDTO(){

    }

    public MessageDTO(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
