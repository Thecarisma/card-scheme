package com.bankwithmint.repository;

import com.bankwithmint.model.CardCache;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 04-Jun-20 02:43 AM
 */
@Repository
public interface CardCacheRepository extends CrudRepository<CardCache, Long> {

    @Query(value = "SELECT * FROM card_cache WHERE card_number = ?1", nativeQuery = true)
    Optional<CardCache> findByCardNumber(String cardNumber);

}
