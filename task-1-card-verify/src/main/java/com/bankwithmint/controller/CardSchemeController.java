package com.bankwithmint.controller;

import com.bankwithmint.model.CardCache;
import com.bankwithmint.model.PagedResponse;
import com.bankwithmint.model.SingleResponse;
import com.bankwithmint.model.Stat;
import com.bankwithmint.service.CardCacheService;
import com.bankwithmint.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:45 AM
 */
@RestController
@RequestMapping("/card-scheme")
public class CardSchemeController {

    @Autowired
    CardCacheService cardCacheService;

    @Autowired
    StatService statService;

    @RequestMapping(value = "/verify/{cardNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyCard(@PathVariable("cardNumber") String cardNumber) {
        CardCache cardCache = cardCacheService.verifyCard(cardNumber);
        if (cardCache == null) {
            return new ResponseEntity<>(new SingleResponse<>(false, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SingleResponse<>(true, cardCache), HttpStatus.OK);
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> stats(@RequestParam(name = "start", defaultValue = "1") int start,
                                   @RequestParam(name = "limit", defaultValue = "3") int limit) {
        List<Stat> stat = statService.getStats(start, limit);
        PagedResponse<List<Stat>> pagedResponse = new PagedResponse<>();
        pagedResponse.setSize(statService.getStatsCount());
        pagedResponse.setSuccess(stat.size() > 0);
        pagedResponse.setStart(start);
        pagedResponse.setLimit(limit);
        pagedResponse.setPayload(stat);
        return new ResponseEntity<>(pagedResponse, HttpStatus.OK);
    }

}
