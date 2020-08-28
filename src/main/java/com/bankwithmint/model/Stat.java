package com.bankwithmint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:44 AM
 */
@Entity
@Table(name = "stat")
public class Stat {

    @Id
    @JsonIgnore
    long id;

    @Column(name = "card_number")
    String cardNumber;

    @Column(name = "hit_counter")
    long hitCounter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getHitCounter() {
        return hitCounter;
    }

    public void setHitCounter(long hitCounter) {
        this.hitCounter = hitCounter;
    }
}
