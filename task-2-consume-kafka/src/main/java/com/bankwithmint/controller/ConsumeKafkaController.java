package com.bankwithmint.controller;

import com.bankwithmint.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 11:41 AM
 */
@Controller
public class ConsumeKafkaController {

    @Autowired
    ConsumerService consumerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/fetch_new", method = RequestMethod.GET)
    public ResponseEntity<?> fetNewData() {
        return new ResponseEntity<>(consumerService.fetchPendingData(), HttpStatus.OK);
    }

}
