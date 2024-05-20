package net.scottcommerce.api.service;

import jakarta.transaction.Transactional;
import net.scottcommerce.api.domain.Coupon;
import net.scottcommerce.api.producer.CouponCreateProducer;
import net.scottcommerce.api.repository.CouponCountRepository;
import net.scottcommerce.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponCountRepository couponCountRepository;

    private final CouponRepository couponRepository;

    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }
    public void apply(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        //couponCreateProducer.create(userId);
       couponRepository.save(new Coupon(userId));

    }
}
