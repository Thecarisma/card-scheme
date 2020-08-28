package com.bankwithmint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:37 AM
 */
public class Card {

    @Id
    @JsonIgnore
    long id;

    String scheme;

    String type;

    String bank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
