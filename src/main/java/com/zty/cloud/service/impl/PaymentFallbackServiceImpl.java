package com.zty.cloud.service.impl;

import com.zty.cloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String OK(long id) {
        return "----------------PaymentHystrixService fallback OK ,重试";
    }

    @Override
    public String TimeOut(long id) {
        return "----------------PaymentHystrixService fallback TIMEOUT ,重试";
    }
}
