package com.bankwithmint.controller;

import com.bankwithmint.model.CardCache;
import com.bankwithmint.model.SingleResponse;
import com.bankwithmint.service.CardCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:45 AM
 */
@RestController
@RequestMapping("/card-scheme")
public class CardSchemeController {

    @Autowired
    CardCacheService cardCacheService;

    @RequestMapping(value = "/verify/{cardNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyCard(@PathVariable("cardNumber") String cardNumber) throws JsonProcessingException {
        CardCache cardCache = cardCacheService.verifyCard(cardNumber);
        return new ResponseEntity<>(new SingleResponse<>(cardCache != null, cardCache), HttpStatus.FOUND);
    }

}
