package com.bankwithmint.repository;

import com.bankwithmint.model.Stat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 04-Jun-20 02:43 AM
 */
@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

    @Query(value = "SELECT * FROM stat WHERE card_number = ?1", nativeQuery = true)
    Optional<Stat> findByCardNumber(String cardNumber);

    @Query(value = "SELECT * FROM stat", nativeQuery = true)
    List<Stat> findAll(int start, int limit);

    @Query(value = "SELECT count(*) FROM stat", nativeQuery = true)
    int countStat();

}
