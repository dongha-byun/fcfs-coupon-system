package com.example.api.controller;

public class ApplyRequest {
    private Long userId;

    public ApplyRequest(Long userId) {
        this.userId = userId;
    }

    public ApplyRequest() {
    }

    public Long getUserId() {
        return userId;
    }
}
