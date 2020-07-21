package com.zty.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zty.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 有问题
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global_fallback")
@RequestMapping("/consumer")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String order_OK(@PathVariable("id") Long id){
        return paymentHystrixService.OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand/**/
    (fallbackMethod = "order_ExceptHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })
    public String order_TimeOut(@PathVariable("id") Long id){
//        int age = 7/0;
        return paymentHystrixService.TimeOut(id);
    }

    //单个fallback
    public String order_ExceptHandler(Long id){
        log.info("*******************拉拉啦***********************");
        return "消费者80，对方繁忙或异常，请稍后再试，查询id:"+id+"\t失败";
    }

    public String global_fallback(){
        return "全局异常处理，请稍后再试";
    }
}
