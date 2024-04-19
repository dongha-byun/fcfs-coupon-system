package com.example.api.controller;

import com.example.api.service.ApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {
    private final ApplyService applyService;

    public CouponController(ApplyService applyService) {
        this.applyService = applyService;
    }

    @PostMapping("/coupons")
    public ResponseEntity<Void> apply(@RequestBody ApplyRequest applyRequest) {
        applyService.apply(applyRequest.getUserId());
        return ResponseEntity.ok().build();
    }
}
