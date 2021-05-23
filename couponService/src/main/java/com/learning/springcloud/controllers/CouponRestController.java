package com.learning.springcloud.controllers;

import com.learning.springcloud.model.Coupon;
import com.learning.springcloud.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    private CouponRepo repo;

    @RequestMapping(value = "/coupons", method = RequestMethod.POST)
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return repo.save(coupon);
    }
    @RequestMapping(value = "/coupons/{code}", method = RequestMethod.GET)
    public Coupon getCoupon(@PathVariable("code") String code){
        return repo.findByCode(code);
    }

}
