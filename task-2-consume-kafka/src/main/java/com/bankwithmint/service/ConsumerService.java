package com.bankwithmint.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 10:21 AM
 */
@Service
public class ConsumerService {

    @KafkaListener(topics = "com.ng.vela.even.card_verified")
    public void consume(String cardCache) {
        System.out.println(cardCache);
    }

}
