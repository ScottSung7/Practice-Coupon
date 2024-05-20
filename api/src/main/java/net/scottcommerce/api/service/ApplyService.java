package net.scottcommerce.api.service;

import jakarta.transaction.Transactional;
import net.scottcommerce.api.domain.Coupon;
import net.scottcommerce.api.repository.CouponCountRepository;
import net.scottcommerce.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponCountRepository couponCountRepository;

    private final CouponRepository couponRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }
    public void apply(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

       couponRepository.save(new Coupon(userId));

    }
}
