package com.learning.springcloud.controllers;

import com.learning.springcloud.dto.Coupon;
import com.learning.springcloud.model.Product;
import com.learning.springcloud.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
    @Autowired
    private ProductRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gatewayURL}")
    private String gatewayUrl;

    @Value("${couponServicePath}")
    private String couponServicePath;




    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        System.out.println(gatewayUrl+couponServicePath+ "coupons/"+product.getCouponCode());
        Coupon coupon = restTemplate.getForObject(gatewayUrl+couponServicePath+ "coupons/"+product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

    @RequestMapping(value = "/products/{name}",method = RequestMethod.GET)
    public Product getProduct(@PathVariable("name") String name){
        return repo.findByName(name);
    }


}
