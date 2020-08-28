package com.bankwithmint.service;

import com.bankwithmint.model.Stat;
import com.bankwithmint.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 08:57 AM
 */
@Service
public class StatService {

    @Autowired
    StatRepository statRepository;

    public List<Stat> getStats(int start, int limit) {
        return statRepository.findAll(start, limit);
    }

    public int getStatsCount() {
        return statRepository.countStat();
    }

}
