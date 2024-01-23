package org.example.simpleerp.repository;

import org.example.simpleerp.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    boolean existsOrderEntityByNumber(
            final Long number
    );
}
