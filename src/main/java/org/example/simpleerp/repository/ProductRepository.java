package org.example.simpleerp.repository;

import org.example.simpleerp.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    boolean existsProductEntityByNumber(
            final Long number
    );

    Optional<ProductEntity> findProductEntityByNumber(
            final Long number
    );
}
