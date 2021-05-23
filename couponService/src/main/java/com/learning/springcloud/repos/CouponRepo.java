package com.learning.springcloud.repos;

import com.learning.springcloud.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon,Long> {
   public  Coupon findByCode(String code);
}
