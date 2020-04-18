package pro.sisit.unit9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sisit.unit9.data.PurshedBookRepository;
import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Customer;
import pro.sisit.unit9.entity.PurshedBook;

import java.math.BigDecimal;


@SpringBootApplication
public class BookService {

    @Autowired
    PurshedBookRepository purshedBookRepository;

    public static void main(String[] args) {
    }

    /**
     * операция покупки книги покупателем
     *
     * @param book
     * @param customer
     * @param price
     */
    public void PayBook(Book book, Customer customer, BigDecimal price) {

        PurshedBook purshedBook = new PurshedBook();
        purshedBook.setBook(book);
        purshedBook.setCustomer(customer);
        purshedBook.setPrice(price);
        purshedBookRepository.save(purshedBook);
    }

    /**
     * подсчет общей стоимости продаж по книге;
     *
     * @param book
     * @return
     */
    public BigDecimal SaleAmountBook(Book book) {
        Long id = book.getId();
        return purshedBookRepository.getSumPrice(id);
    }

    /**
     * подсчет общей стоимости купленных книг по покупателю
     *
     * @param customer
     * @return
     */
    public BigDecimal TotalCostCustomer(Customer customer) {
        Long id = customer.getId();
        return purshedBookRepository.getTotalPriceCustomer(id);
    }
}
