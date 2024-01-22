package org.example.simpleerp.repository;

import org.example.simpleerp.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    boolean existsProductEntityByNumber(
            final Long number
    );
}
