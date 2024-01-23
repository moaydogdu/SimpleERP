package org.example.simpleerp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(
            name = "NUMBER",
            nullable = false,
            unique = true
    )
    private Long number;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "order"
    )
    private List<OrderProductEntity> orderProductEntities;

    @Column(
            name = "TOTAL_PRICE",
            precision = 24,
            scale = 4,
            nullable = false
    )
    private BigDecimal totalPrice;

}
