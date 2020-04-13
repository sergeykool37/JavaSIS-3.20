package pro.sisit.unit9.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import pro.sisit.unit9.data.*;
import pro.sisit.unit9.entity.*;

import java.math.BigDecimal;


@SpringBootApplication
public class BookService {

    @Autowired
    PurshedBookRepository purshedBookRepository;

    public static void main(String[] args){ }
    //операция покупки книги покупателем
    public void PayBook(Book book, Customer customer,BigDecimal price){

        PurshedBook purshedBook=new PurshedBook();
        purshedBook.setBook(book);
        purshedBook.setCustomer(customer);
        purshedBook.setPrice(price);
        purshedBookRepository.save(purshedBook);
    }
    //подсчет общей стоимости продаж по книге;
    public BigDecimal SaleAmountBook(Book book){
        Long id=book.getId();
        return purshedBookRepository.getSumPrice(id);
    }
    //подсчет общей стоимости купленных книг по покупателю
    public BigDecimal TotalCostCustomer(Customer customer){
        Long id=customer.getId();
        return purshedBookRepository.getTotalPriceCustomer(id);
    }
}
