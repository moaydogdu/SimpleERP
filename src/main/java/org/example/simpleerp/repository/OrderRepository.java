package org.example.simpleerp.repository;

import org.example.simpleerp.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    boolean existsOrderEntityByNumber(
            final Long number
    );

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM OrderEntity o")
    BigDecimal getTotalPriceSum();
}
