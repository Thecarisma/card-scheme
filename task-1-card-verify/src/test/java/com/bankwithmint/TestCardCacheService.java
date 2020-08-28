package com.bankwithmint;

import com.bankwithmint.model.CardCache;
import com.bankwithmint.service.CardCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 01:41 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCardCacheService {

    @Autowired
    CardCacheService cardCacheService;

    @Test
    public void testPublishToKafka() throws JsonProcessingException {
        CardCache cardCache = new CardCache();
        cardCache.setType("debit");
        cardCache.setScheme("visa");
        cardCache.setBank("Yella Hella");
        cardCacheService.publishToKafka(cardCache);
    }

    @Test
    public void testApiCallToBinList() throws JsonProcessingException {
        CardCache cardCache = cardCacheService.fromBinListApi("45717360");
        Assert.notNull(cardCache, "Response for 45717360 not null");
    }

    @Test
    public void testApiCallsToBinList() throws JsonProcessingException {
        String[] cardNumbers = new String[] { "45717360", "45717120", "3572625"};
        for (String cardNumber : cardNumbers) {
            CardCache cardCache = cardCacheService.fromBinListApi(cardNumber);
            Assert.notNull(cardCache, "Response for " + cardNumber + " not null");
        }
    }

}
