package com.bankwithmint.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 10:21 AM
 */
@Service
public class ConsumerService {

    List<String> cardFromKaftaHolder = new ArrayList<>();

    @KafkaListener(topics = "com.ng.vela.even.card_verified")
    public void consume(String cardCache) {
        cardFromKaftaHolder.add(cardCache);
        System.out.println(cardCache);
    }

    public Object[] fetchPendingData() {
        Object[] ret = Arrays.copyOf(cardFromKaftaHolder.toArray(), cardFromKaftaHolder.size());
        cardFromKaftaHolder = new ArrayList<>();
        return ret;
    }

}
