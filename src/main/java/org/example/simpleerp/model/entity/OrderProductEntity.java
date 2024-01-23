package org.example.simpleerp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "ORDER_ID",
            referencedColumnName = "ID"
    )
    private OrderEntity order;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "PRODUCT_ID",
            referencedColumnName = "ID"
    )
    private ProductEntity product;

    @Column(
            name = "AMOUNT",
            precision = 24,
            scale = 4
    )
    private BigDecimal amount;

    @Column(
            name = "UNIT_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal unitPrice;

    @Column(
            name = "TOTAL_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal totalPrice;
}
