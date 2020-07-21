package com.zty.cloud.service;

import com.zty.cloud.service.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentHystrixService {
//,
    @GetMapping("/payment/hystrix/ok/{id}")
    public String OK(@PathVariable("id") long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String TimeOut(@PathVariable("id") long id);
}
