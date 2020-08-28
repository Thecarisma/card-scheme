package com.bankwithmint.service;

import com.bankwithmint.model.BinListResponse;
import com.bankwithmint.model.CardCache;
import com.bankwithmint.repository.CardCacheRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 08:15 AM
 */
@Service
public class CardCacheService {

    @Autowired
    CardCacheRepository cardCacheRepository;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * The first time a card is looked up it is fetched from the binlist
     * API and the result is cached, consequently if the same card is
     * requested it is fetched from cache rather than calling the binlist API.
     *
     * Also binlist.net has a 10 request per minute limit, so cache is needed
     * @param cardNumber
     * @return
     */
    public CardCache verifyCard(String cardNumber) {
        Optional<CardCache> cachedCard = cardCacheRepository.findByCardNumber(cardNumber);
        if (!cachedCard.isPresent()) {
            CardCache cardCache = fromBinListApi(cardNumber);
            if (cardCache != null) {
                cardCacheRepository.save(cardCache);
                return cardCache;
            }
            return null;
        }
        return cachedCard.get();
    }

    private CardCache fromBinListApi(String cardNumber) {
        CardCache cardCache = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept-Version", "3");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = "https://lookup.binlist.net/" + cardNumber;
        ResponseEntity<BinListResponse> response  = restTemplate.exchange(url, HttpMethod.GET, entity, BinListResponse.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            cardCache = new CardCache();
            cardCache.setCardNumber(cardNumber);
            cardCache.setType(response.getBody().getType());
            cardCache.setScheme(response.getBody().getScheme());
            cardCache.setBank(response.getBody().getBank().getName());
        }
        return cardCache;
    }

}
