package com.bankwithmint.service;

import com.bankwithmint.model.BinListResponse;
import com.bankwithmint.model.CardCache;
import com.bankwithmint.repository.CardCacheRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
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

    @Autowired
    StatService statService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String TOPICS = "com.ng.vela.even.card_verified";

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
    public CardCache verifyCard(String cardNumber) throws JsonProcessingException {
        statService.logCardHit(cardNumber);
        Optional<CardCache> cachedCard = cardCacheRepository.findByCardNumber(cardNumber);
        if (!cachedCard.isPresent()) {
            CardCache cardCache = fromBinListApi(cardNumber);
            if (cardCache != null) {
                cardCacheRepository.save(cardCache);
                publishToKafka(cardCache);
                return cardCache;
            }
            return null;
        }
        publishToKafka(cachedCard.get());
        return cachedCard.get();
    }

    public CardCache fromBinListApi(String cardNumber) {
        CardCache cardCache = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept-Version", "3");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = "https://lookup.binlist.net/" + cardNumber;
        try {
            ResponseEntity<BinListResponse> response  = restTemplate.exchange(url, HttpMethod.GET, entity, BinListResponse.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                cardCache = new CardCache();
                cardCache.setCardNumber(cardNumber);
                cardCache.setType(response.getBody().getType());
                cardCache.setScheme(response.getBody().getScheme());
                cardCache.setBank(response.getBody().getBank().getName());
            }
        } catch (HttpStatusCodeException exception) {
            exception.printStackTrace();
        }
        return cardCache;
    }

    public void publishString(String topic, String value) {
        kafkaTemplate.send(topic, value);
    }

    // @Async not working
    public void publishToKafka(CardCache cardCache) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cardCache);
        new Thread(() -> publishString(TOPICS, json)).start();
    }

}
