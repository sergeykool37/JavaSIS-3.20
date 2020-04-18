package pro.sisit.unit9.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * сущность "купленная книга", у которой есть атрибуты покупатель, стоимость, и книга
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurshedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "price")
    private BigDecimal price;
}
