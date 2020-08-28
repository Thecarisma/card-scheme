package com.bankwithmint.controller;

import com.bankwithmint.model.SingleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:45 AM
 */
@RestController
@RequestMapping("/card-scheme")
public class CardSchemeController {

    @RequestMapping(value = "/verify/{cardNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listFriends(@RequestHeader("Authorization") String authorization, Pageable pageable) throws JsonProcessingException {
        return new ResponseEntity<>(new SingleResponse<>(false, "nothing"), HttpStatus.FOUND);
    }

}
