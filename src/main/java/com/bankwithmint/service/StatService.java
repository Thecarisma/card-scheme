package com.bankwithmint.service;

import com.bankwithmint.model.Stat;
import com.bankwithmint.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 08:57 AM
 */
@Service
@EnableAsync
public class StatService {

    @Autowired
    StatRepository statRepository;

    @Async
    public void logCardHit(String cardNumber) {
        Optional<Stat> statResult = statRepository.findByCardNumber(cardNumber);
        if (statResult.isPresent()) {
            statResult.get().setHitCounter(statResult.get().getHitCounter() + 1);
            statRepository.save(statResult.get());
        } else {
            Stat stat = new Stat();
            stat.setCardNumber(cardNumber);
            stat.setHitCounter(1);
            statRepository.save(stat);
        }
    }

    public List<Stat> getStats(int start, int limit) {
        return statRepository.findAll(start, limit);
    }

    public int getStatsCount() {
        return statRepository.countStat();
    }

}
