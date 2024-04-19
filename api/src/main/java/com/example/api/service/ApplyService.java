package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import com.example.api.repository.FailCountRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;
    private final FailCountRepository failCountRepository;

    public ApplyService(CouponRepository couponRepository,
                        CouponCountRepository couponCountRepository,
                        CouponCreateProducer couponCreateProducer,
                        AppliedUserRepository appliedUserRepository,
                        FailCountRepository failCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
        this.failCountRepository = failCountRepository;
    }

    public void apply(Long userId) {
        Long applied = appliedUserRepository.add(userId);
        if(applied != 1) {
            failCountRepository.incrementFail();
            return ;
        }

        long count = couponCountRepository.increment();
        if(count > 10000) {
            failCountRepository.incrementFail();
            return ;
        }

        //couponCreateProducer.create(userId);
        couponRepository.save(
                new Coupon(userId)
        );
    }
}
