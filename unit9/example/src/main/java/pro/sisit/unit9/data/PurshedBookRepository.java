package pro.sisit.unit9.data;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pro.sisit.unit9.entity.PurshedBook;

import java.math.BigDecimal;
import java.util.List;

public interface PurshedBookRepository extends CrudRepository<PurshedBook,Long> {
    List<PurshedBook> findByPrice(BigDecimal price);

    @Query("select sum(pur.price) from PurshedBook pur join pur.book where  pur.book.id=?1")
    BigDecimal getSumPrice(Long id);

    @Query("select sum(pur.price) from PurshedBook pur join pur.customer where pur.customer.id=?1")
    BigDecimal getTotalPriceCustomer(Long id);
}
