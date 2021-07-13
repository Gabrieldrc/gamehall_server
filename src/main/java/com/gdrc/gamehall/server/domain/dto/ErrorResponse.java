package com.gdrc.gamehall.server.domain.dto;

public class ErrorResponse extends DefaultResponse{
    public ErrorResponse() {
        setOk(false);
    }

    public ErrorResponse(String message) {
        setOk(false);
        setDataProperty("message", message);
    }
}
